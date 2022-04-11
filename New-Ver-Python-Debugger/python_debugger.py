import javalang
import ast
import random, string
import os
import chardet
import glob
import python_debugger as pyd
from chardet.universaldetector import UniversalDetector
import CG
import copy


detector = UniversalDetector()

priority = {
"=":1,"+=":1,"-=":1, "*=": 1, "/=":1,"%=": 1, 
"?": 2,  ":": 2,
"||": 3,
"&&": 4,
"|": 5,
"^": 6,
"&": 7,
"==": 8, "!=": 8,
"<": 9, "<=": 9, ">": 9, ">=": 9, "instanceof": 9,
"<<": 10, ">>": 10, ">>>": 10,
"+": 11, "-": 11,
"*": 12, "/": 12, "%": 12,
"!": 13, "~": 13,
"++": 14, "--": 14, 
"(": 15, ")": 15, "[": 15, "]": 15
}

def getPriority(c) :
	if c in priority.keys():
		return priority[c]
	else:
		return 0

    
NEWLINE = 	"abcdefgijkoooopppp" 	
symbols = {"{",  "}", ";", NEWLINE}

varList=list(string.ascii_letters)
varNameCandicates = [] # The candicate list for var names
for i in range(10):
    varNameCandicates = varNameCandicates +  [e+str(i) for e in varList] 
varNameCandicates= varNameCandicates + varList

def getEncoding(filename):
    detector = UniversalDetector()
    detector.reset()
    for line in open(filename,'rb'):
        detector.feed(line)
        if detector.done:break
    detector.close()
    #print("Encoding: ", detector.result.get('encoding'))
    if detector.result.get('encoding')!= "utf-8":
            return "big5"
    else:
            return (detector.result.get('encoding'))


def myreplace(data):
    return data.replace('\x8c','') .replace('\xa6', ' ').replace('\xa8', ' ').replace('\xd8', ' ').replace('\xdf', ' ').replace('\xe8', ' ').replace('\xad', ' ').replace('\xed', ' ').replace('\xaa', ' ').replace('\xe9', ' ').replace('\xc5', ' ').replace('\xa1', ' ').replace('\u05b5', ' ').replace('\u05d9', ' ').replace('\xbf', ' ').replace('\xac', ' ').replace('\xae', ' ').replace('\xee', ' ')
	
# Given a tokens to be written
def write_to_file(tokens, filename):
    fp = open(filename, 'w', encoding='utf8')

    for token in tokens:
        if (token !=  NEWLINE):
                fp.write(token + " ")
        else:
            fp.write('\n')

def printCode(code):
    tokens=getJavaTokensFromString(code)
    for token in tokens:
        if (token !=  NEWLINE):
                myprint(token + " ", end="")
        else:
            myprint('\n')		

#		if token in symbols:
#            myprint('\n')

def xx(filename):
    detector.reset()
    for line in open(filename, 'rb'):
        detector.feed(line)
        if detector.done: break
    detector.close()
    return( detector.result)

# read java code from file
# return the list of java tokens
def getJavaTokensFromFile(filename):
    encoding=xx(filename).get('encoding')
    #print(encoding)
    f = open(filename, 'r', encoding=encoding)
    A=f.read()
    A= list( javalang.tokenizer.tokenize(A) )
    A = [ e.value for e in A]
    return A

# read java code string A
# return the list of java tokens
def getJavaTokensFromString(A):
    A= list( javalang.tokenizer.tokenize(A) )
    A = [ e.value for e in A]
    return A

    
##
## The Tree to Java code here
## Return a string
##
#added 2021/07/30 Abstract Syntactic Tree (AST)
class AST:
        def __init__(self, tree, ShowLineNumber=False):
                self.tree = copy.deepcopy(tree) #Note, we copy a new tree, and do everything on it without changing original tree
                self.line_number = 0       #current available line number to be assigned
                self.ranks = {None:0}      #create line ids for each statement node
                self.ranksize = {None:0}   #create line size for each statement node
                self.ShowLineNumber=ShowLineNumber
                self.permute_dict = {}

        def __addNode(self,node): #set the line id of the statement node
                if not node  is None:
                        self.ranks[node] = self.line_number
                        self.line_number +=  1       #next available line id 

        def __setSize(self,node, size):  #set the line size of the statement node
                if not node is None and node in self.ranks.keys() :
                        self.ranksize[node] = size

        def getLineNumber(self, node): #return starting ln and size
                return self.ranks.get(node)
				
        def getLineSize(self, node): #return starting ln and size
                return self.ranksize.get(node)
        
        ###################
        # Transforming rule 2: Change level-1 orders
        ###################        
        def change_fun_order(self): #it will chane the tree order
            for _, cnode in self.tree.filter(javalang.tree.ClassDeclaration): #for each class node
                body = cnode.__getattribute__("body")
                if not body is None and len(body)>1: #collect feature nodes and MethodDeclaration nodes
                    random.shuffle(body)
                    cnode.__setattr__("body", body)
                '''
                tmp = self.__collectBodyLines(body)  #collect line ids of the body codes					
                #compute the permutation
                #print("tmp: ", tmp)
                total_lines = self.getLineSize(self.tree) -self.getLineNumber(self.tree) -1
                old_pvec = list(range(1,total_lines+1 )) #prepare the line ids of old code
                self.permute_dict  = self.__permutate(old_pvec, tmp)
                sort_orders = sorted(self.permute_dict.items(), key=lambda x: x[0], reverse=False)
                #print(" permute_dict:",  sort_orders  )
                '''
            return self.tree

        #old_pvec: line ids of old code, new_pvec: new orders line ids in permuted code
        def __permutate(self, old_pvec, new_pvec): 
                j=0
                pos={} #permutation as dict: old line ids-> new  line ids
                for i in range(len(old_pvec) ):                      
                    if old_pvec[i] in new_pvec:                           
                            elem = new_pvec[j]
                            j+=1
                            old_id = old_pvec.pop(i)
                            old_pvec.insert(i, elem)
                    else:
                            old_id=old_pvec[i]
                            elem = old_id
                    pos[elem]= old_id                
                                    
                return pos
        
        # do p1 and p2 and get another permutation
        def __doPermutation(p1, p2):
                res = {}
                for k1, v1 in p1.items():
                        res[k1]=p2[v1]
                return res
        
        # concatenate two disjoint permutations
        def __mergePermutation(p1, p2):
                return {**p1, **p2}
        
        def getPermutation(self): 
                return self.permute_dict
        
        #do code augmentation
        #task:1 change_fun_order()
        #task:2 full permutation
        def getAugmentedCode(self, task=1, changeVariable=True, permuStatement=True,variableMap = None):
            self.toJava()  # normalize the code and get line counts
            if permuStatement: 
                if task==1:
                        self.tree = self.change_fun_order() #Change the  order in level one
                else:
                        self.tree = self.cdPermuteCode()  #Change the  order in level one
                #get permutation mask
                total_lines = self.getLineSize(self.tree) -self.getLineNumber(self.tree) -1
                old_pvec = list(range(1,total_lines+1 )) #prepare the line ids of old code
                new_p = self.__collectCodeLines(self.tree)
                myprint("old_pvec: ", old_pvec)
                myprint("new_p: ", new_p)
                self.permute_dict  = self.__permutate(old_pvec, new_p)
                sort_orders = sorted(self.permute_dict.items(), key=lambda x: x[0], reverse=False)
                #print("self.permute_dict in getAugmentedCode: ", sort_orders)

            if changeVariable: 
                if(variableMap is None):
                    newCode,variableMap = self.change_variable() #a list of tokens
                else:
                    newCode,variableMap = self.change_variable(variableMap)
            else:
                newCode = self.toJava()	# Rank again to get the new line ids
                newCode=getJavaTokensFromString(newCode) # Get the new code tokens

            return (newCode,variableMap, self.permute_dict)
      
        # gen permuted codes using the computation diagram method
        def cdPermuteCode(self):
            cg = CG.CG(self.tree) #eatablish a CG object
            cg.createGraph(self.tree) #recursively construct the CG graph    
            CG.genCDpermutations(cg) #recursively construct the CG permutations
            #cg.display(1) #level 1 indented
            CG.changeTreePermutation(cg) # change the tree according to cg        
            #newCode = pyd.toJava(tree) #  string 
            return self.tree 


        ###################
        # Transforming rule 1: local variable name changing
        ###################        
        def change_variable(self, varMap = None):
            NoPassedVarMap = (varMap == None)
            #processing vars in each function
            mnodeList =  list()
            thisList =  list()
            for _, thisnode in self.tree.filter(javalang.tree.This):
                mns = thisnode.__getattribute__("selectors")
                for th in mns:  
                    thisList.append(th)

            for _, mnode in self.tree.filter(javalang.tree.MethodDeclaration):
                mnodeList.append(mnode)
            for _, mnode in self.tree.filter( javalang.tree.ConstructorDeclaration):
                mnodeList.append(mnode)
            for mnode in mnodeList:
                #print("Start change names mnode..................")
                declareVars = list()
                referencedVars = list()
                referencedQuaVars=list()
                usedVarNames = set()
                
                for _, node in mnode.filter(javalang.tree.VariableDeclaration): #for variable 
                    #print('$$$$$$$$$$$$$$$$$$$',node)
                    declarators = node.__getattribute__("declarators")
                    for vnode in declarators:
                        #print('$$$$$$$$$$$$$$$$$$$',vnode.__getattribute__("name"))
                        usedVarNames.add( vnode.__getattribute__("name") )
                        declareVars.append(vnode)
                
                for _, node in mnode.filter(javalang.tree.FormalParameter): #arguments
                    #print("!_@)#_",node.__getattribute__("name"))
                    usedVarNames.add( node.__getattribute__("name") )
                    declareVars.append(node)
         
                #api--------------------------------------------------------------------        
                for _, mrnode in mnode.filter(javalang.tree.MemberReference):
                    if not mrnode in thisList:
                        mv = mrnode.__getattribute__("member")
                        qualifier = mrnode.__getattribute__("qualifier")
                        if(mv in usedVarNames):
                            referencedVars.append(mrnode)
                        if  not qualifier is None: # a pure local variable, not member variable
                            referencedQuaVars.append(mrnode)                            
                for _,qunode in mnode.filter(javalang.tree.MethodInvocation):
                    qu = qunode.__getattribute__("qualifier")
                    if(qu in usedVarNames):
                        referencedQuaVars.append(qunode)
                for _,ldnode in mnode.filter(javalang.tree.LocalVariableDeclaration):
                    for _,vanode in mnode.filter(javalang.tree.VariableDeclarator):
                        ini = vanode.__getattribute__("initializer")
                        if(hasattr(ini,"qualifier")):
                            va = ini.__getattribute__("qualifier")
                            if(va in usedVarNames):
                                referencedQuaVars.append(ini)
                                  
                for _,ldnode in mnode.filter(javalang.tree.VariableDeclaration):
                    for _,vanode in mnode.filter(javalang.tree.VariableDeclarator):
                        ini = vanode.__getattribute__("initializer")
                        if(hasattr(ini,"qualifier")):
                            va = ini.__getattribute__("qualifier")
                            if(va in usedVarNames):
                                referencedQuaVars.append(ini)
               
            #Start change names  
                count = len(usedVarNames)
                if(varMap is None):
                    alreadyVarNames =  []
                else:
                    alreadyVarNames  =  varMap.values()#program X's change_variable
                #print("------------------------------ ",alreadyVarNames)  
                cc = [item for item in varNameCandicates if item not in alreadyVarNames]
                random.shuffle(cc)
                newVarNames = cc[:count]#program Y's change_variable

                varMap2 = dict(zip(usedVarNames, newVarNames))#program Y's variable mapping

                if(varMap is None):
                    varMap = varMap2
                else:
                    for k,v in varMap2.items():
                        if(k not in varMap.keys()):
                            varMap[k] = v         
                #Vars    
                for node in declareVars:
                    node.__setattr__("name", varMap[node.__getattribute__("name")])
                    
                for node in referencedVars:            
                    node.__setattr__("member", varMap.get(node.__getattribute__("member"), node.__getattribute__("member")))

                for node in referencedQuaVars:
                         node.__setattr__("qualifier", varMap.get(node.__getattribute__("qualifier"), node.__getattribute__("qualifier")))   
            tokens = self.toJava()    
            code_tokens= getJavaTokensFromString(tokens)
            
            return (code_tokens,varMap)
			
        def __isfloat(self, value):
          try:
            float(value)
            return True
          except ValueError:
            return False			
