import traceback
import numpy as np
import javalang
import python_debugger as pyd
import os
import chardet
import glob
import testAugmentedCode as aug

from chardet.universaldetector import UniversalDetector
detector = UniversalDetector()

def getCost(item, Locked=[]):
    #print(item)
    if item in Locked:
        cost= 999999
    else:
        cost= 1
    #print(cost)
    return cost

# Use dynamic programming to solve the transforing problem
def matchPatch(A, B, Locked=[]):
    L = len(A)  #4
    M = len(B)  #5

    P = np.zeros((L+1, M+1) , dtype=int)
    Act = np.empty((L+1, M+1), dtype=object)

    for x in range(L+1):
        P[x,0] = sum([ getCost(A[i], Locked) for i in range(L-x, L)] )
        if x>0:
            Act[x,0] = {"act":('DelRestA',L-x), "dir":None}
        else:
            Act[x,0] = {"act": ("Stop", None), "dir": None}
    for y in range(M+1):
        P[0,y] = y
        if y>0:
            Act[0,y] = {"act": ( 'AddRestB', B[M-y:]), "dir": None}
        else:
            Act[0,y] = {"act": ("Stop", None), "dir": None}
        
    for l in range(1, L+1):
        for m in range(1, M+1):
            if A[L-l]== B[M-m]:
                P[l,m] = P[l-1,m-1]
                Act[l,m] = {"act": ("Forward", None), "dir": (-1, -1) } #output current A, and move to next position
            else:
                c1 = P[l-1,m-1] +  getCost(A[L-l], Locked)
                c2 = P[l-1,m]+1 +  getCost(A[L-l], Locked)
                c3 = P[l,m-1]+1
                P[l,m]=min(c1,   c2, c3)
                if (P[l,m] == c1):
                     Act[l,m] = {"act": ('ReplaceAwB',( A[L-l], B[M-m]) ), "dir":(-1, -1) } # Replace A with B
                elif (P[l,m] == c2):
                     Act[l,m] = {"act": ('DelA', A[L-l]), "dir": (-1,0) } # Del A
                elif (P[l,m] == c3):
                     Act[l,m] = {"act": ("AddB" , B[M-m]), "dir": (0,-1) } # Add B
    return (P, Act)

# Given a P matric, and Act matric
# return the transforming rules
def getSol(P, Act):
    L = P.shape[0]-1
    M = P.shape[1]-1
    sol=[]
    l = L
    m = M
    while (l>=0 and m>=0):
        act = Act[l,m]["act"]
        offset = Act[l,m]["dir"]
        sol.append(act)
        if not offset is None:
            l += offset[0]
            m += offset[1]
        else:
            l = -1
            m = -1

    return sol

## Given a program A, and a transforming rules sol
## return the transformed programm of A, a list of tokens
def genNewSeq(A, sol) :
    myprint("Depatch: genNewSeq: len(sol), sol:", len(sol), sol)
    myprint("Depatch:  len(A), A:", len(A), A)
    k=0;
    seq=[]
    flagFail=False
    #try: 
    for i in range(len(sol)):
        myprint("loop: k: ", k)
        if (k>=len(A)): break
        act = sol[i][0]
        myprint("loop: act: ", act)
        pos = sol[i][1]
        if act == "Stop": break
        elif act=="Forward":
            k,sp = skipNewLine(A,k)
            seq += sp
            myprint("Forward: k, len(A)", k, len(A))
            if k<len(A): 
                seq.append(A[k])
                myprint("A[k] is forwarded: ", A[k])
            k,sp = skipNewLine(A,k+1)
            seq += sp
        elif act=="AddB":
            seq.append(pos)
        elif act == "ReplaceAwB": #first delete, then add
            myprint("ReplaceAwB: k, pos[1]:", k, pos[1])
            if (pos[1] != "="):
                k,sp = skipNewLine(A,k+1)
                seq+= sp
            else:
                k += 1
            seq.append(pos[1]) 
            if pos[1]=="}":
                seq+= [pyd.NEWLINE]
        elif act == "DelA":
            k,sp = skipNewLine(A,k+1)
            seq += sp
            #print("seq:", seq)
        elif act == "DelRestA":
            seq += collectNewLine(A, k)
            break
        elif act == "AddRestB":
            myprint("Depatch: genNewSeq: pos:", pos)
            seq.append(pos[0])
            seq+= A[k:]            
            
    #except:
    #    myprint("Gen New Seq Error! in Depatch.py")    
    
    return seq
    
