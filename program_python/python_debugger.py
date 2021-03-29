import javalang
import ast
import random, string
import os
import chardet
import glob
import python_debugger as pyd
from chardet.universaldetector import UniversalDetector
import CG

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

# Given a tokens to be written
def write_to_file(tokens, filename):
    fp = open(filename, 'w', encoding='utf8')

    for token in tokens:
        if (token !=  NEWLINE):
                fp.write(token + " ")
        if token in symbols:
            fp.write('\n')

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

###################
# Transforming rule 1: local variable name changing
###################
def change_variable(tree):
 
#processing vars in each function
    for _, mnode in tree.filter(javalang.tree.MethodDeclaration):
        declareVars = list()
        referencedVars = list()
        referencedQuaVars=list()
        usedVarNames = set()
        for _, node in mnode.filter(javalang.tree.LocalVariableDeclaration): #local variable               
            declarators = node.__getattribute__("declarators")
            for vnode in declarators:
                usedVarNames.add( vnode.__getattribute__("name") )
                declareVars.append(vnode)
                            
        for _, node in mnode.filter(javalang.tree.FormalParameter): #arguments
            usedVarNames.add( node.__getattribute__("name") )
            declareVars.append(node)

 
        for _, mrnode in mnode.filter(javalang.tree.MemberReference):
            qualifier = mrnode.__getattribute__("qualifier")
            if  qualifier =="": # a pure local variable, not member variable
                referencedVars.append(mrnode)
            if  not qualifier is None: # a pure local variable, not member variable
                referencedQuaVars.append(mrnode)
                


    #Start change names        
        count = len(usedVarNames)
        random.shuffle(varNameCandicates)
        newVarNames = varNameCandicates[:count]
        varMap = dict(zip(usedVarNames, newVarNames))
    #Vars    
        for node in declareVars:
            #print("old declared name:", node.__getattribute__("name"))
            #print("new declared name:", varMap[node.__getattribute__("name")])
            node.__setattr__("name", varMap[node.__getattribute__("name")])
            
        for node in referencedVars:            
            node.__setattr__("member", varMap.get(node.__getattribute__("member"), node.__getattribute__("member")))

        for node in referencedQuaVars:            
             node.__setattr__("qualifier", varMap.get(node.__getattribute__("qualifier"), node.__getattribute__("qualifier")))

        
    tokens = toJava(tree)    
    code_tokens= getJavaTokensFromString(tokens)
    
    
    return (code_tokens)
    #write_to_file(code_tokens, time)
      

###################
# Transforming rule 2: changing function orders
###################
def change_fun_order(tree):
    for _, cnode in tree.filter(javalang.tree.ClassDeclaration): #for each class node
        body = cnode.__getattribute__("body")
        if not body is None and len(body)>1: #collect MethodDeclaration nodes
            fnode_list = [fnode for fnode in body if type(fnode).__name__ == "FieldDeclaration"]
#            mnode_list = [mnode for mnode in body if type(mnode).__name__ == "MethodDeclaration" or type(mnode).__name__ == "ConstructorDeclaration"]
            mnode_list = [mnode for mnode in body if mnode not in fnode_list]
            if len(mnode_list)>0:
                random.shuffle (mnode_list)
                
                body = fnode_list + mnode_list
                cnode.__setattr__("body", body)
    return tree        

    
##
## The Tree to Java code here
## Return a string
##
stack=[]
def toJava(node):
    stack=[]
    return 	toJava2(node)
	
