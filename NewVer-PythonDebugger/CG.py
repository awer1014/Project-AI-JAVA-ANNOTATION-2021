import sys
import javalang
import ast
import random, string
import os
import chardet
import glob
from chardet.universaldetector import UniversalDetector
import python_debugger as pyd

sys.setrecursionlimit(1000000000)
detector = UniversalDetector()
def  MyMerge(outputs, inputs):
    for e in outputs:
        if not e: continue #skip []
        if not e in inputs: inputs.append(e)
    return inputs

def searchDummyDataNode(outputs):
    ll = []
    for e in outputs:
        if e.member=="" and e.qualifier=="": ll.append(e)
    return ll
	
def myListAdd(l1, l2):
    #print("l1:", l1)
    #print("l2:",l2)
    l1 += l2
    #print("l1+l2:",l1)
    return list(set(l1))
	
class CGData:
    def __init__(self, member, qualifier):
        self.member = member
        self.qualifier = qualifier
        
    def display(self, level):
        n = (level -1) * 3
        levelspaces = ' ' * n
        myprint(levelspaces, self)
        myprint(levelspaces, "member: ", self.member)
        myprint(levelspaces, "qualifier: ", self.qualifier)
    
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

        myprint(levelspaces, "Display Comp Node ", type(self.treenode).__name__, self)
        
        myprint(levelspaces, "Display Inputs to the Comp Node...")        
        if not (self.inputs is None):
            for inps in self.inputs:
                if not inps: continue
                if not type(inps) is list:
                    inps=[inps]
                for inp in inps:
                    inp.display(level+1)
                       
        myprint(levelspaces, "Display Outputs from the Comp Node...")        
        if not (self.outputs is None):
            for outp in self.outputs:
                if not outp: continue                
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
                next_cnodes = [cnode  for cnode in self.graph if cnode != cmpd and not(cnode.inputs is None) and datanode in cnode.inputs]
                #if(len(next_cnodes) == 1):next_cnodes[0].display(1)
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
        roots = [ cnode for cnode in self.graph if cnode.inputs is None or len([ x for x in cnode.inputs if  x in self.outputs] ) == 0]
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
        myprint("Root Paths: ", paths)
        res=[] # [ p1, p2, ......]
        
        for p in paths:
            if len(res)==0:
                res = p
            else:
                res =  self.randomMergeTwoPaths(res, p)                    

        myprint("genRandomMergePaths: ", res)
        return res
   
    def display(self, level):
        super().display(level)
        n = (level -1) * 3
        levelspaces = ' ' * n
        
        myprint(levelspaces, "Display CG: ", type(self.treenode).__name__)
        myprint(levelspaces, "Display Local Variable Data in this CG:")
        for db in self.localvariables:
            db.display(level+1)
        myprint(levelspaces, "Display Created Data in this CG:")
        for db in self.datatbl:
            db.display(level+1)
        myprint(levelspaces, "Display Comp Nodes in this CG:")
        for sg in self.graph:
            sg.display(level+1)

    def gatherInputs(self):
        inputs=[]
        varSet = set()
        
        for cmp in self.graph: #from front to rear
            if cmp.inputs is None:continue
            for inps in cmp.inputs:
                if  (not inps): continue #skip []			
                if not type(inps) is list:
                    inps=[inps]
                for inp in inps:
                    member = inp.member
                    qualifier = inp.qualifier
                    if member=="" and qualifier=="": continue #skip dummy variable
                    if cmp.type=="CG" and  cmp.isLocalVariable(member, qualifier) : continue #skip local variables
                    if not inp in self.datatbl: #external
                        if  not (member, qualifier) in varSet:
                            varSet.add( (member, qualifier) )
                            if not(not inp): inputs.append(inp)
        return inputs
        
    def gatherOutputs(self):
        outputs=[]
        varSet = set()
        
        for cmp in self.graph[-1::-1]: #from rear to front
            for oup in cmp.outputs:
                if  (not oup): continue #skip []			
                member = oup.member
                qualifier = oup.qualifier
                #if member=="" and qualifier=="": continue #skip dummy variable
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
            inputs = searchDummyDataNode(self.outputs)
            subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self)
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
                if not( not dn): subCG.graph[-1].inputs.append(dn)
                #create dummmy data as output of the then block
                dn =  CGData("", "")  #but not insert to graph's data space
                subCG.graph[-1].outputs.append(dn)


            for fb in finally_block:
                subCG.createGraph(fb)
                if not (not dn): subCG.graph[-1].inputs.append(dn)
                
            #insert this CG as a CompNode
            inputs = subCG.gatherInputs()
            outputs = subCG.gatherOutputs()
            if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                inputs = MyMerge(self.outputs, inputs)			 
            inputs = list(set(inputs))
            outputs = list(set(outputs))
            self.insertCGNode(subCG, inputs, outputs)
            for o in outputs:
                if not subCG.isLocalVariable(o.member, o.qualifier): self.datatbl.append(o) #upward output variables

            return
        
        elif type(tnode).__name__ == "CatchClause":
             inputs = searchDummyDataNode(self.outputs)
             subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self)

             block = tnode.__getattribute__("block") # None or list
             parameter = tnode.__getattribute__("parameter")  # None or object
             inputs = self.createGraph(parameter)
             self.createGraph(block)
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                inputs = MyMerge(self.outputs, inputs)			 
             inputs = list(set(inputs))
             outputs = list(set(outputs))
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
            return inputs,[]
        
        elif type(tnode).__name__ == "ConstructorDeclaration": #create a sub CG  # constructor XXX()
             inputs = searchDummyDataNode(self.outputs)
             subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self)
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
             if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                inputs = MyMerge(self.outputs, inputs)			 
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.insertCGNode(subCG, inputs, outputs)
             return 
                  
        elif type(tnode).__name__ == "ClassDeclaration": #create a CG without inputs/output
            subCG = CG(tnode,  inputs=[], outputs=[], referencedgraph=self)
            name= tnode.__getattribute__("name")   #class name         
            body =tnode.__getattribute__("body")
            if not body is None:
                for cmp in body:
                    #print("CG: cmp name: ",type(cmp).__name__ )				
                    if type(cmp) is list and len(cmp)>0: # a block statement here
                        inputs = searchDummyDataNode(subCG.outputs)
                        subCG2 = CG(cmp,  inputs=inputs, outputs=[], referencedgraph=subCG)
                        for sbd in cmp:
                            subCG2.createGraph(sbd)
                        inputs = subCG2.gatherInputs()
                        outputs = subCG2.gatherOutputs()
                        if len( searchDummyDataNode(outputs) )>0: 
                            inputs = MyMerge(self.outputs, inputs)			 
                        inputs = list(set(inputs))
                        outputs = list(set(outputs))
                        subCG.insertCGNode(subCG2, inputs, outputs)
                        subCG.inputs = myListAdd(subCG.inputs, inputs)
                        subCG.outputs = myListAdd(subCG.outputs, outputs)
                        for d in outputs:
                            if not subCG2.isLocalVariable(d.member, d.qualifier):
                                subCG.datatbl.append(d)
 
                    else:    
                        subCG.createGraph(cmp)
            #insert this CG as a CompNode
            inputs = subCG.gatherInputs()
            outputs = subCG.gatherOutputs()
            if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                inputs = MyMerge(self.outputs, inputs)			 
            inputs = list(set(inputs))
            outputs = list(set(outputs))
            self.insertCGNode(subCG, inputs, outputs)

            return

        elif type(tnode).__name__ == "ClassCreator": #create a CompNode  # new XXX()
             reftype = tnode.__getattribute__("type")
             arguments = tnode.__getattribute__("arguments")
             selectors = tnode.__getattribute__("selectors")
             inputs = searchDummyDataNode(self.outputs)
             for arg in arguments:
                 x, y = self.createGraph(arg)
                 for e in x:
                     if not e: continue
                     if not e in inputs: inputs.append(e)
             myprint("ClassCreator inputs: ", inputs)
             #self.insertCompNode(tnode, inputs, [])

             return inputs, []
        elif type(tnode).__name__ == "FieldDeclaration":
             
             declarators = tnode.__getattribute__("declarators")
             inputs = searchDummyDataNode(self.outputs)
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
             return [],[] 
           

        elif type(tnode).__name__ == "MethodDeclaration": #create a sub CG
             inputs = searchDummyDataNode(self.outputs)
             subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self)
             name = tnode.__getattribute__("name") #function name
             parameters = tnode.__getattribute__("parameters") # declare variables
             bd = tnode.__getattribute__("body")
             
             if not parameters is None:
                 for parm in parameters:
                    subCG.createGraph(parm)

                     
             if not bd is None:
                 for cnode in bd:
                    subCG.createGraph(cnode)

             #insert this CG as a CompNode
             inputs = subCG.gatherInputs()
             outputs = subCG.gatherOutputs()			 
             inputs = list(set(inputs))
             outputs = list(set(outputs))
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
             inputs = searchDummyDataNode(self.outputs)
             myprint("LocalVariableDeclaration inputs Dummy: ", inputs)
             outputs=[]
             for vardec in declarators: #declare a set of object data
                name =  vardec.__getattribute__("name")
                dn = self.insertDataNode(name, "")
                self.localvariables.append(dn)
                outputs.append(dn )   #var name: outpus of the CmpNode                 
                              
                initializer= vardec.__getattribute__("initializer")   #initializer: inputs of the CmpNode
                ips,_ = self.createGraph(initializer)
                if not (ips is None) :
                    inputs += ips
             #print("LocalVariableDeclaration inputs: ", inputs)
             #print("LocalVariableDeclaration outputs: ", outputs)
             cmpNode = self.insertCompNode( tnode, inputs, outputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
			 
             return 
        elif type(tnode).__name__ == "VariableDeclaration": #Do NOT create a CompNode
             declarators = tnode.__getattribute__("declarators")
             inputs = searchDummyDataNode(self.outputs)
             outputs=[]
             for vardec in declarators: #declare a set of object data
                name =  vardec.__getattribute__("name")
                dn = self.insertDataNode(name, "")
                self.localvariables.append(dn)
                outputs.append(dn )   #var name: outpus of the CmpNode                 
                              
                initializer= vardec.__getattribute__("initializer")   #initializer: inputs of the CmpNode
                ips, _ = self.createGraph(initializer)
                if not ips is None:
                    inputs += ips
            #cmpNode = self.insertCompNode( tnode, inputs, outputs)
                self.inputs += inputs
                self.outputs += outputs
             return inputs, []
                   
                   
        elif type(tnode).__name__ == "StatementExpression":
             inputs = searchDummyDataNode(self.outputs)
             subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self)
             expression = tnode.__getattribute__("expression")
             myprint("expression: ", expression)
             outputs=[]
             if not expression is None:
                 xx=subCG.createGraph(expression)
                 if not xx is None:
                     inputs, outputs = xx
             if inputs is None: inputs=[]
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs += subCG.gatherOutputs()
             if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                 inputs = MyMerge(self.outputs, inputs)			 
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.insertCGNode(subCG, inputs, outputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
			 
             # data created need to be passed upward one level
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)
             myprint("$##$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$")
             subCG.display(1)
             myprint(subCG.treenode)
             return 
        elif type(tnode).__name__ == "DoStatement":   ## *****
            inputs = searchDummyDataNode(self.outputs)
            subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self)  #CG for Do-Statement

            condition = tnode.__getattribute__("condition")
            body = tnode.__getattribute__("body")
            
            subCG.createGraph(condition) #can not swith order with body, otherwise, it will cause loop forever
            subCG.createGraph(body)

            #insert this CG as a CompNode
            inputs = subCG.gatherInputs() 
            outputs = subCG.gatherOutputs()
            if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                 inputs = MyMerge(self.outputs, inputs)			 
            inputs = list(set(inputs))
            outputs = list(set(outputs))
            self.insertCGNode(subCG, inputs, outputs)
            self.inputs = myListAdd(self.inputs, inputs)
            self.outputs = myListAdd(self.outputs, outputs)
            for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)
            return
        
        elif type(tnode).__name__ == "WhileStatement":   ## *****
             inputs = searchDummyDataNode(self.outputs)
             subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self) #CG for While-Statement
             condition = tnode.__getattribute__("condition")
             body = tnode.__getattribute__("body")
             
             subCG.createGraph(condition) # inputs of the conditional part
             subCG.createGraph(body)
 
             #insert this CG as a CompNode
             inputs = subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                 inputs = MyMerge(self.outputs, inputs)			 
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.insertCGNode(subCG, inputs, outputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)
             return
                       
        elif type(tnode).__name__ == "ForStatement": #create a sub CG  *****
             inputs = searchDummyDataNode(self.outputs)
             subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self) #CG for For-Statement             
             body = tnode.__getattribute__("body")
             control = tnode.__getattribute__("control")              
                     
             subCG.createGraph(control)
             subCG.createGraph(body)

             #insert this CG as a CompNode
             inputs = subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                 inputs = MyMerge(self.outputs, inputs)			 
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.insertCGNode(subCG, inputs, outputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)
             return 

        elif type(tnode).__name__ == "ForControl":   ## ***** did not generate a for-control block
             condition = tnode.__getattribute__("condition")
             init = tnode.__getattribute__("init") # init may be object of list
             update = tnode.__getattribute__("update")
             input1 = searchDummyDataNode(self.outputs)
             self.createGraph(init)
             inputs, outputs = self.createGraph(condition) #No outputs    
             inputs2, outputs2 = self.createGraph(update)
             inputs = myListAdd(inputs, input1)
             inputs = myListAdd(inputs, inputs2)
             outputs = myListAdd(outputs, outputs2)
			 
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)

             return inputs, outputs

        elif type(tnode).__name__ == "BlockStatement":  ## *****
             inputs = searchDummyDataNode(self.outputs)
             subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self)
           
             statements = tnode.__getattribute__("statements")
             for statement in statements:
                 subCG.createGraph(statement)
             #insert this CG as a CompNode
             inputs = subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                 inputs = MyMerge(self.outputs, inputs)			 
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.insertCGNode(subCG, inputs, outputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)

             return
            
        
        elif type(tnode).__name__ == "SwitchStatement":   ## ***** create a CG
             inputs = searchDummyDataNode(self.outputs)
             subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self)
             cases = tnode.__getattribute__("cases")
             expression = tnode.__getattribute__("expression") #generate inputs

             inputs,outputs = subCG.createGraph(expression) #control variable of the switch statement
             for case in cases:
                 subCG.createGraph(case)
             
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                 inputs = MyMerge(self.outputs, inputs)			 
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.insertCGNode(subCG, inputs, outputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)

             return

        elif type(tnode).__name__ == "SwitchStatementCase":   ## ***** create a CG
             inputs = searchDummyDataNode(self.outputs)
             subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self)
             case = tnode.__getattribute__("case") 
             statements = tnode.__getattribute__("statements")
             inputs=[]
             outputs=[]
             for c in case:
                 subCG.createGraph(case)
                 
             for statement in statements:    
                 subCG.createGraph(statement)
                 outputs += subCG.gatherOutputs()
                 outputs = list(set(outputs))

             subCG.outputs = outputs
                 
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                 inputs = MyMerge(self.outputs, inputs)			 
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.insertCGNode(subCG, inputs, outputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
             return
            
        elif type(tnode).__name__ == "BreakStatement":
             inputs = searchDummyDataNode(self.outputs) #if none then assigned []
             myprint("*********** ", self.outputs)
             inputs = MyMerge(self.outputs, inputs)
             dn =  CGData("", "") #create a dummy data node
             cmpNode = self.insertCompNode( tnode, inputs, [dn])
             myprint("----------------------------- ", inputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, [dn])
             return inputs, [dn]
            
        elif type(tnode).__name__ == "IfStatement":   ## *****
             inputs = searchDummyDataNode(self.outputs)
             subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self)
             condition = tnode.__getattribute__("condition")
             then_statement = tnode.__getattribute__("then_statement")
             else_statement = tnode.__getattribute__("else_statement")
             
             inputs, outputs = subCG.createGraph(condition)
             myprint("XXXX after condtion subCG: len(subCG.graph):",len(subCG.graph) )
             
             subCG.createGraph(then_statement)
             myprint("XXXX aftern then subCG: len(subCG.graph):",len(subCG.graph) )
             #create dummmy data as output of the then block
             dn =  CGData("", "")  #but not insert to graph's data space
             subCG.graph[-1].outputs.append(dn)
           
             subCG.createGraph(else_statement)
             myprint("XXXX after else subCG: len(subCG.graph):",len(subCG.graph) )
             #add the dummmy data to the input of the else block
             if not(subCG.graph[-1].inputs  is None and not(not dn)):
                 subCG.graph[-1].inputs.append(dn)
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs += subCG.gatherOutputs()
             #if dummy exist in the outputs, then update inputs by adding self.inputs
             if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                 inputs = MyMerge(self.outputs, inputs)			 
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.insertCGNode(subCG, inputs, outputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
             for d in outputs:
                 #print("StatementExpression adding data  ", d, " to upper level: " , self.datatbl)
                 if not subCG.isLocalVariable(d.member, d.qualifier): self.datatbl.append(d)
             myprint(":::::::::::::::::::::::::::::::::::::: ",inputs)
             myprint(":::::::::::::::::::::::::::::::::::::: ",outputs)

             return
        elif type(tnode).__name__ == "TernaryExpression":   ## *****
             inputs = searchDummyDataNode(self.outputs)
             subCG = CG(tnode,  inputs=inputs, outputs=[], referencedgraph=self)
             condition = tnode.__getattribute__("condition")
             if_false = tnode.__getattribute__("if_false")
             if_true = tnode.__getattribute__("if_true")
             
             inputs = subCG.createGraph(condition)
             subCG.createGraph(if_true)
             subCG.createGraph(if_false)
             #insert this CG as a CompNode
             inputs += subCG.gatherInputs()
             outputs = subCG.gatherOutputs()
             if len( searchDummyDataNode(outputs) )>0: #append self.outputs to inputs
                 inputs = MyMerge(self.outputs, inputs)			 
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.insertCGNode(subCG, inputs, outputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)

             return
         
        elif type(tnode).__name__ == "MethodInvocation": #create a CompNode
             qualifier =  tnode.__getattribute__("qualifier")
             arguments =  tnode.__getattribute__("arguments")
             member =  tnode.__getattribute__("member")
             inputs = searchDummyDataNode(self.outputs)
             outputs = []
             dn = self.lookforDataNode( qualifier, "")
             if not dn is None and (not(not dn)): inputs.append(dn)
             for arg in arguments:
                t_inputs, t_outputs = self.createGraph(arg)
                inputs += t_inputs
                outputs += t_outputs

             #self.insertCompNode(tnode, inputs, [])  # ???????
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
             dn =  CGData("", "") #create a dummy data node
             return inputs, outputs + [dn]
            
        elif type(tnode).__name__ == "ReturnStatement": #create a CompNode
             inputs = searchDummyDataNode(self.outputs)
             expression = tnode.__getattribute__("expression")
             outputs=[]
             if not expression is None:
                 input1, outputs = self.createGraph(expression)
                 inputs = myListAdd(inputs, input1)
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             inputs = MyMerge(self.outputs, inputs)
             dn =  CGData("", "")
             outputs+= [dn]
             self.insertCompNode( tnode, inputs, outputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
             return #inputs, outputs
             
        elif type(tnode).__name__ == "SuperConstructorInvocation": #create a CompNode
             inputs = searchDummyDataNode(self.outputs) #if none then assigned []
             myprint("*********** ", self.outputs)
             inputs = MyMerge(self.outputs, inputs)
             dn =  CGData("", "") #create a dummy data node
			 
             arguments = tnode.__getattribute__("arguments")
             inputs = []
             for arg in arguments:
                 t_inputs,_ =self.createGraph(arg)
                 inputs += t_inputs
             inputs = list(set(inputs))    
             self.insertCompNode(tnode, inputs, [dn])
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, [dn])
             return inputs, [dn]
			 
            
        elif type(tnode).__name__ == "Assignment": #create a CompNode
             inputs = searchDummyDataNode(self.outputs)
             expressionl =  tnode.__getattribute__("expressionl")
             l_inputs, _ =  self.createGraph(expressionl)# reference of the input var at the left-hand side

             value =  tnode.__getattribute__("value")
             r_inputs, r_outputs = self.createGraph(value) # reference of the input var at the right-hand side
             outputs = r_outputs
             inputs = myListAdd(r_inputs, inputs)
             for o in l_inputs: # create new data node corrs. to the lef-hand var
                #First search the data node
                dn = self.lookforDataNode(o.member, o.qualifier)
                if not dn is None and not dn in inputs:
                    inputs += [dn]
                if (o.qualifier != ""):
                    dn = self.lookforDataNode(o.qualifier , "")
                    if not dn is None and not dn in inputs:
                        inputs += [dn]                    
                #Now, create new outputs                
                outputs.append( self.insertDataNode(o.member, o.qualifier) )
              
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.insertCompNode(tnode, inputs, outputs)
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
             return inputs, outputs
        elif type(tnode).__name__ == "BinaryOperation": #createdata nodes
             operandl =  tnode.__getattribute__("operandl") #inputs
             operandr =  tnode.__getattribute__("operandr") #inputs
             l_inputs, l_outputs = self.createGraph(operandl)
             r_inputs, r_outputs = self.createGraph(operandr)
			 
             inputs = list(set(l_inputs + r_inputs))
             outputs = list(set(l_outputs + r_outputs))
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
            
             return inputs , outputs
        
        elif type(tnode).__name__ == "ArraySelector": #createdata nodes
             index =  tnode.__getattribute__("index")
             inputs, outputs = self.createGraph(index)  
             inputs = list(set(inputs))
             outputs = list(set(outputs))
             self.inputs = myListAdd(self.inputs, inputs)
             self.outputs = myListAdd(self.outputs, outputs)
             return inputs, outputs
            
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

            postfix_operators = tnode.__getattribute__("postfix_operators")
            prefix_operators = tnode.__getattribute__("prefix_operators")
            outputs = []
            if (len(postfix_operators) > 0 or len(prefix_operators) > 0):
                outputs.append( self.insertDataNode(dn.member, dn.qualifier) )
            
            #self.insertCompNode(tnode, inputs, outputs)
            inputs = list(set(inputs))
            outputs = list(set(outputs))
            self.inputs = myListAdd(self.inputs, inputs)
            self.outputs = myListAdd(self.outputs, outputs)
            
            return inputs, outputs
        
        else:
            return [],[]

#Recursively permutate the order of the nodes in graph[]
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
        if len(blockstatement_cgnode.graph) == 0:
            #print("??????????????????????????????",blockstatement_cgnode.graph)
            path = [blockstatement_cgnode.treenode]
        else:
            path = [node.treenode for node in  blockstatement_cgnode.graph]
        body.__setattr__("statements",  path)
    elif type(tnode).__name__ == "DoStatement":
        body = tnode.__getattribute__("body")
        blockstatement_cgnode = cgnode.graph[0]
        if len(blockstatement_cgnode.graph) == 0:
            #print("??????????????????????????????",blockstatement_cgnode.graph)
            path = [blockstatement_cgnode.treenode]
        else:
            path = [node.treenode for node in  blockstatement_cgnode.graph]
        body.__setattr__("statements",  path)

    elif type(tnode).__name__ == "ForStatement":
        body = tnode.__getattribute__("body")
        blockstatement_cgnode = cgnode.graph[0]

        if len(blockstatement_cgnode.graph) == 0:
            #print("??????????????????????????????",blockstatement_cgnode.graph)
            path = [blockstatement_cgnode.treenode]
        else:
            path = [node.treenode for node in  blockstatement_cgnode.graph]
        #print("***************************",path)   
        body.__setattr__("statements",  path)
        
        
    elif type(tnode).__name__ == "IfStatement":
        then_statement = tnode.__getattribute__("then_statement")
        else_statement = tnode.__getattribute__("else_statement")
        then_block = None
        if (len(cgnode.graph) >0):then_block=cgnode.graph[0]
        else_block=None
        if (len(cgnode.graph) >1): else_block = cgnode.graph[1]
        path=None
        if not then_block is None:
            if type(then_statement).__name__ == "BlockStatement":
                if(then_block.type == "CG"):
                    path = [node.treenode for node in then_block.graph]
                else:
                    path = None
                then_statement.__setattr__("statements",  path)
            else:
                changeTreePermutationNode(then_block)
        
        if not else_block is None:
            if type(else_statement).__name__ == "BlockStatement":
                if(else_block.type == "CG"):
                    path = [node.treenode for node in else_block.graph]
                else:
                    path = None                   
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
    
    elif type(tnode).__name__ == "ReturnStatement":
        path = [node.treenode for node in cgnode.graph]
        tnode.__setattr__("expression",  path[0])
    
def genCDpermutations(cgnode):
    #first, permute at this level of cgnode
    cgnode.display(1)
    if cgnode.type=="CGComp":
        myprint("CGComp")
        return
    if len(cgnode.graph) == 0:
        myprint("cgnode.graph) == 0")
        return
    myprint("call genRandomMergePaths().................................... ")
    c1 = len(cgnode.graph)
    tmp  = cgnode.genRandomMergePaths() #generate permutation at this level
    c2 = len(tmp)	
    myprint("genCDpermutations: c1, c2", c1, c2)
    if c1==c2:
        cgnode.graph=tmp
    for elem in cgnode.graph:
        genCDpermutations(elem) # permute nodes in each sub cg node's graph array

        
#Recursively change the permutations according to the cg graph
def changeTreePermutation(cg):
    if cg.type == "CGComp": return
    if len(cg.graph)==0: return
    changeTreePermutationNode(cg) # change tree node of this level
    for cgnode in cg.graph:
        changeTreePermutation(cgnode)  # change sub cg nodes

def cdPermuteCode(tree):
    myprint("11111111111111111111111111111",tree)
    cg = CG(tree) #eatablish a CG graph
    cg.createGraph(tree) #recursively construct the CG graph  
    myprint("22222222222222222222222222222",tree)
    genCDpermutations(cg)
    myprint("3333333333333333333333333333",tree)

    #cg.display(1) #level 1 indented
    changeTreePermutation(cg) # change the tree according to cg
    myprint("4444444444444444444444444444",tree)
    
    #newCode = pyd.toJava(tree) #  string 
    return tree 
def myprint( *args):
        if (DEBUG):
                print(*args)
        
DEBUG=False	
if __name__ == '__main__' :
    sourcefile = 'Target.java'
    with open(sourcefile, 'r', encoding='utf8') as myFile:
        data = myFile.read()
        tree = javalang.parse.parse(data)
        myprint(tree)
        cg = CG(tree) #eatablish a CG graph
        cg.createGraph(tree) #recursively construct the CG graph
        cg.display(1) #level 1 indented


        for i in range(1): #generate N CG permutations            
            #path =  cg.graph[0].graph[0].genRandomMergePaths() #CompilationUnit/ClassDeclaration/MethodDeclaration
            #print("PATH: ", path)
            #for p in path:
                #print("  ", type(p.treenode).__name__, p)
                
            # generate one permutation of the CD graph
            myprint("+++++++++++++++++++++++++++++++")
            genCDpermutations(cg)
            cg.display(1) #level 1 indented
            changeTreePermutation(cg) # change the tree according to cg
            myprint(tree)
            newCode = pyd.toJava(tree) #  string 
            myprint(newCode)
            #print(type(newCode))


