import javalang
import ast
import random, string
import os
import chardet
import glob
from chardet.universaldetector import UniversalDetector
import python_debugger as pyd

detector = UniversalDetector()


class CGData:
    def __init__(self, member, qualifier):
        self.member = member
        self.qualifier = qualifier
        
    def display(self, level):
        n = (level -1) * 3
        levelspaces = ' ' * n
        print(levelspaces, self)
        print(levelspaces, "member: ", self.member)
        print(levelspaces, "qualifier: ", self.qualifier)
    
##################################################
class CGComp:
    def __init__(self, treenode, inputs, outputs):
        self.treenode = treenode # a tree statement
        self.inputs = inputs  #a list of CGData nodes
        self.outputs = outputs  # a CGData node
        self.type = "CGComp"

    def display(self, level):
        n = (level -1) * 3
        levelspaces = ' ' * n

        print(levelspaces, "Display Comp Node ", type(self.treenode).__name__, self)
        
        print(levelspaces, "Display Inputs to the Comp Node...")        
        for inp in self.inputs:
            inp.display(level+1)
                       
        print(levelspaces, "Display Outputs from the Comp Node...")        
        for outp in self.outputs:
            outp.display(level + 1)
        
##################################################       
class CG (CGComp) : ## extends CGComp
    def __init__(self, treenode=None, inputs=[], outputs=[], referencedgraph=None):
        super().__init__(treenode, inputs, outputs)
        self.graph = []   #compnodes in the graph, and can contain subgraph
        self.datatbl = [] #self created data
        self.localvariables = [] #(local variable data)
        self.referencedgraph = referencedgraph  #reference to outside graph
        self.type = "CG"
        
    def isLocalVariable(self, member, qualifier):
        res = [ dn for dn in self.localvariables if dn.member == member and dn.qualifier==qualifier]
        if (len(res)>0):
            return True
        else:
            return False
        
        
    def searchDataNode(self, member, qualifier):
        #print("Searching ", qualifier, member, " in ", self.datatbl)
        res = [ dn for dn in self.datatbl if dn.member == member and dn.qualifier==qualifier]
        if (len(res)>0):
            return res[-1]
        else:
            return None

    def  lookforDataNode(self, member, qualifier):
        parentGraph = self
        found = False
        while not found and parentGraph is not None:
            res = parentGraph.searchDataNode(member, qualifier)
            if res is not None:
                return res
            else:            
                parentGraph=parentGraph.referencedgraph
   
        
    def insertDataNode(self, member, qualifier):
        
        nd = CGData(member, qualifier)
        self.datatbl.append(nd )
        return nd
        
    #inputs: [dn1, dn2....]
    #output: [dn1, dn2, ...]
    def insertCompNode(self, treenode, inputs, outputs):
        
        cmpnode = CGComp(treenode, inputs, outputs) 
        self.graph.append( cmpnode )
        return cmpnode
    
    def insertCGNode(self, cg, inputs, outputs):        
        cg.inputs = inputs
        cg.outputs =  outputs 
        self.graph.append( cg )
        return cg

# return all sybpath of CompNodes from cmpd node
# [ path1, path2, ..]
    def findPaths(self, cmpd):
        #print("findPaths: ", type(cmpd.treenode).__name__, cmpd)
        if cmpd is None:
            return []
        else:
            res=[  ] #initially, only one path of one node
            for datanode in cmpd.outputs:
                next_cnodes = [cnode  for cnode in self.graph if datanode in cnode.inputs]
                for nd in next_cnodes:
                    subpaths = self.findPaths( nd ) # [ [nd,...], [nd, ..] ...  ] subpaths starting from nd                    
                    res = res + subpaths            
            if len(res)>0:
                res =[ [cmpd] + p for p in res]
            else:
                res = [ [cmpd] ]
            return res

