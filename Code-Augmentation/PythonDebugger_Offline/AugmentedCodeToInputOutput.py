#!/usr/bin/env python
# coding: utf-8

# In[ ]:


import os
import python_debugger as pyd
import javalang
import xml.etree.ElementTree as ET
import numpy as np
import glob


# In[ ]:


def listdir_fullpath(d):
    return [f for f in os.listdir(d)]


# In[ ]:


def augmentCode(sourcefileX, changeVariable,permuStatement, task=1 ):
    data=pyd.readJavaFile(sourcefileX)
    tree = javalang.parse.parse(data)
    newCode,_, permutation = pyd.AST(tree).getAugmentedCode(task = task, changeVariable=changeVariable, permuStatement=permuStatement)
    return newCode,permutation


# In[ ]:


def changeLineBlock(begin, end, permutation):
    permu_lineblock = org_lineblock = [value for value in range(begin, end+1)]#make permu_lineblock get permutation value    
    for i in range(len(org_lineblock)):
        permu_lineblock[i] = permutation[permu_lineblock[i]]    
    permu_lineblock.sort()#sort permu
    line = [] 
    lines = []
    last_permu = (len(permu_lineblock)-1)
    for i in range(len(permu_lineblock)):
        if (i == 0):
            line.append(permu_lineblock[i])
            if (permu_lineblock[i] == permu_lineblock[last_permu]):
                lines.append(list(line))
                line.clear()
        elif (permu_lineblock[i] == (permu_lineblock[i-1]+1) ):
            line.append(permu_lineblock[i])
            if (permu_lineblock[i] == permu_lineblock[last_permu]):
                lines.append(list(line))
                line.clear()
        elif (permu_lineblock[i] != (permu_lineblock[i-1]+1) ):
            lines.append(list(line))
            line.clear()
            line.append(permu_lineblock[i])
            if (permu_lineblock[i] == permu_lineblock[last_permu]):
                lines.append(list(line))
                line.clear()
        else:
            line.append(permu_lineblock[i])
            lines.append(line)
            line.clear()
    return lines


# In[ ]:


def inputGenerate(list_newCode,copyNum,txtfiledir,case):
    f_input=open(txtfiledir+"\\"+case+".txt",'w') #寫檔
    for newCode in list_newCode:
        f_input.write(" <BOC> ")
        NEWLINE = 	"abcdefgijkoooopppp"
        for content in newCode:
            if(content==NEWLINE):
                f_input.write(" <CR> ")
            else:
                f_input.write(content)
                f_input.write(" ")
            #print(content)
        f_input.write(" <EOC> ")
    f_input.close()


# In[ ]:


'''ref https://officeguide.cc/python-read-write-xml-format-file-tutorial-examples/'''
def xmlGenerate(xml_name,xml_source_dir,list_src_dict,copyNum,output_copyfiledir,case):
    tree = ET.parse(xml_source_dir)#抓到xml檔
    root = tree.getroot()#找到根節點
    for Error in root.iter("Error"):#root 尋找 Error 節點
        new_Linelist=ET.SubElement(Error,"new_Linelist")#在Error這個節點創建子節點new_Linelist提供給轉換過後的段落存取
        for Linelist in Error.iter("Linelist"):#Error 尋找 Linelist 節點
            for line in Linelist.iter("line"):#在Linelist 尋找 Line 節點
                #追蹤用   print("line.attrib",line.attrib) #印出line的屬性 
                old_begin=line.get("Begin")# 取得節點指定屬性質 此時抓到的type為str
                new_begin=int(old_begin)#創建一個新變數將old_begin轉換成int
                old_end=line.get("End")
                new_end=int(old_end)
                src=line.get("src")
                #追蹤用   print("src",src)
                if(new_begin == new_end == -1):#當new_begin&new_end都為-1時沒辦法丟入changeLineBlock()去轉換新的段落，所以直接拿原檔的行號做輸出
                    #print("new_begin: ",new_begin,", new_end: ",new_end)
                    element=ET.SubElement(new_Linelist,"new_line")#將產生出的新段落放入new_Linelist的子節點
                    element.set('Begin',old_begin)#因為tree.write()沒辦法參照"-1"的int值所以拿沒轉換過的old_begin的str值設值
                    element.set('End',old_end)#因為tree.write()沒辦法參照"-1"的int值所以拿沒轉換過的old_end的str值設值
                    element.set('src',src)
                else:
                    line_blocks=changeLineBlock(new_begin,new_end,list_src_dict[src])#進入changeLineBlock將舊段落對照permutation產生新的段落   
                    #追蹤用   print("line_blocks",line_blocks)
                    for line_block in line_blocks:
                        element=ET.SubElement(new_Linelist,"new_line")#將產生出的新段落放入new_Linelist的子節點
                        element.set('Begin',str(line_block[0]))#設置節點的屬性 因為line_block裡面是list ex:[2,3,4,5,6] 所以抓的是list初始位置
                        element.set('End',str(line_block[-1]))#設置節點的屬性 因為line_block裡面是list ex:[2,3,4,5,6] 所以抓的是list最後位置
                        element.set('src',src)
                        #追蹤用   print("element.attrib",element.attrib)


    for Error in root.iter("Error"):#將原本Linelist的每個line節點作清空
        for Linelist in Error.iter("Linelist"):
            for line in Linelist.iter("line"):
                line.clear()
    for Error in root.iter("Error"):#把Error裡的每個Linelist節點刪除
        for Linelist in Error.iter("Linelist"):
            Error.remove(Linelist)

    for Error in root.iter("Error"):#先將每個new_Linelist底下的new_line的data改名為line
        for new_Linelist in Error.iter("new_Linelist"):
            for new_line in new_Linelist.iter("new_line"):
                new_line.tag="line"
    for Error in root.iter("Error"):
        for new_Linelist in Error.iter("new_Linelist"):#再將每個Error底下的new_Linelist的data改名為Linelist
                new_Linelist.tag="Linelist"
    fname=os.path.join(output_copyfiledir,xml_name)
    tree.write(fname)


