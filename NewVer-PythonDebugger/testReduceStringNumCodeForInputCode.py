import os
import python_debugger as pyd
import javalang
import re
from os import path
import nltk;

def parseSentence(x):	
    tokens=[]
    state="START"
    chrs=""
    for i in range(len(x)):
        if (ord(x[i])>255):
            inp="U"
        elif (ord(x[i])>=48 and ord(x[i])<=57):
            inp="D"
        else:
            inp="E"

        if state=="START":
            if inp=="D":
                state="NUMBER"
                tokens.append(x[i])
            elif inp=="E":
                state="ASCII"
                chrs=x[i]
            else:#U
                state="UNICODE"
                tokens.append(x[i])

        elif state=="ASCII":
            if inp=="D":
                state="NUMBER"
                tokens += nltk.word_tokenize(chrs)
                chrs=""
                tokens.append(x[i])			
            elif inp=="E":
                state="ASCII"
                chrs += x[i]
            else:#U
                state="UNICODE"
                tokens += nltk.word_tokenize(chrs)
                chrs=""
                tokens.append(x[i])

        elif state=="NUMBER":
            if inp=="D":
                state="NUMBER"
                tokens.append(x[i])
            elif inp=="E":
                state="ASCII"
                chrs=x[i]
            else:#U
                state="UNICODE"
                tokens.append(x[i])		

        elif state=="UNICODE":
            if inp=="D":
                state="NUMBER"
                tokens.append(x[i])
            elif inp=="E":
                state="ASCII"
                chrs=x[i]
            else:#U
                state="UNICODE"
                tokens.append(x[i])

    if len(chrs)>0:
        tokens += nltk.word_tokenize(chrs)
    return replaceTAGS(tokens)

def replaceTAGS(x):
    replace_sublist(x, ['<', 'NUM_INT', '>'], ["<NUM_INT>"])
    replace_sublist(x, ['<', 'NUM_FLOAT', '>'], ["<NUM_FLOAT>"])
    replace_sublist(x, ['<', 'STRING', '>'], ["<STRING>"])
    replace_sublist(x, ['<', 'BOC', '>'], ["<BOC>"])
    replace_sublist(x, ['<', 'EOC', '>'], ["<EOC>"])
    replace_sublist(x, ['<', 'BOTM', '>'], ["<BOTM>"])
    replace_sublist(x, ['<', 'BOT', '>'], ["<BOT>"])
    replace_sublist(x, ['<', 'EOT', '>'], ["<EOT>"])
    replace_sublist(x, ['<', 'BOM', '>'], ["<BOM>"])
    replace_sublist(x, ['<', 'EOM', '>'], ["<EOM>"])
    replace_sublist(x, ['<', 'EOTM', '>'], ["<EOTM>"])
    replace_sublist(x, ['<', 'CR', '>'], ["<CR>"])
    return x

def find_first_sublist(seq, sublist, start=0):
    length = len(sublist)
    for index in range(start, len(seq)):
        if seq[index:index+length] == sublist:
            return index, index+length

def replace_sublist(seq, sublist, replacement):
    length = len(replacement)
    index = 0
    for start, end in iter(lambda: find_first_sublist(seq, sublist, index), None):
        seq[start:end] = replacement
        index = start + length

        
def listdir_fullpath(d):
    return [f for f in os.listdir(d)]

def normalizeCode(sourcefileX):
    data=pyd.readJavaFile(sourcefileX)
    tree = javalang.parse.parse(data)
    parser = pyd.AST(tree)
    newCode,_, _ = parser.getAugmentedCode(changeVariable=False, permuStatement=False)
    return newCode

def saveFile(targetfilepath, data):
    with open(targetfilepath, "w", encoding='utf8') as text_file:
        text_file.write(data)
    
# this function will normalized the code and replace string and nums by the "<Literal>" token,
# and NEWLINE as the '<CR>' token
def reduceStringNum(sourcefile):
    data=pyd.readJavaFile(sourcefile)
    data = re.findall("<BOC>(.*?)<EOC>", data)
    #first, replace  <CR> to  make it a java code
    result = []
    for code in data:
        code = code.replace("<CR>","")    
        tree = javalang.parse.parse(code)
        parser = pyd.AST(tree)
        parser.reduceCode_StringNum() #reduce the code by replaing string and num as '<Literal>'
        newCode,_, _ = parser.getAugmentedCode(changeVariable=False, permuStatement=False)
        newCode = " ".join(newCode).replace(pyd.NEWLINE, '<CR>')
        tokens = parseSentence(newCode)
        tokens=["<BOC>"] + tokens + ["<EOC>"]
        result += tokens
    return result


def readcode(fname):
    with open(fname) as f:
        data = f.read()
        return data
    
if __name__ == '__main__' :
    print("starting ...")
    source_dir = "NormalizedCODES" #the source codes
    dest_dir = "ReducedCODES" #the source codes
    cases = listdir_fullpath(source_dir)
    for case in cases:
        sourcefiledir = source_dir + "\\" + case
        destfiledir = dest_dir + "\\" + case
        if path.exists(destfiledir): continue
        if not os.path.exists(destfiledir):
            os.mkdir(destfiledir)  #create dest case dir
        sourcefiles = listdir_fullpath(sourcefiledir)
        for sourcefile in sourcefiles:
            if not sourcefile.lower().endswith(".txt"): continue
            print(sourcefiledir+"\\"+sourcefile)
            destfile = destfiledir + "\\" + sourcefile
            #test reduce code by replcing string and num by <Literal>
            tokens = reduceStringNum(sourcefiledir+"\\"+sourcefile)
            data = " ".join(tokens)
            saveFile(destfile, data)
            