# find all paths in graph        
    def findRootPaths(self):
        #Those cmp nodes without inputs
        roots = [ cnode for cnode in self.graph if len([ x for x in cnode.inputs if  x in self.outputs] ) == 0]
        #print(roots)
        res =[]
        for r in roots:
            ps = self.findPaths( r )
            res += ps
        return res

    # p1: [n1, n2, ...]
    # p2: [m1, m2, ...]
    # return [ [x1, x2, ...], [..], ... ]
    def mergeTwoPaths(self, p1, p2):
        if p1 is None or len(p1)==0:
            return [p2]
        elif p2 is None or len(p2)==0:
            return [p1]
        else:
            if p1[-1] == p2[-1]:
                paths = self.mergeTwoPaths(p1[:-1], p2[:-1])
                return [ p + [ p1[-1] ] for p in paths if  not p1[-1] in p]
            else:
                paths1 = self.mergeTwoPaths(p1[:-1], p2)
                paths1 =  [ p + [ p1[-1] ] for p in paths1 if not p  is None and not p1[-1] in p]
               
                paths2 = self.mergeTwoPaths( p1, p2[:-1])
                paths2 =  [  p + [ p2[-1] ] for p in paths2 if not p is  None and not p2[-1] in p]
                
                return paths1 + paths2
    # p1: [n1, n2, ...]
    # p2: [m1, m2, ...]
    # return  merged [x1, x2, ...]
    def randomMergeTwoPaths(self, p1, p2):
        #print("randomMergeTwoPaths: ", p1)
        #print("randomMergeTwoPaths: ", p2)
        if p1 is None or len(p1)==0:
            return p2
        elif p2 is None or len(p2)==0:
            return p1
        else:
            if p1[-1] == p2[-1]:
                path = self.randomMergeTwoPaths(p1[:-1], p2[:-1])
                #print("S0: ", path)
                if  not p1[-1] in path:
                    path += [ p1[-1] ]
                #print("S0: AFter: ", path)
                return path
            else:
                rr = random.randint(0, 1)
                if rr==0:
                    path = self.randomMergeTwoPaths(p1[:-1], p2)
                    #print("S1: ", path)
                    if not p1[-1] in path:
                        path += [ p1[-1] ]
                    #print("S1: AFter: ", path)    
                else:                  
                    path = self.randomMergeTwoPaths( p1, p2[:-1])
                    #print("S2: ", path)
                    if not p2[-1] in path:
                        path += [ p2[-1] ]
                    #print("S2: AFter: ", path)                                
                return path
                           
                           
# merged all paths into one, generate all these sequences
# paths = [p1, p2, ...]
    def genMergePaths(self):
        paths = self.findRootPaths()
        #print("Root Paths: ", paths)
        res=[] # [ p1, p2, ......]
        
        for p in paths:
            if len(res)==0:
                res = [p]
            else:
                parts=[]
                for pp in res: 
                    x =  self.mergeTwoPaths(pp, p)                   
                    parts += x
                res = parts   
        return res
    
    def genRandomMergePaths(self):
        paths = self.findRootPaths()
        print("Root Paths: ", paths)
        res=[] # [ p1, p2, ......]
        
        for p in paths:
            if len(res)==0:
                res = p
            else:
                res =  self.randomMergeTwoPaths(res, p)                    

        print("genRandomMergePaths: ", res)
        return res
   
    def display(self, level):
        super().display(level)
        n = (level -1) * 3
        levelspaces = ' ' * n
        
        print(levelspaces, "Display CG: ", type(self.treenode).__name__)
        print(levelspaces, "Display Local Variable Data in this CG:")
        for db in self.localvariables:
            db.display(level+1)
        print(levelspaces, "Display Created Data in this CG:")
        for db in self.datatbl:
            db.display(level+1)
        print(levelspaces, "Display Comp Nodes in this CG:")
        for sg in self.graph:
            sg.display(level+1)

    def gatherInputs(self):
        inputs=[]
        varSet = set()
        
        for cmp in self.graph: #from front to rear
            for inp in cmp.inputs:
                member = inp.member
                qualifier = inp.qualifier
                if member=="" and qualifier=="": continue #skip dummy variable
                if cmp.type=="CG" and  cmp.isLocalVariable(member, qualifier) : continue #skip local variables
                if not inp in self.datatbl: #external
                    if  not (member, qualifier) in varSet:
                        varSet.add( (member, qualifier) )
                        inputs.append(inp)
        return inputs
        
    def gatherOutputs(self):
        outputs=[]
        varSet = set()
        
        for cmp in self.graph[-1::-1]: #from rear to front
            for oup in cmp.outputs:
                member = oup.member
                qualifier = oup.qualifier
                if member=="" and qualifier=="": continue #skip dummy variable
                if cmp.type=="CG" and  cmp.isLocalVariable(member, qualifier) : continue #skip local variables
            #if not  oup in self.datatbl: #external
                if not (member, qualifier) in varSet:
                    varSet.add( (member, qualifier) )
                    outputs.append(oup)
        return outputs
                
                
            