#add a new function reduceCode_StringNum(tree)
#changing any string literal as <STRING> token
#changing any numeric literal as <NUM_INT> or <MUM_FLOAT> token
        def reduceCode_StringNum(self):
            for _, thisnode in self.tree.filter(javalang.tree.Literal):
                x = thisnode.__getattribute__("value")
                if x.isdigit() :
                    setattr(thisnode,"value", "<NUM_INT>")
                elif self.__isfloat(x) :
                    setattr(thisnode,"value", "<NUM_FLOAT>")
                else:
                    setattr(thisnode,"value", "<STRING>")
				
            return self.tree

                                     
        def toJava(self): #It will rank the tree (generate line ids and line size)
                myprint("self.tree",self.tree)
                self.line_number = 0       #current available line number to be assigned
                self.ranks = {None:0}      #create line ids for each statement node
                self.ranksize = {None:0}   #create line size for each statement node
                res = self.__toJava2(self.tree)
                #print("Total lines: ", self.getLineSize(self.tree)-1)
                return res
                
        def __toJava2(self, node):
            if node is None: return ""
            result = ""
            #print("type(node).__name__: ", type(node).__name__)
            if type(node).__name__ == "BlockStatement":
                myprint("type(node).__name__: ", type(node).__name__)
                self.__addNode(node) #because it is already added by its main element like for
                statements = node.__getattribute__("statements")
                result += "{ " + NEWLINE + " "
                result += " ".join( [self.__toJava2(sta) for sta in statements] )
                result += "}" + NEWLINE + " "
                myprint("BlockStatement: ",result)
                self.line_number += 1
                self.__setSize(node, self.line_number - self.getLineNumber(node) )
                myprint("BlockStatement Size: ",self.line_number - self.getLineNumber(node))
				
            elif type(node).__name__ == "CatchClauseParameter":
                name = node.__getattribute__("name") # 
                types = node.__getattribute__("types") # None or list
                if not types is None:
                    result += " ".join( e for e in types) + " " + name
          
            elif type(node).__name__ == "CatchClause":
                block = node.__getattribute__("block") # None or list
                parameter = node.__getattribute__("parameter")  # None or object
                self.__addNode(node)
                result += " catch ( "
                if not parameter is None:
                    result += self.__toJava2(parameter)
                result += " )  { "   + NEWLINE + " " 
				#self.line_number =self.line_number + 1
                if not block is None:
                   result +=  " ".join( [ self.__toJava2(b) for b in block] )
                result += " }  " + NEWLINE + " " 
                self.line_number += 1
                self.__setSize(node, self.line_number - self.getLineNumber(node) )				
				
            elif type(node).__name__ == "ExplicitConstructorInvocation":
                arguments = node.__getattribute__("arguments") # None or list
                result += "this("
                if not arguments is None:
                   result +=  ",".join( [ self.__toJava2(arg) for arg in arguments] )
                result += ")"
                    
            elif type(node).__name__ == "TryStatement":
                block = node.__getattribute__("block") # None or list
                catches = node.__getattribute__("catches")  # None or list
                finally_block = node.__getattribute__("finally_block")  # None or list
                self.__addNode(node)
                result += "try " + " { " + NEWLINE + " "
                if not block is None:
                   result +=  " ".join( [ self.__toJava2(b) for b in block] )
                result += " } " + NEWLINE + " "
                self.line_number += 1

                if not catches is None:
                   result += " ".join( [ self.__toJava2(c) for c in catches] )
                if not finally_block is None:
                   result += "finally { " + NEWLINE + " "
                   self.line_number += 1
                   result += " ".join( [ self.__toJava2(f) for f in finally_block] )
                   result += " } " + NEWLINE + " "
                   self.line_number += 1
                self.__setSize(node, self.line_number - self.getLineNumber(node) )
                
            elif type(node).__name__ == "BreakStatement":
                self.__addNode(node)
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                result += "break ;"+ NEWLINE + " "
                self.__setSize(node, self.line_number - self.getLineNumber(node) )
                
            elif type(node).__name__ == "This":
                postfix_operators = node.__getattribute__("postfix_operators")
                prefix_operators = node.__getattribute__("prefix_operators")
                selectors = node.__getattribute__("selectors")
                if not prefix_operators is None and len(prefix_operators)>0:
                    result += " ".join([e for e in prefix_operators] )
                    
                result += "this"
                if not selectors is None and len(selectors)>0:
                    for e in selectors:
                        if type(e).__name__ != "ArraySelector":
                            result += "."+self.__toJava2(e)
                        else:
                            result += self.__toJava2(e)

                if not postfix_operators is None and len(postfix_operators)>0:
                    result += " ".join([e for e in postfix_operators] )
                
            elif type(node).__name__ == "SuperConstructorInvocation":
                #self.__addNode(node)
                arguments = node.__getattribute__("arguments")
                result += "super("
                if not arguments  is None and len(arguments)>0 :
                    result += ",".join([self.__toJava2(e) for e in arguments] )
                result += ")" 

            elif type(node).__name__ == "ArraySelector":
                index = node.__getattribute__("index")
                result += " [" + self.__toJava2(index) + "] " 

              
            elif type(node).__name__ == "Cast":
                expression = node.__getattribute__("expression")
                ttype = node.__getattribute__("type")
                
                result += "( " + self.__toJava2(ttype) + " ) "
                opr=self.__toJava2(expression)
                if len(list(javalang.tokenizer.tokenize(opr ))) > 1:
                    result += " (" + opr + ") "
                else:
                     result += opr
                        
            elif type(node).__name__ == "ForControl":
                condition = node.__getattribute__("condition")
                init = node.__getattribute__("init") # init may be object of list
                update = node.__getattribute__("update")
                if (type(init) is list):
                    result += "( " + ",".join( [ self.__toJava2(e) for e in init ] ) +";" + self.__toJava2(condition) + ";"
                else:
                    result += "( " + self.__toJava2(init) +";" + self.__toJava2(condition) + ";" 
                result+= ",".join( [self.__toJava2(upd) for upd in update])        
                result += ") "

            elif type(node).__name__ == "TernaryExpression":
                condition = node.__getattribute__("condition")
                if_false = node.__getattribute__("if_false")
                if_true = node.__getattribute__("if_true")
                result += self.__toJava2(condition) + " ? " + self.__toJava2(if_true)
                if not if_false is None:
                    result += " : " + self.__toJava2(if_false)
					
            elif type(node).__name__ == "IfStatement":
                self.__addNode(node) #create line number for if () {
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                condition = node.__getattribute__("condition")
                else_statement = node.__getattribute__("else_statement")
                then_statement = node.__getattribute__("then_statement")
                if type(then_statement).__name__ == "BlockStatement":
                    myprint("beging process BLOCK then_statement at position:", self.line_number )
                    myprint("beging process BLOCK then_statement  type is: ", type(then_statement).__name__  )
                    self.line_number -= 1
                    result += "if (" + self.__toJava2(condition) + ") " + self.__toJava2(then_statement)
                    myprint("after process BLOCK then_statement at position:", self.line_number )
                    myprint("after process BLOCK then_statement at position: result is: ", result )
                else: # "StatementExpression" or type(then_statement).__name__ == "ReturnStatement" or type(then_statement).__name__ == "BreakStatement" or type(then_statement).__name__ == "ContinueStatement":
                    result += "if (" + self.__toJava2(condition) + ") " + NEWLINE + " "
                    myprint("begin process then_statement (StatementExpression) position:", self.line_number )
                    result += self.__toJava2(then_statement)
                    myprint("after process then_statement position:", self.line_number )
                    #self.line_number += 1 #????                   
                if not else_statement is None:
                    if self.ShowLineNumber:
                            result += str(self.line_number) + "     "  

                    if type(else_statement).__name__ == "IfStatement":   # IfStatement
                            result += " else "   
                            myprint("begin process else_statement (IfStatement) at position:", self.line_number )
                            result += self.__toJava2(else_statement)
                            myprint("after process else_statement(IfStatement) at position:", self.line_number )
                            #self.line_number += 1  # }
                    elif type(else_statement).__name__ ==  "BlockStatement":
                            result += " else "  
                            #self.line_number += 1  
                            result += self.__toJava2(else_statement)
                    else:# "StatementExpression" or "ReturnStatement" or  "BreakStatement" or  "ContinueStatement":
                            result += " else " + NEWLINE + " "
                            self.line_number += 1
                            myprint("begin process else_statement position:", self.line_number )
                            result += self.__toJava2(else_statement)
                            myprint("after process else_statement position:", self.line_number )
                myprint("IF Statement Size:",self.line_number,"-",self.getLineNumber(node), "=", ( self.line_number - self.getLineNumber(node)) )
                            
                self.__setSize(node, self.line_number - self.getLineNumber(node) )
                myprint("IF Statement result:", result)					
            elif type(node).__name__ == "SwitchStatementCase":
                case = node.__getattribute__("case")
                statements = node.__getattribute__("statements")
                myprint("start processing SwitchStatementCase  at pos:", self.line_number )  # 6   
                self.__addNode(node)
                self.line_number -= 1
                if len(case) > 0:
                    for c in case: #Literal
                        result +=  "case " + self.__toJava2(c) + " : "+ NEWLINE + " "  #6, 7
                        #myprint("after: self.line_number: ", self.line_number)
                        self.line_number += 1

                else: #it's default case
                    result += "default " + self.__toJava2(case) + ": " + NEWLINE + " "                  
                    self.line_number += 1

                #now , line is 8
                myprint("start processing statements SwitchStatementCase  at pos:", self.line_number )  # 8
                for statement in statements:
                        result += self.__toJava2(statement)
                        myprint("next  statements SwitchStatementCase  at pos:", self.line_number )  
                        
                self.__setSize(node, self.line_number - self.getLineNumber(node) )

            elif type(node).__name__ == "SwitchStatement":
                cases = node.__getattribute__("cases")
                expression = node.__getattribute__("expression")
                myprint("processing SwitchStatement at pos:", self.line_number )    #5
                self.__addNode(node)
                result += "switch (" + self.__toJava2(expression) + ") { " + NEWLINE + " "
                myprint("processing SwitchStatement case structire at pos:", self.line_number )  #6                  
                result+= " ".join( [ self.__toJava2(case) for case in cases] )
                result += " } "+ NEWLINE + " "
                self.line_number += 1
                self.__setSize(node, self.line_number - self.getLineNumber(node) )

            elif type(node).__name__ == "WhileStatement":
                condition = node.__getattribute__("condition")
                body = node.__getattribute__("body")
                self.__addNode(node)
                if type(body).__name__ == "StatementExpression":
                    result += "while (" + self.__toJava2(condition) + ") " + NEWLINE + " "
                    myprint("begin process while body statement position:", self.line_number )
                    result += self.__toJava2(body)
                    myprint("after process while body statement position:", self.line_number )
                else: #BlockStatement
                    self.line_number=self.line_number-1 #newly added because next BS will add one line
                    result += "while (" + self.__toJava2(condition) + ") " +  self.__toJava2(body)
               
                self.__setSize(node, self.line_number - self.getLineNumber(node) )

            elif type(node).__name__ == "DoStatement":
                condition = node.__getattribute__("condition")
                body = node.__getattribute__("body")
                self.__addNode(node)                
                #body only BlockStatement
                result += "do " + " "
                self.line_number -= 1
                result += self.__toJava2(body) + " while ( " + self.__toJava2(condition) + " ) ; "+ NEWLINE + " "
                self.line_number += 1
                myprint("DoWhle Statement Size:",self.line_number,"-",self.getLineNumber(node), "=", ( self.line_number - self.getLineNumber(node)) )
                self.__setSize(node, self.line_number - self.getLineNumber(node) )				
				
            elif type(node).__name__ == "ForStatement":
                body = node.__getattribute__("body")
                control = node.__getattribute__("control")
                self.__addNode(node)				                
                if type(body).__name__ == "StatementExpression":
                    result += "for " + self.__toJava2(control) + NEWLINE + " "
                    myprint("begin process for body statement position:", self.line_number )
                    result += self.__toJava2(body)
                    myprint("after process for body statement position:", self.line_number )
                else: #BlockStatement
                    self.line_number=self.line_number-1 #newly added because next BS will add one line
                    result += "for " + self.__toJava2(control) +  self.__toJava2(body)

                myprint("For Statement Size:",self.line_number,"-",self.getLineNumber(node), "=", ( self.line_number - self.getLineNumber(node)) )
                self.__setSize(node, self.line_number - self.getLineNumber(node) )				
				
            elif type(node).__name__ == "EnhancedForControl":
                iterable = node.__getattribute__("iterable")
                var = node.__getattribute__("var")
                result += " ( "+ self.__toJava2(var) + " : "+	self.__toJava2(iterable)	+" ) "

            elif type(node).__name__ == "ContinueStatement":
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                result += " continue ; " + NEWLINE + " "
                self.__setSize(node, self.line_number - self.getLineNumber(node) )
                
            elif type(node).__name__ == "ReturnStatement":
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                expression = node.__getattribute__("expression")
                result += "return" + " "
                result += self.__toJava2(expression) + " "
                result += ";"+ NEWLINE + " "
                myprint("ReturnStatement: value type: ",type(expression).__name__)
                myprint("ReturnStatement: size ",self.line_number - self.getLineNumber(node))
                self.__setSize(node, self.line_number - self.getLineNumber(node) )				
				
            elif type(node).__name__ == "ConstructorDeclaration": # constructor XXX()
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                classname = node.__getattribute__("name")
                parameters = node.__getattribute__("parameters")
                modifiers = node.__getattribute__("modifiers")
                body = node.__getattribute__("body")
                for mod in sorted(modifiers):
                    result += mod + " "
                result += classname + "("
                if not parameters is None:
                    result +=  ",".join( [self.__toJava2(parameter) for parameter in parameters])
                result += ") { " +  NEWLINE + " "
                if not body is None:
                    result +=  " ".join( [self.__toJava2(bd) for bd in body])
                result += " } " + NEWLINE + " "
                self.line_number += 1
                self.__setSize(node, self.line_number - self.getLineNumber(node) )				
				
            elif type(node).__name__ == "ClassCreator": # new XXX()
                reftype = node.__getattribute__("type")
                arguments = node.__getattribute__("arguments")
                selectors = node.__getattribute__("selectors")
                body = node.__getattribute__("body")
                result += 'new ' + self.__toJava2(reftype) + "( "
                if not arguments is None:
                    result +=  ",".join( [self.__toJava2(arg) for arg in arguments])            
                result += ")"
                if not selectors is None and len(selectors)>0:
                    for selector in selectors:
                        result += "."+ self.__toJava2(selector)
                if not body is None:
                    result +=  " { " +  NEWLINE + " "
                    self.line_number += 1
                    result +=" ".join( [self.__toJava2(bd) for bd in body])
                    result += " } " +  NEWLINE + " "
                    self.line_number += 1

            elif type(node).__name__ == "StatementExpression":
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                expression = node.__getattribute__("expression")
                result += self.__toJava2(expression) + " ; " +  NEWLINE + " "				
                self.__setSize(node, self.line_number - self.getLineNumber(node) )
                
            elif type(node).__name__ == "Assignment":       
                left_expression = node.__getattribute__("expressionl")
                assigment_type = node.__getattribute__("type") # =
                right_expression = node.__getattribute__("value")      
                result += self.__toJava2(left_expression) + " " + assigment_type + " " + self.__toJava2(right_expression) 

            elif type(node).__name__ == "VariableDeclaration":
                tty = node.__getattribute__("type")
                declarators = node.__getattribute__("declarators")
                result += self.__toJava2(tty) + " "
                result += ",".join([self.__toJava2(dec) for dec in declarators ])
                
           
            elif type(node).__name__ == "LocalVariableDeclaration":
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                tty = node.__getattribute__("type")
                declarators = node.__getattribute__("declarators")
                modifiers= node.__getattribute__("modifiers")
                if not modifiers is None:
                   result += ' '.join( [e for e in modifiers] ) + " "
                result += self.__toJava2(tty) + " "
                result += ",".join([self.__toJava2(dec) for dec in declarators ]) + " ; " + NEWLINE + " " 
                self.__setSize(node, self.line_number - self.getLineNumber(node) )

            elif type(node).__name__ == "ArrayInitializer":
                initializers = node.__getattribute__("initializers")
                result += "{ " 
                if not initializers is None and len(initializers)>0:
                    result += ",".join(self.__toJava2(e) for e in initializers) 
                result += "} "

            elif type(node).__name__ == "ArrayCreator":
                dimensions = node.__getattribute__("dimensions")
                initializer = node.__getattribute__("initializer")
                tty = node.__getattribute__("type")
                result += "new " + self.__toJava2(tty)
                if not dimensions is None and len(dimensions)>0:
                    for e in dimensions:
                        dim=""
                        if e is not None: dim = self.__toJava2(e)
                        result += " [ " +  dim + "]"
                result += self.__toJava2(initializer)          

            elif type(node).__name__ == "VariableDeclarator":
                dimensions = node.__getattribute__("dimensions")
                name = node.__getattribute__("name")
                initialize = node.__getattribute__("initializer")
                result += name
                if not dimensions is None and len(dimensions)>0:
                    for e in dimensions:
                        dim = ""
                        if e is not None: dim = self.__toJava2(e)
                        result += " [ " + dim + " ] "

                if not initialize is None:
                    result += "=" + " "
                    result += self.__toJava2(initialize) + " "


            elif type(node).__name__ == "MethodInvocation":
                arguments = node.__getattribute__("arguments")
                member = node.__getattribute__("member")
                qualifier=node.__getattribute__("qualifier")
                selectors=node.__getattribute__("selectors")
                postfix_operators=node.__getattribute__("postfix_operators")
                prefix_operators=node.__getattribute__("prefix_operators")
                
                if (not qualifier is None) and (qualifier !=""):
                    result += qualifier + "." + member + " "
                else:
                    result +=member + " "
                result += "(" + " "
                if not arguments is None:
                    result +=  ",".join( [self.__toJava2(arg) for arg in arguments])
            
                result += ")" + " "
                
                if not selectors is None and len(selectors)>0:
                    result += '.'+ ' '.join( [ self.__toJava2(sel) for sel in selectors])
                    
                if not  prefix_operators is None and len(prefix_operators)>0: 
                    result =  ' '.join(prefix_operators)+ result

                if not  postfix_operators is None and len(postfix_operators)>0: 
                    result +=  ' '.join(postfix_operators)

            elif type(node).__name__ == "FormalParameter":
                tty = node.__getattribute__("type")
                name = node.__getattribute__("name")
                result += self.__toJava2(tty) + " "
                result += name + " "
            elif type(node).__name__ == "BasicType":
                dimensions = node.__getattribute__("dimensions")
                name = node.__getattribute__("name")
                result += name + " "

                if not dimensions is None:
                    for dim in dimensions:
                        if dim is None:
                            result += "[]" + " "
                        else:
                            result += "[" + " "
                            result += dim + " "
                            result += "]" + " "

            elif type(node).__name__ == "TypeArgument":
                tty = node.__getattribute__("type")
                result += self.__toJava2(tty)
                
            elif type(node).__name__ == "ReferenceType":
                name = node.__getattribute__("name")
                dimensions = node.__getattribute__("dimensions")
                arguments = node.__getattribute__("arguments")
                sub_type= node.__getattribute__("sub_type")
                result += name
                if not sub_type is None:
                   result +=  "." + self.__toJava2(sub_type)
                if not arguments is None:
                   result +=  "<" +  ",".join( [ self.__toJava2(arg) for arg in arguments]) + "> " 
                if not dimensions is None:
                    for dim in dimensions:
                        if dim is None:
                            result += "[]" + " "
                        else:
                            result += "[" + " "
                            result += dim + " "
                            result += "]" + " "

            elif type(node).__name__ == "BinaryOperation":
                stack=[]			
                # myprint("into binary operation")
                operandl = node.__getattribute__("operandl")
                operandr = node.__getattribute__("operandr")
                operator = node.__getattribute__("operator")
                stack.append(operator)
                #print(stack)
                # myprint(operator)
                opl = self.__toJava2(operandl)
                '''
                if len(list(javalang.tokenizer.tokenize(opl))) > 1 and operator != "+" and operator != "-":
                    opl = '(' + opl + ')'
                '''			
                opr = self.__toJava2(operandr)
                '''
                if len(list(javalang.tokenizer.tokenize(opr))) > 1 and operator != "+":
                    opr = '(' + opr + ')'
                '''	
                stack.pop()  
                parent_op=None		
                if len(stack)>0:
                    parent_op = stack[-1]	
                currentPriority = getPriority(operator)
                parrentPriority	= getPriority(parent_op)
                if 	currentPriority < parrentPriority :
                    result += "(" + opl + " "
                    result += operator + " "      
                    result +=  opr + ")"
                else:
                    result +=  opl + " "
                    result += operator + " "      
                    result +=  opr 
                        
            elif type(node).__name__ == "Literal":
                value = node.__getattribute__("value")
                postfix_operators = node.__getattribute__("postfix_operators")
                prefix_operators = node.__getattribute__("prefix_operators")
                if not  prefix_operators is None and len(prefix_operators)>0: 
                    result =  ' '.join(prefix_operators)+result
                   
                result += value + " "
                    
                if not  postfix_operators is None and len(postfix_operators)>0: 
                    result +=   ' '.join(postfix_operators)

                
            elif type(node).__name__ == "MemberReference":
                member = node.__getattribute__("member")
                postfix_operators = node.__getattribute__("postfix_operators")
                prefix_operators = node.__getattribute__("prefix_operators")
                qualifier = node.__getattribute__("qualifier")
                selectors = node.__getattribute__("selectors")
                if (not qualifier is None) and (qualifier!=""):
                    result += qualifier +"."
                if not  prefix_operators is None and len(prefix_operators)>0: 
                    result =  ' '.join(prefix_operators)+result
                   
                result += member
                if not selectors is None:
                    result += '.'.join( [ self.__toJava2(sel) for sel in selectors])
                    
                if not  postfix_operators is None and len(postfix_operators)>0: 
                    result +=   ' '.join(postfix_operators)
                    

            elif type(node).__name__ == "CompilationUnit":
                imports = node.__getattribute__("imports")
                package = node.__getattribute__("package")
                types = node.__getattribute__("types")
                self.__addNode(node) #create line number

                if not package is None:
                    result += self.__toJava2(package) + " "
                if not imports is None:
                    for imp in imports:
                        result += self.__toJava2(imp) + " "
                if not types is None:
                    for ty in types:
                        result += self.__toJava2(ty) + " "
                self.__setSize(node, self.line_number - self.getLineNumber(node) )

            elif type(node).__name__ == "PackageDeclaration":
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                annotations = node.__getattribute__("annotations")
                documentation = node.__getattribute__("documentation")
                modifiers = node.__getattribute__("modifiers")
                name = node.__getattribute__("name")
                if not name is None:
                    result += "package" + " "
                    result += name + " "
                    result += ";" + NEWLINE + " " 
                self.__setSize(node, self.line_number - self.getLineNumber(node) )

            elif type(node).__name__ == "Import":
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                path = node.__getattribute__("path")
                static = node.__getattribute__("static")
                wildcard = node.__getattribute__("wildcard")
                if not path is None:
                    result += "import" + " "
                    if static is True:
                        result += "static" + " "
         
                    xx = lambda w: path + ".*" if w else path
                    result += xx(wildcard) + " "
                    result += ";" + " " + NEWLINE + " "
                self.__setSize(node, self.line_number - self.getLineNumber(node) )

            elif type(node).__name__ == "InterfaceDeclaration":
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                annotations = node.__getattribute__("annotations")
                body = node.__getattribute__("body")
                extends = node.__getattribute__("extends")
                modifiers = node.__getattribute__("modifiers")
                name = node.__getattribute__("name")
                type_parameters = node.__getattribute__("type_parameters")
                documentation = node.__getattribute__("documentation")
                if not modifiers is None:
                    result += ' '.join( sorted(modifiers)) + " interface" + " "
                if not name is None:
                    result += name + " "
                if not extends  is None:
                    result += " extends "
                    for e in extends:
                           result += self.__toJava2(e) + " "
                result += "{" + " " + NEWLINE + " "
                if not body is None:
                    for bd in body:
                        if type(bd) is list and len(bd)>0:
                            result += " { " + " " + NEWLINE + " "
                            self.line_number += 1
                            for sbd in bd:
                                result += self.__toJava2(sbd) + " "
                            result += " } " + " " + NEWLINE + " "
                            self.line_number += 1
 							
                        else:    
                         result += self.__toJava2(bd) + "  "
                result += " }" + " " + NEWLINE + " "
                self.line_number += 1
                self.__setSize(node, self.line_number - self.getLineNumber(node) )

            elif type(node).__name__ == "ClassDeclaration":
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                annotations = node.__getattribute__("annotations")
                body = node.__getattribute__("body")
                documentation = node.__getattribute__("documentation")
                extends = node.__getattribute__("extends")
                implements = node.__getattribute__("implements")
                modifiers = node.__getattribute__("modifiers")
                name = node.__getattribute__("name")
                type_parameters = node.__getattribute__("type_parameters")
                if not modifiers is None:
                    result += ' '.join( sorted(modifiers)) + " class" + " "
                if not name is None:
                    result += name + " "
                if not extends  is None:
                    result += " extends " + self.__toJava2(extends) + " "
                if not implements  is None:
                    result += " implements " + " , ".join(self.__toJava2(imp) for imp in implements) + " "   
                result += "{" + " "+ NEWLINE + " "
                if not body is None:
                    for bd in body:
                        myprint("__toJava2: bd name: ",type(bd).__name__ )					
                        if type(bd) is list and len(bd)>0:
                            result += " { "+ " "+ NEWLINE + " "
                            self.line_number += 1
                            for sbd in bd:
                                result += self.__toJava2(sbd) + " "
                            result += " } "+ " "+ NEWLINE + " "
                            self.line_number += 1
                            myprint("__toJava2: result: ", result)						
                        else:    
                         result += self.__toJava2(bd) + " "
                result += " }"+ " "+ NEWLINE + " "
                self.line_number += 1			
                self.__setSize(node, self.line_number - self.getLineNumber(node) )
                
            elif type(node).__name__ == "ConstantDeclaration":
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                annotations = node.__getattribute__("annotations")
                declarators = node.__getattribute__("declarators")
                documentation = node.__getattribute__("documentation")
                modifiers = node.__getattribute__("modifiers")
                tty = node.__getattribute__("type")

                if not modifiers is None:
                    for modifier in sorted(modifiers):
                        result += modifier + " "
                        # result.append(modifier)
                if not tty is None:
                    result += self.__toJava2(tty) + " "
                if not declarators is None:
                    result += ",".join( [self.__toJava2(declarator) for declarator in declarators] )
                    
                    result += ";" + " " + NEWLINE + " " 
                self.__setSize(node, self.line_number - self.getLineNumber(node) )
					
 				
            elif type(node).__name__ == "FieldDeclaration":
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                annotations = node.__getattribute__("annotations")
                declarators = node.__getattribute__("declarators")
                documentation = node.__getattribute__("documentation")
                modifiers = node.__getattribute__("modifiers")
                tty = node.__getattribute__("type")

                if not modifiers is None:
                    for modifier in sorted(modifiers):
                        result += modifier + " "
                        # result.append(modifier)
                if not tty is None:
                    result += self.__toJava2(tty) + " "
                if not declarators is None:
                    result += ",".join( [self.__toJava2(declarator) for declarator in declarators] )
                    
                    result += ";" + " " + NEWLINE + " " 
                self.__setSize(node, self.line_number - self.getLineNumber(node) )
					
            elif type(node).__name__ == "Annotation":
                self.__addNode(node) #create line number    
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                element = node.__getattribute__("element")
                name = node.__getattribute__("name")	
                result += "@"+name+" " + NEWLINE + " "
                self.__setSize(node, self.line_number - self.getLineNumber(node) )

            elif type(node).__name__ == "MethodDeclaration":
                self.__addNode(node) #create line number
                if self.ShowLineNumber:
                   result = str(self.getLineNumber(node)) + "     "
                annotations = node.__getattribute__("annotations")
                body = node.__getattribute__("body")
                documentation = node.__getattribute__("documentation")
                parameters = node.__getattribute__("parameters")
                return_type = node.__getattribute__("return_type")
                modifiers = node.__getattribute__("modifiers")
                name = node.__getattribute__("name")
                throws = node.__getattribute__("throws")
                type_parameters = node.__getattribute__("type_parameters")
                abstract=False
                for ann in annotations:
                    result +=self.__toJava2(ann) 
                if not modifiers is None:
                    for modifier in sorted( modifiers):
                        if  modifier=="abstract":  abstract=True
                        result += modifier + " "
                        # result.append(modifier)
                if return_type is None:
                    result += "void" + " "
                    # result.append("void")
                else:
                    result += return_type.__getattribute__("name") + " "
                    # result.append(return_type.__getattribute__("name"))
                if not name is None:
                    result += name + " "
                    result += "(" + " "
                    # result.append(name)
                    # result.append("(")
                    if not parameters is None:
                        result +=  ",".join( [self.__toJava2(parameter) for parameter in parameters])            
                    result += ") " + " "
                    if not throws is None and len(throws)>0:
                        result += " throws " + ",".join(throws)    
                    # result.append(") {")
                if not abstract :
                    if not body is None:
                        result +=  " { " + NEWLINE + " "
                        #self.line_number += 1 ##newly added
                        result += " ".join( [self.__toJava2(bd) for bd in body]) + " } " + NEWLINE + " "
                        self.line_number += 1
                    else:
                        result += " ; "+ NEWLINE + " "
                else:
                    result += " ; "+ NEWLINE + " "
                self.__setSize(node, self.line_number - self.getLineNumber(node) )
					
            return result

        def __updatePCRel(self, PCRel,  result, cur_line):
                if not PCRel is None:
                        for child_id in result:
                                if child_id == cur_line: continue
                                if not child_id in PCRel.keys():
                                        PCRel[child_id] = cur_line  

                
        ## collect the [line ids,.....] in the permuted tree
        ## PCRel: Parent-Child Relations,a dict with keys representing child ids, and values representing parent id
        def collectCodeLines(self, PCRel=None):
                #print("self.line_number:", self.line_number)
                if len(self.ranks)==0:
                        return []
                else:
                        return self.__collectCodeLines(self.tree, PCRel)
                        
        def __collectCodeLines(self, node, PCRel=None):

            if node is None: return []
            result = []
            myprint("type(node).__name__: ",type(node).__name__)
            if type(node).__name__ == "BlockStatement":
                result = [self.getLineNumber(node)]
                statements = node.__getattribute__("statements")
                for sta in statements:
                        result +=  self.__collectCodeLines(sta, PCRel)
                result += [result[-1]+1] # } 那一列
                myprint("BlockStatement: final result", result)
                return result

            elif type(node).__name__ == "CatchClauseParameter":
                return []
          
            elif type(node).__name__ == "CatchClause":
                block = node.__getattribute__("block") # None or list
                if not block is None:
                   for b in block:
                           result +=  self.__collectCodeLines(b, PCRel) 				
                return result
	
            elif type(node).__name__ == "ExplicitConstructorInvocation":
                return []
                    
            elif type(node).__name__ == "TryStatement":
                block = node.__getattribute__("block") # None or list
                catches = node.__getattribute__("catches")  # None or list
                finally_block = node.__getattribute__("finally_block")  # None or list
                cur_line =self.getLineNumber(node)
                result = [cur_line]
                if not block is None:
                   try_block_acc=[]
                   for b in block:#each is StatementExpression
                           myprint("try block", b)
                           try_block = self.__collectCodeLines(b, PCRel)
                           
                           try_block_acc +=  try_block
                   myprint("try_block: ", try_block_acc)
                   self.__updatePCRel(PCRel, try_block_acc, cur_line)
                   result += try_block_acc 
                   result +=  [result[-1]+1] #the } line is sibling of curline
                   myprint("try_statement: ", result)

                if not catches is None:#many catch clause
                    for c in catches: #a catch clause
                           catch_line = result[-1] +1 # catch 那一列，
                           catch_statement=[catch_line]
                           catch_block = self.__collectCodeLines(c, PCRel)
                           myprint("catch_block: ", catch_block)
                           catch_statement += catch_block+ [catch_block[-1]+1]# }
                           self.__updatePCRel(PCRel, catch_block, catch_line)
                           result += catch_statement
                           myprint("catch_statement: ", catch_statement)
                final_line = result[-1]+1  # final 那一列， 如果有的話
                if not finally_block is None:
                    result += [final_line]	# final 那一列
                    final_block_acc=[]
                    for f in finally_block:
                           final_block = self.__collectCodeLines(f, PCRel) 
                           myprint("final_block: ", final_block)
                           final_block_acc +=  final_block
                    self.__updatePCRel(PCRel, final_block_acc, final_line)
                    result+= final_block_acc + [final_block_acc[-1]+1] # } 那一列
                myprint("begin of TryStatement: ", result[0], "size of try: ", len(result))
                myprint("result of TryStatement: ", result)
                if self.getLineSize(node)-len(result)>0:
                   myprint("*** We have problem in TryStatement at position 1060!")				
                return result
              
            elif type(node).__name__ == "BreakStatement":
                   return [self.getLineNumber(node) ]
                
            elif type(node).__name__ == "This":
                   return []
                
            elif type(node).__name__ == "SuperConstructorInvocation":
                   return [ ]

            elif type(node).__name__ == "ArraySelector":
                   return []
              
            elif type(node).__name__ == "Cast":
                   return []
                        
            elif type(node).__name__ == "ForControl":
                   return []

            elif type(node).__name__ == "TernaryExpression":
                   return []
					
            elif type(node).__name__ == "IfStatement":
                if_line = self.getLineNumber(node)                    
                result = [if_line]
                else_statement = node.__getattribute__("else_statement")
                then_statement = node.__getattribute__("then_statement")

                if type(then_statement).__name__ == "BlockStatement": #Block Statement
                        self.line_number -=1 #newly added because next BS will add one line
                        x =self.__collectCodeLines(then_statement, PCRel)			
                        myprint("then_statement (Block Statement): ", x)
                        result = x
                        self.__updatePCRel(PCRel, x[1:-1], if_line)
                        end_of_then_line = result[-1]
                else: # "ReturnStatement", "BreakStatement" , "ContinueStatement"
                        x =self.__collectCodeLines(then_statement, PCRel)			
                        myprint("then_statement (StatementExpression): ", x)	                        
                        end_of_then_line = if_line + len(x)
                        result += x
                        self.__updatePCRel(PCRel, x, if_line)
                        
                myprint("If statement then: result:", result)         
                if not else_statement is None:
                    else_line = end_of_then_line + 1   #else 那一行          
                    if type(else_statement).__name__ ==  "BlockStatement" : #Block Statement
                           z = self.__collectCodeLines(else_statement, PCRel)
                           result += z
                           myprint("else_statement(Block Statement): ", z)
                            
                           self.__updatePCRel(PCRel, z[1:-1], else_line)
                                                
                    elif type(else_statement).__name__ == "IfStatement":
                            #result += [else_line]  #不用， 因為並在else if ...
                            z = self.__collectCodeLines(else_statement, PCRel)				
                            myprint("else_statement (IfStatement) z : ", z, "result: ", result)
                            #end_of_else_line = else_line + len(z) # 不用加 } 那一列， 因為並在else if ...
                            result += z # + [end_of_else_line]                                                      
                            self.__updatePCRel(PCRel, z, else_line)                              
                    else:  #"StatementExpression" or type(then_statement).__name__ == "ReturnStatement" or type(then_statement).__name__ == "BreakStatement" or type(then_statement).__name__ == "ContinueStatement":
                            result += [else_line]
                            z = self.__collectCodeLines(else_statement, PCRel)				
                            myprint("else_statement (StatementExpression): ", z)
                            myprint("result: ", result)                            
                            result += z
                            self.__updatePCRel(PCRel, z, else_line)

                myprint("begin of if: ", result[0], "size of if: ", len(result))
                myprint("result of IFStatement: ", result)
                if self.getLineSize(node)-len(result)>0:
                   myprint("expect size of IFStatement: ", self.getLineSize(node))
                   myprint("***** we have problem in IfStatement (code position: 1081!)")				
                return result
        				
            elif type(node).__name__ == "SwitchStatementCase":
                case = node.__getattribute__("case") #may contain several literals
                statements = node.__getattribute__("statements")
                begin_case_line = self.getLineNumber(node)
                case_num=0
                if len(case) > 0:
                    for c in case:
                        result += self.__collectCodeLines(c, PCRel) + [begin_case_line + case_num]
                        case_num += 1
                else: #default
                    result +=  self.__collectCodeLines(case, PCRel) + [begin_case_line ]
                stats=[]
                for statement in statements:
                        stats +=  self.__collectCodeLines(statement, PCRel) 
                self.__updatePCRel(PCRel, stats, begin_case_line)
                result += stats
                myprint("begin of switch case: ", result[0], "size of switch case: ", len(result))
                myprint("result of switch case: ", result)
                if self.getLineSize(node)-len(result)>0:
                   myprint("***** we have problem in SwitchStatementCase (code position: 1135!)")				
                return result

            elif type(node).__name__ == "SwitchStatement":
                swith_line = self.getLineNumber(node)
                result = [swith_line]
                cases = node.__getattribute__("cases")
                expression = node.__getattribute__("expression")

                result += self.__collectCodeLines(expression, PCRel)
                casestats=[]
                for case in cases:
                        casestats+= self.__collectCodeLines(case, PCRel)
                result+= casestats
                end_of_switch = result[0] + len(result) # } 那一列
                self.__updatePCRel(PCRel, casestats, swith_line)                
                result += [end_of_switch]
                myprint("begin of switch : ", result[0], "size of switch : ", len(result))
                myprint("result of switch: ", result)
                if self.getLineSize(node)-len(result)>0:
                   myprint("***** we have problem in SwitchStatement (code position: 1135!)")				
                return result

            elif type(node).__name__ == "WhileStatement":
                while_line = self.getLineNumber(node)
                result = [while_line]
                condition = node.__getattribute__("condition")
                body = node.__getattribute__("body")
                result += self.__collectCodeLines(condition, PCRel)                                
                if type(body).__name__ == "BlockStatement": # BlockStatement
                        while_block =self.__collectCodeLines(body, PCRel)			
                        myprint("while body statement: ", while_block)
                        result = while_block
                        self.__updatePCRel(PCRel, while_block[1:-1], while_line)
                else:  #"StatementExpression":
                        while_block =self.__collectCodeLines(body, PCRel)			
                        myprint("while body statement: ", while_block)	                        
                        end_of_while_line = while_line + len(while_block)
                        result += while_block                    
                        self.__updatePCRel(PCRel, while_block, while_line)                
                              
                myprint("begin of while : ", result[0], "size of while : ", len(result))
                myprint("result of while: ", result)
                if self.getLineSize(node)-len(result)>0:
                        myprint("Size of While Statement", self.getLineSize(node))
                        myprint("***** we have problem in WhileStatement (code position: 1169!)")				
                return result

            elif type(node).__name__ == "DoStatement":
                myprint("node", node)
                do_line =self.getLineNumber(node)
                result = [do_line]
                condition = node.__getattribute__("condition")
                body = node.__getattribute__("body")
                if type(body).__name__ == "BlockStatement": # BlockStatement
                    while_block = self.__collectCodeLines(body, PCRel)  
                    myprint("do while_block (Block Statement): ", while_block)
                    while_block = while_block + [while_block[-1]+1] 
                    result =  while_block 
                    self.__updatePCRel(PCRel, while_block[1:-2], do_line)
 
                else:#"StatementExpression":
                    while_block =self.__collectCodeLines(body, PCRel)			
                    myprint("do while body statement: ", while_block)	                        
                    end_of_while_line = do_line + len(while_block)+1
                    while_block = while_block + [while_block[-1]+1]
                    result += while_block        
                    self.__updatePCRel(PCRel, while_block, do_line)
                myprint("begin of do while : ", result[0], "size of while : ", len(result))
                myprint("result of do while: ", result)
                if self.getLineSize(node)-len(result)>0:
                   myprint("***** we have problem in DoStatement (code position: 1180!)")				
                return result

            elif type(node).__name__ == "ForStatement":
                for_line = self.getLineNumber(node)
                result = [for_line]
                body = node.__getattribute__("body")
                control = node.__getattribute__("control")
                result +=  self.__collectCodeLines(control, PCRel)
                if type(body).__name__ == "BlockStatement" : 
                        for_block =self.__collectCodeLines(body, PCRel)			
                        myprint("for body statement: ", for_block)	                        
                        #end_of_for_line = for_line + len(for_block[1:])-1 # [1:] because block has the line, 
                        result = for_block # for and block { in the same line
                        self.__updatePCRel(PCRel, for_block[1:-1], for_line)                                
                        #result+= [end_of_for_line]                      
                else: #StatementExpression"
                        for_block =self.__collectCodeLines(body, PCRel)			
                        myprint("for body statement: ", for_block)	                        
                        end_of_for_line = for_line + len(for_block)
                        result += for_block                    
                        self.__updatePCRel(PCRel, for_block, for_line)                                
                          
                myprint("begin of for : ", result[0], "size of for : ", len(result))
                myprint("result of for: ", result)                

                if self.getLineSize(node)-len(result)>0:
                   myprint("***** we have problem in ForStatement (code position: 1202!)")				
                return result
				
            elif type(node).__name__ == "EnhancedForControl":
                return []

            elif type(node).__name__ == "ContinueStatement":
                return [ self.getLineNumber(node) ]
                
            elif type(node).__name__ == "ReturnStatement":
                result = [self.getLineNumber(node)]
                expression = node.__getattribute__("expression")
                result += self.__collectCodeLines(expression, PCRel) 
                return result
				
            elif type(node).__name__ == "ConstructorDeclaration": # constructor XXX()
                construct_line = self.getLineNumber(node)
                result = [construct_line]
                parameters = node.__getattribute__("parameters")
                body = node.__getattribute__("body")
                if not parameters is None:
                    for parameter in parameters:
                            result += self.__collectCodeLines(parameter, PCRel) 
                if not body is None:
                    constructor_block=[]
                    for bd in body:
                            myprint("type(bd).__name__: ",type(bd).__name__)
                            constructor_block +=self.__collectCodeLines(bd, PCRel)
                            myprint("constructor_block:", constructor_block)
                    result +=  constructor_block
                    myprint("Before constructor_block PCRel: ", sortPermutation(PCRel)) #sortPermutation(x))
                    myprint("constructor_block, construct_line: ", constructor_block, construct_line)                                                            
                    self.__updatePCRel(PCRel, constructor_block, construct_line)                                                            
                    myprint("After constructor_block PCRel: ", sortPermutation(PCRel)) #sortPermutation(x))
                    result += [result[0] + len(constructor_block) +1]  # } 那一列
                myprint("begin of ConstructorDeclaration : ", result[0], "size of for : ", len(result))
                myprint("result of ConstructorDeclaration: ", result)                
                if self.getLineSize(node)-len(result)>0:
                   myprint("***** we have problem in ConstructorDeclaration (code position: 1230!)")				
                return result
				
            elif type(node).__name__ == "ClassCreator": # new XXX()
                reftype = node.__getattribute__("type")
                arguments = node.__getattribute__("arguments")
                selectors = node.__getattribute__("selectors")
                body = node.__getattribute__("body")
                result +=  self.__collectCodeLines(reftype, PCRel) 
                if not arguments is None:
                    for arg in arguments:
                            result +=  self.__collectCodeLines(arg, PCRel)            
                if not selectors is None and len(selectors)>0:
                    for selector in selectors:
                        result += self.__collectCodeLines(selector, PCRel) 
                if not body is None:
                    for bd in body:
                            result += self.__collectCodeLines(bd, PCRel) 
                return result

            elif type(node).__name__ == "StatementExpression":
                result = [self.getLineNumber(node)]
                expression = node.__getattribute__("expression")
                myprint("StatementExpression: expression type:", type(expression).__name__)
                result += self.__collectCodeLines(expression, PCRel)
                myprint("StatementExpression: result:", result)
                if self.getLineSize(node)-len(result)>0:
                   myprint("***** we have problem in StatementExpression (code position: 1257!)")				
                return result
                
            elif type(node).__name__ == "Assignment":       
                left_expression = node.__getattribute__("expressionl")
                right_expression = node.__getattribute__("value")
                result += self.__collectCodeLines(left_expression, PCRel) + self.__collectCodeLines(right_expression, PCRel) 
                return result

            elif type(node).__name__ == "VariableDeclaration":
                tty = node.__getattribute__("type")
                declarators = node.__getattribute__("declarators")
                result += self.__collectCodeLines(tty, PCRel) 
                for dec in declarators:
                        result += self.__collectCodeLines(dec, PCRel)  
                return result                
           
            elif type(node).__name__ == "LocalVariableDeclaration":
                result = [self.getLineNumber(node)]
                tty = node.__getattribute__("type")
                declarators = node.__getattribute__("declarators")
                result += self.__collectCodeLines(tty, PCRel) 
                for dec in declarators:
                        result += self.__collectCodeLines(dec, PCRel)  
                if self.getLineSize(node)-len(result)>0:
                   myprint("***** we have problem in LocalVariableDeclaration (code position: 1282!)")				
                return result                
 
            elif type(node).__name__ == "ArrayInitializer":
                initializers = node.__getattribute__("initializers")
                if not initializers is None and len(initializers)>0:
                    for e in initializers:
                            result += self.__collectCodeLines(e, PCRel) 
                return result                

            elif type(node).__name__ == "ArrayCreator":
                initializer = node.__getattribute__("initializer")
                tty = node.__getattribute__("type")
                result +=  self.__collectCodeLines(tty, PCRel)
                result += self.__collectCodeLines(initializer, PCRel)          
                return result                

            elif type(node).__name__ == "VariableDeclarator":
                initialize = node.__getattribute__("initializer")
                if not initialize is None:
                    result += self.__collectCodeLines(initialize, PCRel) 
                return result                


            elif type(node).__name__ == "MethodInvocation":
                arguments = node.__getattribute__("arguments")
                selectors=node.__getattribute__("selectors")
                
                if not arguments is None:
                    for arg in arguments:
                            result +=  self.__collectCodeLines(arg, PCRel) 
                
                if not selectors is None and len(selectors)>0:
                    for sel in selectors:
                            result += self.__collectCodeLines(sel, PCRel)
                return result                
                    
 
            elif type(node).__name__ == "FormalParameter":
                return []
        
            elif type(node).__name__ == "BasicType":
                return []

            elif type(node).__name__ == "TypeArgument":
                return []

            elif type(node).__name__ == "ReferenceType":
                return []

            elif type(node).__name__ == "BinaryOperation":
                return []
                        
            elif type(node).__name__ == "Literal":
                return []
                
            elif type(node).__name__ == "MemberReference":
                return []
                    

            elif type(node).__name__ == "CompilationUnit":
                c_line =   self.getLineNumber(node)
                imports = node.__getattribute__("imports")
                package = node.__getattribute__("package")
                types = node.__getattribute__("types")

                if not package is None:
                    result += self.__collectCodeLines(package, PCRel) 
                if not imports is None:
                    for imp in imports:
                        result += self.__collectCodeLines(imp, PCRel) 
                if not types is None:
                    for ty in types:
                        result += self.__collectCodeLines(ty, PCRel) 
                self.__updatePCRel(PCRel, result, c_line)                                                            
                return result

            elif type(node).__name__ == "PackageDeclaration":
                return [self.getLineNumber(node)]

            elif type(node).__name__ == "Import":
                return [self.getLineNumber(node)]

            elif type(node).__name__ == "InterfaceDeclaration":
                interface_line =  self.getLineNumber(node)
                result = [interface_line]
                body = node.__getattribute__("body")
                if not body is None:
                    for bd in body:
                        if type(bd) is list and len(bd)>0:
                            for sbd in bd:
                                result += self.__collectCodeLines(sbd, PCRel) 
                        else:    
                         result += self.__collectCodeLines(bd, PCRel)
                self.__updatePCRel(PCRel, result, interface_line)                                                            
                result += [result[0] + len(result)]
                myprint("begining of InterfaceDeclaration:: ", result[0], "size: ", len(result))
                myprint("InterfaceDeclaration: ", result)
                if self.getLineSize(node)-len(result)>0:
                   myprint("**** we have problem in InterfaceDeclaration (at position 1306)")					
                return result

            elif type(node).__name__ == "ClassDeclaration":
                class_line =self.getLineNumber(node)
                result = [class_line]
                body = node.__getattribute__("body")
                name = node.__getattribute__("name")  
                if not body is None:
                    for bd in body:
                        myprint("Python_debugger bd: ", type(bd).__name__)
                        if type(bd) is list and len(bd)>0:
                            block_lines = []
                            for sbd in bd:
                                myprint("ClassDeclaration: for " ,type(sbd).__name__)
                                block_lines += self.__collectCodeLines(sbd, PCRel)
                            myprint("ClassDeclaration: block_lines:", block_lines)
                            result += [ block_lines[0]-1 ] + block_lines
                            self.__updatePCRel(PCRel, block_lines, class_line)                                                            
                            result += [block_lines[-1] + 1] # { .... }
                            myprint("@@@@@@@ result: ", result)								
                        else:
                         myprint("ClassDeclaration: else: type(bd).__name__ " ,type(bd).__name__)
                         block_lines = self.__collectCodeLines(bd, PCRel)
                         myprint("ClassDeclaration: else : block_lines:", block_lines)
                         self.__updatePCRel(PCRel, block_lines, class_line)
                         result += block_lines
                         
                result += [ result[0]+len(result) ] # } 那一行
                myprint("begining of ClassDeclaration:: ", result[0], "size: ", len(result))
                myprint("ClassDeclaration: ", result)
                if self.getLineSize(node)-len(result)>0:
                   myprint("**** we have problem in ClassDeclaration (at position 1321)")				
                return result
				
            elif type(node).__name__ == "ConstantDeclaration":
                result = [self.getLineNumber(node)]
                declarators = node.__getattribute__("declarators")
                if not declarators is None:
                    for declarator in declarators:
                            result += self.__collectCodeLines(declarator, PCRel) 
                if self.getLineSize(node)-len(result)>0:
                   myprint("**** we have problem in ConstantDeclaration (at position 1337)")				
                return result
            elif type(node).__name__ == "FieldDeclaration":
                result = [self.getLineNumber(node)]
                declarators = node.__getattribute__("declarators")
                if not declarators is None:
                    for declarator in declarators:
                            result += self.__collectCodeLines(declarator, PCRel) 
                if self.getLineSize(node)-len(result)>0:
                   myprint("**** we have problem in FieldDeclaration (at position 1334)")				
                return result
					
            elif type(node).__name__ == "Annotation":
                result = [self.getLineNumber(node)]  
                return result

            elif type(node).__name__ == "MethodDeclaration":
                method_line = self.getLineNumber(node)
                result = [method_line]  
                annotations = node.__getattribute__("annotations")
                body = node.__getattribute__("body")
                parameters = node.__getattribute__("parameters")
                modifiers = node.__getattribute__("modifiers")
                name = node.__getattribute__("name")
                abstract=False
                for ann in annotations:
                    result +=self.__collectCodeLines(ann, PCRel) 
                if not modifiers is None:
                    for modifier in sorted( modifiers):
                        if  modifier=="abstract":  abstract=True
                if not name is None:
                    if not parameters is None:
                        for parameter in parameters:
                                result +=  self.__collectCodeLines(parameter, PCRel)            
                if not abstract :
                    if not body is None:
                        for bd in body:
                                result +=self.__collectCodeLines(bd, PCRel)
                        self.__updatePCRel(PCRel, result, method_line)                         
                        result += [result[0]+len(result)]				
                myprint("begining of MethodDeclaration:: ", result[0], "size: ", len(result))
                myprint("MethodDeclaration: ", result)
                if self.getLineSize(node)-len(result)>0:
                   myprint("**** we have problem in MethodDeclaration (at position 1365)")				
                return result
            return result

        def __collectBodyLines(self, body):
                tmp = [] #new orders line ids in permuted code
                for b in body:#generate the permutation vector
                    begin_line = self.getLineNumber(b)	
                    end_line = self.getLineSize(b) + begin_line-1
                    p = list(range(begin_line, end_line+1))
                    tmp += p
                return tmp

