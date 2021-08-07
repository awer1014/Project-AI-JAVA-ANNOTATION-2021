import os
import python_debugger as pyd
import javalang


def listdir_fullpath(d):
    return [f for f in os.listdir(d)]

def normalizeCode(sourcefileX):
    data=pyd.readJavaFile(sourcefileX)
    tree = javalang.parse.parse(data)
    newCode,_, _ = pyd.AST(tree).getAugmentedCode(changeVariable=False, permuStatement=False)
    return newCode

if __name__ == '__main__' :
    source_dir = "SourceCODES" #the source codes
    dest_dir = "NormalizedCODES" #store the augmented files
    cases = listdir_fullpath(source_dir)
    for case in cases:
        sourcefiledir = source_dir + "\\" + case
        destfiledir = dest_dir + "\\" + case
        if not os.path.exists(destfiledir):
            os.mkdir(destfiledir)  #create dest case dir
        sourcefiles = listdir_fullpath(sourcefiledir)
        for sourcefile in sourcefiles:
            if not sourcefile.lower().endswith(".java"): continue
            destfile = destfiledir + "\\" + sourcefile
            print(sourcefiledir+"\\"+sourcefile)
            newCode = normalizeCode(sourcefiledir+"\\"+sourcefile)
            pyd.write_to_file(newCode, destfile)


        