def toJava2(node):
    if node is None: return ""
    result = ""

    if type(node).__name__ == "BlockStatement":
        statements = node.__getattribute__("statements")
        result += "{ " +  " ".join( [toJava(sta) for sta in statements] ) + "}"
  
    elif type(node).__name__ == "CatchClauseParameter":
        name = node.__getattribute__("name") # 
        types = node.__getattribute__("types") # None or list
        if not types is None:
            result += " ".join( e for e in types) + " " + name

  
    elif type(node).__name__ == "CatchClause":
        block = node.__getattribute__("block") # None or list
        parameter = node.__getattribute__("parameter")  # None or object
        result += " catch ( "
        if not parameter is None:
            result += toJava(parameter)
        result += " )  { "    
        if not block is None:
           result +=  " ".join( [ toJava(b) for b in block] )
        result += " }  "

    elif type(node).__name__ == "ExplicitConstructorInvocation":
        arguments = node.__getattribute__("arguments") # None or list
        result += "this("
        if not arguments is None:
           result +=  ",".join( [ toJava(arg) for arg in arguments] )
        result += ")"
            
    elif type(node).__name__ == "TryStatement":
        block = node.__getattribute__("block") # None or list
        catches = node.__getattribute__("catches")  # None or list
        finally_block = node.__getattribute__("finally_block")  # None or list

        result += "try " + " { " 
        if not block is None:
           result +=  " ".join( [ toJava(b) for b in block] )
        result += " } "
        if not catches is None:
           result += " ".join( [ toJava(c) for c in catches] )
        if not finally_block is None:
           result += "finally { "+ " ".join( [ toJava(f) for f in finally_block] ) +  " } "

        
    elif type(node).__name__ == "BreakStatement":
        result += "break ;"
        
    elif type(node).__name__ == "This":
        postfix_operators = node.__getattribute__("postfix_operators")
        prefix_operators = node.__getattribute__("prefix_operators")
        selectors = node.__getattribute__("selectors")
        if not prefix_operators is None and len(prefix_operators)>0:
            result = " ".join([e for e in prefix_operators] )+result
            
        result += "this"
        if not selectors is None and len(selectors)>0:
            for e in selectors:
                if type(e).__name__ != "ArraySelector":
                    result += "."+toJava(e)
                else:
                    result += toJava(e)

        if not postfix_operators is None and len(postfix_operators)>0:
            result += " ".join([e for e in postfix_operators] )
        
    elif type(node).__name__ == "SuperConstructorInvocation":
        arguments = node.__getattribute__("arguments")
        result += "super("
        if not arguments  is None and len(arguments)>0 :
            result += ",".join([toJava(e) for e in arguments] )
        result += ")" 

    elif type(node).__name__ == "ArraySelector":
        index = node.__getattribute__("index")
        result += " [" + toJava(index) + "] " 

      
    elif type(node).__name__ == "Cast":
        expression = node.__getattribute__("expression")
        ttype = node.__getattribute__("type")
        
        result += "( " + toJava(ttype) + " ) "
        opr=toJava(expression)
        if len(list(javalang.tokenizer.tokenize(opr ))) > 1:
            result += " (" + opr + ") "
        else:
             result += opr
                
    elif type(node).__name__ == "ForControl":
        condition = node.__getattribute__("condition")
        init = node.__getattribute__("init") # init may be object of list
        update = node.__getattribute__("update")

        if (type(init) is list):
            result += "( " + ",".join( [ toJava(e) for e in init ] ) +";" + toJava(condition) + ";"
        else:
            result += "( " + toJava(init) +";" + toJava(condition) + ";" 
        result+= ",".join( [toJava(upd) for upd in update])        
        result += ") "

    elif type(node).__name__ == "TernaryExpression":
        condition = node.__getattribute__("condition")
        if_false = node.__getattribute__("if_false")
        if_true = node.__getattribute__("if_true")
        result += toJava(condition) + " ? " + toJava(if_true)
        if not if_false is None:
            result += " : " + toJava(if_false)
        

    elif type(node).__name__ == "IfStatement":
        condition = node.__getattribute__("condition")
        else_statement = node.__getattribute__("else_statement")
        then_statement = node.__getattribute__("then_statement")
        result += "if (" + toJava(condition) + ") "  + toJava(then_statement)
        if not else_statement is None:
            result += " else " + toJava(else_statement)
    elif type(node).__name__ == "SwitchStatementCase":
        case = node.__getattribute__("case")
        statements = node.__getattribute__("statements")
        if len(case) > 0:
            for c in case:
                result += "case " + toJava(c) + " : "
        else:
            result += "default " + toJava(case) + ": "
           
        result += "".join( [ toJava(statement) for statement in statements] )

    elif type(node).__name__ == "SwitchStatement":
        cases = node.__getattribute__("cases")
        expression = node.__getattribute__("expression")

        result += "switch (" + toJava(expression) + ") { "
        result+= " ".join( [ toJava(case) for case in cases] )
        result += " } "

    elif type(node).__name__ == "WhileStatement":
        condition = node.__getattribute__("condition")
        body = node.__getattribute__("body")
        result += "while (" + toJava(condition) + ") " + NEWLINE + " " + toJava(body)

    elif type(node).__name__ == "DoStatement":
        condition = node.__getattribute__("condition")
        body = node.__getattribute__("body")
        result += "do " + NEWLINE + " " + toJava(body) + " while ( " + toJava(condition) + " ) ; "
        
    elif type(node).__name__ == "ForStatement":
        body = node.__getattribute__("body")
        control = node.__getattribute__("control")
        
        result += "for " + toJava(control) + NEWLINE + " " +  toJava(body)
    elif type(node).__name__ == "EnhancedForControl":
        iterable = node.__getattribute__("iterable")
        var = node.__getattribute__("var")
        result += " ( "+ toJava(var) + " : "+	toJava(iterable)	+" ) "
    elif type(node).__name__ == "ContinueStatement":
        result += " continue ; "
  
  
    elif type(node).__name__ == "ReturnStatement":
        expression = node.__getattribute__("expression")
        result += "return" + " "
        result += toJava(expression) + " "
        result += ";"
    elif type(node).__name__ == "ConstructorDeclaration": # constructor XXX()
        classname = node.__getattribute__("name")
        parameters = node.__getattribute__("parameters")
        modifiers = node.__getattribute__("modifiers")
        body = node.__getattribute__("body")
        for mod in sorted(modifiers):
            result += mod + " "
        result += classname + "("
        if not parameters is None:
            result +=  ",".join( [toJava(parameter) for parameter in parameters])
        result += ") {" + " "
        if not body is None:
            result +=  " ".join( [toJava(bd) for bd in body])
        result += " }"

    elif type(node).__name__ == "ClassCreator": # new XXX()
        reftype = node.__getattribute__("type")
        arguments = node.__getattribute__("arguments")
        selectors = node.__getattribute__("selectors")
        body = node.__getattribute__("body")
        result += 'new ' + toJava(reftype) + "( "
        if not arguments is None:
            result +=  ",".join( [toJava(arg) for arg in arguments])            
        result += ")"
        if not selectors is None and len(selectors)>0:
            for selector in selectors:
                result += "."+ toJava(selector)
        if not body is None:
            result +=  " { " +  " ".join( [toJava(bd) for bd in body]) + " } "

    elif type(node).__name__ == "This":
        selectors = node.__getattribute__("selectors")
        result += 'this'
        for selector in selectors:
            result += "."+ toJava(selector)
         

    elif type(node).__name__ == "StatementExpression":
        expression = node.__getattribute__("expression")
        result += toJava(expression) + " ; "
        
    elif type(node).__name__ == "Assignment":       
        left_expression = node.__getattribute__("expressionl")
        assigment_type = node.__getattribute__("type") # =
        right_expression = node.__getattribute__("value")      
        result += toJava(left_expression) + " " + assigment_type + " " + toJava(right_expression) 

    elif type(node).__name__ == "VariableDeclaration":
        tty = node.__getattribute__("type")
        declarators = node.__getattribute__("declarators")
        result += toJava(tty) + " "
        result += ",".join([toJava(dec) for dec in declarators ])
        
   
    elif type(node).__name__ == "LocalVariableDeclaration":
        tty = node.__getattribute__("type")
        declarators = node.__getattribute__("declarators")
        modifiers= node.__getattribute__("modifiers")
        if not modifiers is None:
           result += ' '.join( [e for e in modifiers] ) + " "
        result += toJava(tty) + " "
        result += ",".join([toJava(dec) for dec in declarators ]) + " ; "

    elif type(node).__name__ == "ArrayInitializer":
        initializers = node.__getattribute__("initializers")
        result += "{ " 
        if not initializers is None and len(initializers)>0:
            result += ",".join(toJava(e) for e in initializers) 
        result += "} "

    elif type(node).__name__ == "ArrayCreator":
        dimensions = node.__getattribute__("dimensions")
        initializer = node.__getattribute__("initializer")
        tty = node.__getattribute__("type")
        result += "new " + toJava(tty)
        if not dimensions is None and len(dimensions)>0:
            for e in dimensions:
                dim=""
                if e is not None: dim = toJava(e)
                result += " [ " +  dim + "]"
        result += toJava(initializer)
        

    elif type(node).__name__ == "VariableDeclarator":
        dimensions = node.__getattribute__("dimensions")
        name = node.__getattribute__("name")
        initialize = node.__getattribute__("initializer")
        result += name
        if not dimensions is None and len(dimensions)>0:
            for e in dimensions:
                dim = ""
                if e is not None: dim = toJava(e)
                result += " [ " + dim + " ] "

        if not initialize is None:
            result += "=" + " "
            result += toJava(initialize) + " "


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
            result +=  ",".join( [toJava(arg) for arg in arguments])
    
        result += ")" + " "
        
        if not selectors is None and len(selectors)>0:
            result += '.'+ ' '.join( [ toJava(sel) for sel in selectors])
            
        if not  prefix_operators is None and len(prefix_operators)>0: 
            result =  ' '.join(prefix_operators)+ result

        if not  postfix_operators is None and len(postfix_operators)>0: 
            result +=  ' '.join(postfix_operators)

    elif type(node).__name__ == "FormalParameter":
        tty = node.__getattribute__("type")
        name = node.__getattribute__("name")
        result += toJava(tty) + " "
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
        result += toJava(tty)
        
    elif type(node).__name__ == "ReferenceType":
        name = node.__getattribute__("name")
        dimensions = node.__getattribute__("dimensions")
        arguments = node.__getattribute__("arguments")
        sub_type= node.__getattribute__("sub_type")
        result += name
        if not sub_type is None:
           result +=  "." + toJava(sub_type)
        if not arguments is None:
           result +=  "<" +  ",".join( [ toJava(arg) for arg in arguments]) + "> " 
        if not dimensions is None:
            for dim in dimensions:
                if dim is None:
                    result += "[]" + " "
                else:
                    result += "[" + " "
                    result += dim + " "
                    result += "]" + " "

    elif type(node).__name__ == "BinaryOperation":
        # print("into binary operation")
        operandl = node.__getattribute__("operandl")
        operandr = node.__getattribute__("operandr")
        operator = node.__getattribute__("operator")
        stack.append(operator)
        print(stack)
        # print(operator)
        opl = toJava(operandl)
        '''
        if len(list(javalang.tokenizer.tokenize(opl))) > 1 and operator != "+" and operator != "-":
            opl = '(' + opl + ')'
        '''			
        opr = toJava(operandr)
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
            result += '.'.join( [ toJava(sel) for sel in selectors])
            
        if not  postfix_operators is None and len(postfix_operators)>0: 
            result +=   ' '.join(postfix_operators)
            

    elif type(node).__name__ == "CompilationUnit":
        imports = node.__getattribute__("imports")
        package = node.__getattribute__("package")
        types = node.__getattribute__("types")

        if not package is None:
            result += toJava(package) + " "
        if not imports is None:
            for imp in imports:
                result += toJava(imp) + " "
        if not types is None:
            for ty in types:
                result += toJava(ty) + " "

    elif type(node).__name__ == "PackageDeclaration":
        annotations = node.__getattribute__("annotations")
        documentation = node.__getattribute__("documentation")
        modifiers = node.__getattribute__("modifiers")
        name = node.__getattribute__("name")
        if not name is None:
            result += "package" + " "
            result += name + " "
            result += ";"

    elif type(node).__name__ == "Import":
        path = node.__getattribute__("path")
        static = node.__getattribute__("static")
        wildcard = node.__getattribute__("wildcard")
        if not path is None:
            result += "import" + " "
            if static is True:
                result += "static" + " "
 
            xx = lambda w: path + ".*" if w else path
            result += xx(wildcard) + " "
            result += ";" + " "

    elif type(node).__name__ == "InterfaceDeclaration":
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
            result += " extends " + toJava(extends) + " "
        result += "{" + " "
        if not body is None:
            for bd in body:
                if type(bd) is list and len(bd)>0:
                    result += " { "
                    for sbd in bd:
                        result += toJava(sbd) + " "
                    result += " } "
                else:    
                 result += toJava(bd) + "  "
        result += " }"
        

    elif type(node).__name__ == "ClassDeclaration":
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
            result += " extends " + toJava(extends) + " "
        if not implements  is None:
            result += " implements " + " , ".join(toJava(imp) for imp in implements) + " "   
        result += "{" + " "
        if not body is None:
            for bd in body:
                if type(bd) is list and len(bd)>0:
                    result += " { "
                    for sbd in bd:
                        result += toJava(sbd) + " "
                    result += " } "
                else:    
                 result += toJava(bd) + " "
        result += " }"

    elif type(node).__name__ == "FieldDeclaration":
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
            result += toJava(tty) + " "
        if not declarators is None:
            result += ",".join( [toJava(declarator) for declarator in declarators] )
            
            result += ";" + " "
            # result.append(";")
    elif type(node).__name__ == "Annotation":
        element = node.__getattribute__("element")
        name = node.__getattribute__("name")	
        result += "@"+name+" ; "

    elif type(node).__name__ == "MethodDeclaration":
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
            result +=toJava(ann) 
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
                result +=  ",".join( [toJava(parameter) for parameter in parameters])            
            result += ") " + " "
            if not throws is None and len(throws)>0:
                result += " throws " + ",".join(throws)    
            # result.append(") {")
        if not abstract :
            if not body is None:
                result +=  " { " + " ".join( [toJava(bd) for bd in body]) + " } "
            else:
                result += " ; "
        else:
            result += " ; "
    return result