# In[ ]:


if __name__ == '__main__' :
    source_dir = "D:\\NewVerPythonDebugger\\NormalizedCODES"
    txt_dir = "D:\\NewVerPythonDebugger\\InputTxt"
    xml_dir = "D:\\NewVerPythonDebugger\\OutputXml"
    cases = listdir_fullpath(source_dir)
    copyNum = 5
    #print("cases",cases)
    for case in cases:
        javafiles=[]
        list_newCode=[]
        list_src_dict={}
        sourcefiledir = os.path.join(source_dir,case)
        #print("sourcefiledir",sourcefiledir)
        txtfiledir = os.path.join(txt_dir,case)
        #print("txtfiledir",txtfiledir)
        if not os.path.exists(txtfiledir):
                os.mkdir(txtfiledir)
        xmlfiledir = os.path.join(xml_dir,case)
        #print("xml_dir",xml_dir)
        #print("xmlfiledir",xmlfiledir)
        if not os.path.exists(xmlfiledir):
            os.mkdir(xmlfiledir)
        sourcefiles = listdir_fullpath(sourcefiledir)
        #print("sourcefiles",sourcefiles)
        for sourcefile in sourcefiles:
            if sourcefile.endswith('.java'):
                javafiles.append(sourcefile)
            elif sourcefile.endswith('.xml'):
                xmlfile=sourcefile
        for i in range(copyNum):
            input_copyfiledir = os.path.join(txtfiledir,str(i))  #create dest case/0 dir, e.g. XX/CASE/0
            #print("input_copyfiledir",input_copyfiledir)
            if not os.path.exists(input_copyfiledir):
                os.mkdir(input_copyfiledir)
            output_copyfiledir = os.path.join(xmlfiledir,str(i))  #create dest case/0 dir, e.g. XX/CASE/0
            #print("output_copyfiledir",output_copyfiledir)
            if not os.path.exists(output_copyfiledir):
                os.mkdir(output_copyfiledir)
            for javafile in javafiles:
                #print("javafile",javafile)
                jfname = os.path.join(sourcefiledir,javafile)
                newCode, permutation = augmentCode(jfname, True, True, 1)
                #print("type(sourcefile)",type(sourcefile))
                src=javafile.split(".java")
                #print("src[0]",src[0])
                list_newCode.append(newCode)
                #print("list_newCode",list_newCode)
                src_dict={src[0]:permutation}
                list_src_dict.update(src_dict)
                #print("list_src_dict",list_src_dict)
            inputGenerate(list_newCode,copyNum,input_copyfiledir,case)
            xmlfile_dir = os.path.join(sourcefiledir,xmlfile)
            #print("xml_dir",xmlfile_dir)
            xmlGenerate(xmlfile,xmlfile_dir,list_src_dict,copyNum,output_copyfiledir,case)
            #print("javafiles",javafiles)
            #print("xmlfile",xmlfile)

