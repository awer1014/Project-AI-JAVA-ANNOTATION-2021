import os
import python_debugger as pyd
import javalang
import xml.etree.ElementTree as ET
import numpy as np


def listdir_fullpath(d):
    return [f for f in os.listdir(d)]


def augmentCode(sourcefileX, changeVariable,permuStatement, task=1 ):
    data=pyd.readJavaFile(sourcefileX)
    tree = javalang.parse.parse(data)
    newCode,_, _ = pyd.AST(tree).getAugmentedCode(task = task, changeVariable=changeVariable, permuStatement=permuStatement)
    return newCode
   
def InputGenerate(list_newCode,copyNum,txtfiledir,case):
    for i in range(copyNum):
        copyfiledir = txtfiledir + "\\" + str(i)  #create txt case/0 dir, e.g. XX/CASE/0
        if not os.path.exists(copyfiledir):
            os.mkdir(copyfiledir)
            f_input=open(case,'w') #寫檔
            f_input.write(" <BOC> ")
            for newCode in list_newCode:
                ct = newCode.split("\n"); #以換行符號做切割
                print(type(ct))
                for content in ct:
                    print(content)
                    f_input.write(content)
                    f_input.write(" <CR> ")
                f_input.write(" <EOC> ")
            f_input.close()
     
def ChangeLineBlock(begin, end , permutation):
    line_block =[]
    oldVec = np.arange(begin, end+1)#建立元素值為 begin, begin+1, begin+2 ... (end-1),end 的陣列
    for i in oldVec
        i = permutation[index] #在permutation找到替換過後的行號 作替代
    oldVec.sort() #= np.sort(oldVec)
    for index in range(oldVec.size):
        if(oldVec[index]!=0):
            new_begin=oldVec[index]
        elif(oldVec[index]==oldVec.end())
            
        if(new_begin!=0):
            next_index=index+1
            temp=new_begin
            continuous=False
            for next_index in range(oldVec):
                if(oldVec[next_index]==new_begin+1):
                    continuous = True
                    temp=oldVec[next_index]
                elif(next_index!=temp+1 and continuous==False):
                    line_block.append(new_begin,new_begin)
                elif(next_index!=temp+1 and continuous==True):
                    line_block.append(new_begin,oldVec[oldVec[next_index-1]])
                    index = next_index
                    
     
def XmlGenerate(src_dict,copyNum,xmlfiledir,case):
    for i in range(copyNum)
        copyfiledir = txtfiledir + "\\" + str(i)  #create txt case/0 dir, e.g. XX/CASE/0
        if not os.path.exists(copyfiledir):
            os.mkdir(copyfiledir)
            tree = ET.parse("test.xml")#抓到xml檔
            root = tree.getroot()#找到根節點
            for Linelist in root.iter("Linelist"):# 尋找 Linelist 節點
                for line in Linelist.iter("Line"):#在Linelist 尋找 Line 節點
                    old_begin=line.get("Begin")# 取得節點指定屬性質
                    old_end=line.get("End")
                    src=line.get("src")
                    line_block=ChangeLineBlock(old_begin,old_end,src_dict["src"])
                    for index in line_block:
                        element=Element('line',{'src':src},{'End':index[1]},{'Begin':index[0]})
                        Linelist.append(element)
            tree.write("output.xml")

if __name__ == '__main__' :
    source_dir = "NormalizedCODES" #the source codes
    dest_dir = "AugmentedCODES" #store the augmented files
    txt_dir = "InputTxt"
    xml_dir = "OutputXml"
    cases = listdir_fullpath(source_dir)
    copyNum = 1
    for case in cases:
        list_newCode=[]
        src_dict={}
        sourcefiledir = source_dir + "\\" + case
        destfiledir = dest_dir + "\\" + case
        if not os.path.exists(destfiledir):
            os.mkdir(destfiledir)  #create dest case dir, e.g. XX/CASE
        txtfiledir = txt_dir + "\\" + case
        if not os.path.exists(txtfiledir):
            os.mkdir(txtfiledir)  #create txt case dir, e.g. XX/CASE
        xmlfiledir = xml_dir + "\\" + case
        if not os.path.exists(xmlfiledir):
            os.mkdir(xmlfiledir)  #create xml case dir, e.g. XX/CASE
        sourcefiles = listdir_fullpath(sourcefiledir)
        
        for sourcefile in sourcefiles: # .java
            for i in range(copyNum):
                print("Augmenting case : ", case, "sourcefile: ",sourcefile, " copy num: ", i, " ......") 
                copyfiledir = destfiledir + "\\" + str(i)  #create dest case/0 dir, e.g. XX/CASE/0
                if not os.path.exists(copyfiledir):
                    os.mkdir(copyfiledir)  #create dest case/0 dir
                    destfile = copyfiledir + "\\" + sourcefile
                    print("destfile:",destfile)
                    newCode,variableMap, permutation = augmentCode(sourcefile, True, True, 1)
                    list_newCode.append(newCode)
                    src_dict["destfile"]=permutation
                    pyd.write_to_file(newCode, destfile)
                    
        InputGenerate(list_newCode,copyNum,txtfiledir,case)
            


        