import builtins
import pprint

pp = pprint.PrettyPrinter(indent=4)

		
import re

def readJavaFile(filename):
    #print(filename)
    with open(filename, 'r', encoding=getEncoding(filename)) as myFile:
        data = myFile.read()
        #print("pyd : readJavaFile: data: ", data)
        data = re.sub(r'static +public', 'public static', data)
        data = re.sub(r'public +abstract', 'abstract public', data)
        data = re.sub(r'protected +abstract', 'abstract protected', data)
    return data

        
def sortDict(t):
    return sortPermutation(t)

        
def sortPermutation(p):
    if p is None: return None 
    return sorted(p.items(), key=lambda x:  -1 if not x[0] else x[0], reverse=False)

def myprint( *args):
        if (DEBUG):
                print(*args)
        
DEBUG=False

if __name__ == '__main__' :
    sourcefileX = 'MainX.java'
    sourcefileY = 'MainY.java'
    destfileX = "newMainX"
    destfileY = "newMainY"
    
    data1=readJavaFile(sourcefileX)
    tree = javalang.parse.parse(data1)
    #myprint(tree1)
    parent_child_inv_index ={}
    par = pyd.AST(tree)
    # normalize the code and get line counts
    newCode,x, y = par.getAugmentedCode(changeVariable=False, permuStatement=False)
    write_to_file(newCode, destfileX)
    myprint( par.collectCodeLines(parent_child_inv_index) )  # get the lines and tree structure in terms of children-parent dict
    print()
    print("x: ", x) #sortPermutation(x))
    print("y: ", y) #sortPermutation(x))


      