import builtins


def print(*args, **kwargs):
    builtins.print("    > ", *args, **kwargs)

import pprint

pp = pprint.PrettyPrinter(indent=4)

def getAugmentedCode(tree, changeVariable=True, permuStatement=True):
    if permuStatement: tree = change_fun_order(tree) #CG.cdPermuteCode(tree) #
    if changeVariable: 
        newCode = change_variable(tree) #a list of tokens
    else:
        newCode = pyd.toJava(tree)	#a string
        newCode=getJavaTokensFromString(newCode) # Get the  source code
        #print(newCode)
    return newCode

import re          
if __name__ == '__main__' :
    sourcefile = 'test.java'
    destfile = "newtest"
    #sourcefile = "Main9new.java" #revised code
    #destfile="Main9newChanged.java" # variable renamed code
    with open(sourcefile, 'r', encoding='utf8') as myFile:
        data = myFile.read()        
        data = re.sub(r'static +public', 'public static', data)
        data = re.sub(r'public +abstract', 'abstract public', data)
        data = re.sub(r'protected +abstract', 'abstract protected', data)

        tree = javalang.parse.parse(data)
        print(tree)
        pp.pprint(toJava(tree))
       
        
        time = 1

        for num in range(time):
            print("***", num)
            newCode = getAugmentedCode(tree, changeVariable=False, permuStatement=False)
            print(newCode)
            filename = destfile + str(num) + ".java"
            write_to_file(newCode, filename)