#return a list of cmpnodes
    def createGraph(self, tnode):
        if type(tnode).__name__ == "CompilationUnit": #create a CG without inputs/output
            subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)           
            imports= tnode.__getattribute__("imports")
            package= tnode.__getattribute__("package")
            types = tnode.__getattribute__("types")
            
            for cnode in types: #class declaration
                self.createGraph(cnode)
            #insert this CG as a CompNode
            inputs = subCG.gatherInputs()
            outputs = subCG.gatherOutputs()
            #self.insertCGNode(subCG, inputs, outputs)

            return

        elif type(tnode).__name__ == "TryStatement": #create a CG without inputs/output
            subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)
            tryblock = tnode.__getattribute__("block") # None or list
            catches = tnode.__getattribute__("catches")  # None or list
            finally_block = tnode.__getattribute__("finally_block")  # None or list

            #create dummmy data as output of the then block
            dn =  CGData("", "")  #but not insert to graph's data space           
            for tryb in tryblock:
                subCG.createGraph(tryb)
                subCG.graph[-1].outputs.append(dn)
                    
            
            for catch in catches:
                subCG.createGraph(catch)
                #add the dummmy data to the input of the catch block
                subCG.graph[-1].inputs.append(dn)
                #create dummmy data as output of the then block
                dn =  CGData("", "")  #but not insert to graph's data space
                subCG.graph[-1].outputs.append(dn)


            for fb in finally_block:
                subCG.createGraph(fb)
                subCG.graph[-1].inputs.append(dn)
                
            #insert this CG as a CompNode
            inputs = subCG.gatherInputs()
            outputs = subCG.gatherOutputs()
            self.insertCGNode(subCG, inputs, outputs)
            for o in outputs:
                if not subCG.isLocalVariable(o.member, o.qualifier): self.datatbl.append(o) #upward output variables

            return
        
        elif type(tnode).__name__ == "CatchClause":
             subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)

             block = tnode.__getattribute__("block") # None or list
             parameter = tnode.__getattribute__("parameter")  # None or object
             inputs = self.createGraph(parameter)
             self.createGraph(block)
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             self.insertCGNode(subCG, inputs, outputs)

             return            

        elif type(tnode).__name__ == "CatchClauseParameter": #create data nodes
            member =  tnode.__getattribute__("name")
            qualifier = tnode.__getattribute__("types")
            qualifier = ".".join(qualifier)
            #search the data node
            dn = self.lookforDataNode(member, qualifier)
            if dn is None:
                dn =  CGData(member, qualifier)  #but not insert to graph's data space
            inputs=[dn]
            if (qualifier != ""):
                dn = self.lookforDataNode(qualifier , "")
                if not dn is None and not dn in inputs:
                    inputs += [dn]    
            for d in inputs:
                self.localvariables.append(d)
            return inputs
        
        elif type(tnode).__name__ == "ConstructorDeclaration": #create a sub CG  # constructor XXX()
             subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)
             classname = tnode.__getattribute__("name")
             parameters = tnode.__getattribute__("parameters")
             modifiers = tnode.__getattribute__("modifiers")
             body = tnode.__getattribute__("body")
             
             for parm in parameters:
                 subCG.createGraph(parm)
                     
             for cnode in body:
                 subCG.createGraph(cnode)

            #insert this CG as a CompNode
             inputs = subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             self.insertCGNode(subCG, inputs, outputs)

             return 
                  
        elif type(tnode).__name__ == "ClassDeclaration": #create a CG without inputs/output
            subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)
            name= tnode.__getattribute__("name")   #class name         
            body =tnode.__getattribute__("body")
            for cmp in body:
                subCG.createGraph(cmp) 
            #insert this CG as a CompNode
            inputs = subCG.gatherInputs()
            outputs = subCG.gatherOutputs()
            self.insertCGNode(subCG, inputs, outputs)

            return
        
        elif type(tnode).__name__ == "ClassCreator": #create a CompNode  # new XXX()
             reftype = tnode.__getattribute__("type")
             arguments = tnode.__getattribute__("arguments")
             selectors = tnode.__getattribute__("selectors")
             inputs = []
             for arg in arguments:
                 x = self.createGraph(arg)
                 for e in x:
                     if not e in inputs: inputs.append(e)
             #print("ClassCreator inputs: ", inputs)
             #self.insertCompNode(tnode, inputs, [])

             return inputs
        elif type(tnode).__name__ == "FieldDeclaration":
             
             declarators = tnode.__getattribute__("declarators")
             inputs=[]
             outputs=[]
             for vardec in declarators: #declare a set of object data
                name =  vardec.__getattribute__("name")
                dn = self.insertDataNode(name, "")
                self.localvariables.append(dn)
                outputs.append(dn )   #var name: outpus of the CmpNode               
                initializer= vardec.__getattribute__("initializer")   #initializer: inputs of the CmpNode                
                inputs += self.createGraph(initializer)
             cmpNode = self.insertCompNode( tnode, inputs, outputs)
             return 
                                     
        elif type(tnode).__name__ == "Literal": #return empty data node
             value= tnode.__getattribute__("value")   #var value  
             qualifier= tnode.__getattribute__("qualifier")   #var qualifier
             return [] 
           

        elif type(tnode).__name__ == "MethodDeclaration": #create a sub CG
             subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)
             name = tnode.__getattribute__("name") #function name
             parameters = tnode.__getattribute__("parameters") # declare variables
             bd = tnode.__getattribute__("body")
             
             for parm in parameters:
                 subCG.createGraph(parm)

                     
             for cnode in bd:
                 subCG.createGraph(cnode)

             #insert this CG as a CompNode
             inputs = subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             self.insertCGNode(subCG, inputs, outputs)


             return 
            
        elif type(tnode).__name__ == "FormalParameter": #create a data node
             name = tnode.__getattribute__("name") #arg name
             dn = self.insertDataNode(name, "")
             self.localvariables.append(dn)
             inputs=[]
             outputs = [ dn ]
             #cmpNode = self.insertCompNode( tnode, inputs, outputs)
             return 
            
        elif type(tnode).__name__ == "LocalVariableDeclaration" : #create a CompNode
             declarators = tnode.__getattribute__("declarators")
             inputs=[]
             outputs=[]
             for vardec in declarators: #declare a set of object data
                name =  vardec.__getattribute__("name")
                dn = self.insertDataNode(name, "")
                self.localvariables.append(dn)
                outputs.append(dn )   #var name: outpus of the CmpNode                 
                              
                initializer= vardec.__getattribute__("initializer")   #initializer: inputs of the CmpNode
                ips = self.createGraph(initializer)
                if not ips is None:
                    inputs += ips
             cmpNode = self.insertCompNode( tnode, inputs, outputs)
             return 
        elif type(tnode).__name__ == "VariableDeclaration": #Do NOT create a CompNode
             declarators = tnode.__getattribute__("declarators")
             inputs=[]
             outputs=[]
             for vardec in declarators: #declare a set of object data
                name =  vardec.__getattribute__("name")
                dn = self.insertDataNode(name, "")
                self.localvariables.append(dn)
                outputs.append(dn )   #var name: outpus of the CmpNode                 
                              
                initializer= vardec.__getattribute__("initializer")   #initializer: inputs of the CmpNode
                ips = self.createGraph(initializer)
                if not ips is None:
                    inputs += ips
            #cmpNode = self.insertCompNode( tnode, inputs, outputs)
                self.inputs += inputs
                self.outputs += outputs
             return inputs
                   
                   
        elif type(tnode).__name__ == "StatementExpression":
             subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)
             expression = tnode.__getattribute__("expression")
             inputs = subCG.createGraph(expression)
             if inputs is None: inputs=[]
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             self.insertCGNode(subCG, inputs, outputs)
             # data created need to be passed upward one level
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)
             return 
        elif type(tnode).__name__ == "DoStatement":   ## *****
            subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)  #CG for Do-Statement

            condition = tnode.__getattribute__("condition")
            body = tnode.__getattribute__("body")
            
            subCG.createGraph(condition) #can not swith order with body, otherwise, it will cause loop forever
            subCG.createGraph(body)

            #insert this CG as a CompNode
            inputs = subCG.gatherInputs() 
            outputs = subCG.gatherOutputs()
            self.insertCGNode(subCG, inputs, outputs)
            for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)
            return
        
        elif type(tnode).__name__ == "WhileStatement":   ## *****
             subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self) #CG for While-Statement
             condition = tnode.__getattribute__("condition")
             body = tnode.__getattribute__("body")
             
             subCG.createGraph(condition) # inputs of the conditional part
             subCG.createGraph(body)
 
             #insert this CG as a CompNode
             inputs = subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             self.insertCGNode(subCG, inputs, outputs)
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)
             return
                       
        elif type(tnode).__name__ == "ForStatement": #create a sub CG  *****
             subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self) #CG for For-Statement             
             body = tnode.__getattribute__("body")
             control = tnode.__getattribute__("control")              
                     
             subCG.createGraph(control)
             subCG.createGraph(body)

             #insert this CG as a CompNode
             inputs = subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             self.insertCGNode(subCG, inputs, outputs)
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)
             return 

        elif type(tnode).__name__ == "ForControl":   ## ***** did not generate a for-control block
             condition = tnode.__getattribute__("condition")
             init = tnode.__getattribute__("init") # init may be object of list
             update = tnode.__getattribute__("update")

             self.createGraph(init)
             inputs = self.createGraph(condition) #No outputs    
             inputs += self.createGraph(update)
             self.inputs += inputs

             return inputs

        elif type(tnode).__name__ == "BlockStatement":  ## *****
             subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)
           
             statements = tnode.__getattribute__("statements")
             for statement in statements:
                 subCG.createGraph(statement)
             #insert this CG as a CompNode
             inputs = subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             self.insertCGNode(subCG, inputs, outputs)
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)

             return
            
        
        elif type(tnode).__name__ == "SwitchStatement":   ## ***** create a CG
             subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)
             cases = tnode.__getattribute__("cases")
             expression = tnode.__getattribute__("expression") #generate inputs

             inputs = subCG.createGraph(expression) #control variable of the switch statement
             for case in cases:
                 subCG.createGraph(case)
             
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             self.insertCGNode(subCG, inputs, outputs)
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)

             return

        elif type(tnode).__name__ == "SwitchStatementCase":   ## ***** create a CG
             subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)
             case = tnode.__getattribute__("case") 
             statements = tnode.__getattribute__("statements")
             inputs=[]
             outputs=[]
             for c in case:
                 subCG.createGraph(case)
                 
             for statement in statements:    
                 subCG.createGraph(statement)
                 outputs += subCG.gatherOutputs()
                 subCG.outputs = outputs
                 
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             self.insertCGNode(subCG, inputs, outputs)
             return
            
        elif type(tnode).__name__ == "BreakStatement":
             inputs = []
             print("*********** ", self.outputs)
             for e in self.outputs:
                 if not e in inputs: inputs.append(e)
             cmpNode = self.insertCompNode( tnode, inputs, [])
             return
            
        elif type(tnode).__name__ == "IfStatement":   ## *****
             subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)
             condition = tnode.__getattribute__("condition")
             then_statement = tnode.__getattribute__("then_statement")
             else_statement = tnode.__getattribute__("else_statement")
             
             inputs = subCG.createGraph(condition)
             
             subCG.createGraph(then_statement)
             #create dummmy data as output of the then block
             dn =  CGData("", "")  #but not insert to graph's data space
             subCG.graph[-1].outputs.append(dn)
           
             subCG.createGraph(else_statement)
             #add the dummmy data to the input of the else block
             subCG.graph[-1].inputs.append(dn)
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             self.insertCGNode(subCG, inputs, outputs)
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)

             return
        elif type(tnode).__name__ == "TernaryExpression":   ## *****
             subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)
             condition = tnode.__getattribute__("condition")
             if_false = tnode.__getattribute__("if_false")
             if_true = tnode.__getattribute__("if_true")
             
             inputs = subCG.createGraph(condition)
             subCG.createGraph(if_true)
             subCG.createGraph(if_false)
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             self.insertCGNode(subCG, inputs, outputs)

             return
         
        elif type(tnode).__name__ == "MethodInvocation": #create a CompNode
             qualifier =  tnode.__getattribute__("qualifier")
             arguments =  tnode.__getattribute__("arguments")
             member =  tnode.__getattribute__("member")
             inputs = []
             dn = self.lookforDataNode( qualifier, "")
             if not dn is None: inputs.append(dn)
             for arg in arguments:
                inputs += self.createGraph(arg)

             #self.insertCompNode(tnode, inputs, [])  # ???????

             return inputs
            
        elif type(tnode).__name__ == "ReturnStatement": #create a CompNode
             expression = tnode.__getattribute__("expression")
             inputs = self.createGraph(expression)
             self.insertCompNode(tnode, inputs, [])

        elif type(tnode).__name__ == "SuperConstructorInvocation": #create a CompNode
             arguments = tnode.__getattribute__("arguments")
            
             inputs = []
             for arg in arguments:
                 inputs +=self.createGraph(arg)
                
             self.insertCompNode(tnode, inputs, [])
                
            
        elif type(tnode).__name__ == "Assignment": #create a CompNode
             expressionl =  tnode.__getattribute__("expressionl")
             outputs =  self.createGraph(expressionl)
             newoutputs = []
             inputs = []
             value =  tnode.__getattribute__("value")
             inputs += self.createGraph(value)
           
             for o in outputs: # create new data node
                #First search the data node
                dn = self.lookforDataNode(o.member, o.qualifier)
                if not dn is None and not dn in inputs:
                    inputs += [dn]
                if (o.qualifier != ""):
                    dn = self.lookforDataNode(o.qualifier , "")
                    if not dn is None and not dn in inputs:
                        inputs += [dn]                    
                #Now, create new outputs                
                newoutputs.append( self.insertDataNode(o.member, o.qualifier) )
              
 
             self.insertCompNode(tnode, inputs, newoutputs)
            
        elif type(tnode).__name__ == "BinaryOperation": #createdata nodes
             operandl =  tnode.__getattribute__("operandl") #inputs
             operandr =  tnode.__getattribute__("operandr") #inputs
             inputs = self.createGraph(operandl)
             inputs += self.createGraph(operandr)
            
             return inputs
        
        elif type(tnode).__name__ == "ArraySelector": #createdata nodes
             index =  tnode.__getattribute__("index")
             inputs = self.createGraph(index)  
             return inputs
            
        elif type(tnode).__name__ == "MemberReference": #createdata nodes
            member =  tnode.__getattribute__("member")
            qualifier = tnode.__getattribute__("qualifier")

            #search the data node
            dn = self.lookforDataNode(member, qualifier)
            if dn is None:
                dn =  CGData(member, qualifier)  #but not insert to graph's data space
            inputs=[dn]
            if (qualifier != ""):
                dn = self.lookforDataNode(qualifier , "")
                if not dn is None and not dn in inputs:
                    inputs += [dn]    
            return inputs
        
        else:
            return []