def skipNewLine(A, k):
    sp=[]
    while k < len(A) and A[k]==pyd.NEWLINE  :
        sp+=[pyd.NEWLINE]
        k = k+1	
    #if (k>=len(A)): k=k-1
    return k,sp
  
def collectNewLine(A, k):
    sp=[]
    while k < len(A)   :
        if A[k]==pyd.NEWLINE:
            sp+=[pyd.NEWLINE]
        k = k+1	
    #if (k>=len(A)): k=k-1
    return sp
    
    
def xx(filename):
    detector.reset()
    for line in open(filename, 'rb'):
        detector.feed(line)
        if detector.done: break
    detector.close()
    return( detector.result)

def reversePermutation(p):
    #return {v: k for k, v in sorted(p.items(), key=lambda item: item[1])}
    return   {v: k for k, v in p.items()}   

def permutateCode(C, p): #C: list of tokens, p: permu dict
    lines = []
    line = []
    for tok in C:
        line.append(tok)
        if tok==pyd.NEWLINE:
            lines.append(line)
            line=[]

    target_lines=[]
    myprint("Depatch: permutateCode: C: ", C)
    myprint("Depatch: permutateCode: p: ", p)
    myprint("Depatch: permutateCode: lines: ", len(lines), lines)
    for old, new in sorted(p.items(), key=lambda item: item[1])	: #order by new line
        myprint("Depatch: permutateCode: old:", old)
        if old <=len(lines):
            target_lines.append(lines[old-1])
        else:
            myprint("Depatch: permutateCode: IndexError: list index out of range")
    myprint("Depatch: permutateCode: target_lines:", target_lines)
    return  [item for sublist in target_lines for item in sublist]
    
## Given a clean newSource java code,  and whether to change variable
## return the syntax buggy coriginal code with variable renamed
## named by destfile_copynum.java
## always no permutate
def transformJavaCode(sol, newSourcefile, changeVariable=False , permuteStatement=False, task=1):
    #encoding=xx(newSourcefile).get('encoding')
    #print(encoding)
    C, varSet, permutation = aug.augmentCode(newSourcefile, changeVariable, permuteStatement , task)
    myprint("transformJavaCode: permutation: ", permutation)
    myprint("transformJavaCode: newCode: ", type(C), C)
    if permuteStatement:
        revPerm = reversePermutation(permutation)
        C =  permutateCode(C, revPerm)
    pyd.write_to_file(C, ".\\tmp.py") #save to normalized code dir

    C = genNewSeq (C, sol) # a lis of tokens
    if permuteStatement:
        C =  permutateCode(C, permutation)
    myprint("transformJavaCode: generated code: ", ' '.join(C))
    return C,permutation

def getTransformRule(newSourcefile, targetfile ):
    target = pyd.getJavaTokensFromFile(targetfile)
    source = pyd.getJavaTokensFromFile(newSourcefile)

    #print("target: ", target)
    #print("source: ", source)

    Locked = []   
    P, Act = matchPatch(source, target, Locked)
    sol = getSol(P, Act)  # get the transforming rule f=fro revised to syntax buggy code
    return sol

def myprint( *args):
        if (DEBUG):
                print(*args)

DEBUG=False
if __name__ == '__main__' :

#    A="this is a book but not good".split(' ')
#    B="this is an book a".split(' ')
#    Locked = ['a']
#    print("A: ", A)
#    print("B: ", B)

    targetfile = "Main9.java" #syntax buggy code
    newSourcefile = "Main9new.java" #revised code
    destfile="Main9newChanged.java" # variable renamed code

    sol = getTransformRule(newSourcefile, targetfile )
    myprint(sol)
    
    newcode =transformJavaCode( sol, newSourcefile, destfile, True )
    myprint(newcode)


                
                
                
  
