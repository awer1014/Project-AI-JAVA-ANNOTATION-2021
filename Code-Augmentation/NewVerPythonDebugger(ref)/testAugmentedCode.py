import os
import python_debugger as pyd
import javalang


def listdir_fullpath(d):
    return [f for f in os.listdir(d)]


def augmentCode(sourcefileX, changeVariable,permuStatement, task=1 ):
    data=pyd.readJavaFile(sourcefileX)
    tree = javalang.parse.parse(data)
    newCode,_, _ = pyd.AST(tree).getAugmentedCode(task = task, changeVariable=changeVariable, permuStatement=permuStatement)
    return newCode


if __name__ == '__main__' :
    source_dir = "NormalizedCODES" #the source codes
    dest_dir = "AugmentedCODES" #store the augmented files
    cases = listdir_fullpath(source_dir)
    copyNum = 10
    for case in cases:
        sourcefiledir = source_dir + "\\" + case
        destfiledir = dest_dir + "\\" + case
        if not os.path.exists(destfiledir):
            os.mkdir(destfiledir)  #create dest case dir, e.g. XX/CASE
        sourcefiles = listdir_fullpath(sourcefiledir)
        for sourcefile in sourcefiles: # .java
            for i in range(copyNum):
                print("Augmenting case : ", case, "sourcefile: ",sourcefile, " copy num: ", i, " ......") 
                copyfiledir = destfiledir + "\\" + str(i)  #create dest case/0 dir, e.g. XX/CASE/0
                if not os.path.exists(copyfiledir):
                    os.mkdir(copyfiledir)  #create dest case/0 dir
                    destfile = copyfiledir + "\\" + sourcefile
                    newCode = augmentCode(sourcefile, True, True, 1)
                    pyd.write_to_file(newCode, destfile)


        