#Recursively permutate the order of the nodes in graph[]
def genCDpermutations(cgnode):
    #first, permute at this level of cgnode
    if cgnode.type=="CGComp": return
    if len(cgnode.graph) == 0: return
    cgnode.graph  = cgnode.genRandomMergePaths() #generate permutation at this level
    for elem in cgnode.graph:
        genCDpermutations(elem) # permute nodes in each sub cg node's graph array

def changeTreePermutationNode(cgnode):
    tnode = cgnode.treenode
    #print("type of tnode:  ",type(tnode).__name__)
    if type(tnode).__name__ == "CompilationUnit":
        return
    elif type(tnode).__name__ == "ClassDeclaration":
        path = [node.treenode for node in cgnode.graph]
        tnode.__setattr__("body",  path)
    elif type(tnode).__name__ == "MethodDeclaration":
        path = [node.treenode for node in cgnode.graph]
        tnode.__setattr__("body",  path)
    elif type(tnode).__name__ == "ConstructorDeclaration":
        path = [node.treenode for node in cgnode.graph]
        tnode.__setattr__("body",  path)
    elif type(tnode).__name__ == "WhileStatement":
        body = tnode.__getattribute__("body")
        blockstatement_cgnode = cgnode.graph[0]
        path = [node.treenode for node in  blockstatement_cgnode.graph] 
        body.__setattr__("statements",  path)
    elif type(tnode).__name__ == "DoStatement":
        body = tnode.__getattribute__("body")
        blockstatement_cgnode = cgnode.graph[0]
        path = [node.treenode for node in  blockstatement_cgnode.graph] 
        body.__setattr__("statements",  path)

    elif type(tnode).__name__ == "ForStatement":
        body = tnode.__getattribute__("body")
        blockstatement_cgnode = cgnode.graph[0]
        path = [node.treenode for node in  blockstatement_cgnode.graph] 
        body.__setattr__("statements",  path)
        
        
    elif type(tnode).__name__ == "IfStatement":
        then_statement = tnode.__getattribute__("then_statement")
        else_statement = tnode.__getattribute__("else_statement")
        then_block = cgnode.graph[0]
        else_block=None
        if (len(cgnode.graph) >1): else_block = cgnode.graph[1]
        
        path = [node.treenode for node in then_block.graph]
        then_statement.__setattr__("statements",  path)
        
        if not else_block is None:
            if type(else_statement).__name__ == "BlockStatement":
                path = [node.treenode for node in else_block.graph]
                else_statement.__setattr__("statements",  path)
            else:
                changeTreePermutationNode(else_block)

    elif type(tnode).__name__ == "TryStatement":

        tryBlockPath=[]
        catchBlockPath=[]
        finallyBlockPath=[]
        statecode = "Add Try Block"
        for node in cgnode.graph:
            treeNode = node.treenode
            #print("Adding ", type(treeNode).__name__)
            #print ("Current state: ", statecode)
            if statecode=="Add Try Block" and type(treeNode).__name__ != "CatchClause": 
                tryBlockPath.append(treeNode)
                #print("to tryB " )
            elif statecode=="Add Try Block" and type(treeNode).__name__ == "CatchClause":
                statecode="Add Catch Block"
                catchBlockPath.append(treeNode)
                #print("to catchB " )
            elif statecode=="Add Catch Block" and type(treeNode).__name__ == "CatchClause":
                catchBlockPath.append(treeNode)
                #print("to catchB " )
            elif statecode=="Add Catch Block" and type(treeNode).__name__ != "CatchClause":
                statecode = "Add Finally Block"
                finallyBlockPath.append(treeNode)
                #print("to finally B " )
            else:
                finallyBlockPath.append(treeNode)
                #print("to finally B " )
                
        tnode.__setattr__("block",  tryBlockPath)
        tnode.__setattr__("catches",  catchBlockPath)
        tnode.__setattr__("finally_block",  finallyBlockPath)
           
    elif type(tnode).__name__ == "CatchClause":
        path = [node.treenode for node in cgnode.graph]
        tnode.__setattr__("block",  path)
    elif type(tnode).__name__ == "SwitchStatementCase":
        path = [node.treenode for node in cgnode.graph]
        tnode.__setattr__("statements",  path)
       
        

    elif type(tnode).__name__ == "StatementExpression":
        path = [node.treenode for node in cgnode.graph]
        tnode.__setattr__("expression",  path[0])
    

        
#Recursively change the permutations according to the cg graph
def changeTreePermutation(cg):
    if cg.type == "CGComp": return
    if len(cg.graph)==0: return
    changeTreePermutationNode(cg) # change tree node of this level
    for cgnode in cg.graph:
        changeTreePermutation(cgnode)  # change sub cg nodes

def cdPermuteCode(tree):
    cg = CG(tree) #eatablish a CG graph
    cg.createGraph(tree) #recursively construct the CG graph    
    genCDpermutations(cg)
    #cg.display(1) #level 1 indented
    changeTreePermutation(cg) # change the tree according to cg        
    #newCode = pyd.toJava(tree) #  string 
    return tree 
	
if __name__ == '__main__' :
    sourcefile = 'Main9.java'
    with open(sourcefile, 'r', encoding='utf8') as myFile:
        data = myFile.read()
        tree = javalang.parse.parse(data)
        print(tree)
        cg = CG(tree) #eatablish a CG graph
        cg.createGraph(tree) #recursively construct the CG graph
        cg.display(1) #level 1 indented


        for i in range(1): #generate N CG permutations            
            #path =  cg.graph[0].graph[0].genRandomMergePaths() #CompilationUnit/ClassDeclaration/MethodDeclaration
            #print("PATH: ", path)
            #for p in path:
                #print("  ", type(p.treenode).__name__, p)
                
            # generate one permutation of the CD graph
            print("+++++++++++++++++++++++++++++++")
            genCDpermutations(cg)
            cg.display(1) #level 1 indented
            changeTreePermutation(cg) # change the tree according to cg
            print(tree)
            newCode = pyd.toJava(tree) #  string 
            print(newCode)
            #print(type(newCode))


