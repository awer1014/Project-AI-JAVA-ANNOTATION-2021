import javalang
import os
import chardet
import Depatch as dp
import python_debugger as pyd
import random
import testNormalizeCode as nor
import testAugmentedCode as aug

def listdir_fullpath(d):
    return [f for f in os.listdir(d)]

def myprint( *args):
        if (DEBUG):
                print(*args)

DEBUG=False
if __name__ == '__main__' :
    SourceDir = "SourceCODES" #already created
    PatchedDir = "PatchCODES"  #already created
    NormalizedDir = "NormalizedCODES"  #auto created if not exist
    PatchBackDir =  "PatchBackCODES" #auto created if not exist
    
    copyNum = 1 #擴增次數
    cases = listdir_fullpath(SourceDir)
    for case in cases:
        sourcefiledir = SourceDir + "\\" + case
        pachedfiledir = PatchedDir + "\\" + case
        normalfiledir = NormalizedDir + "\\" + case
        patchbackfiledir = PatchBackDir + "\\" + case
        if not os.path.exists(normalfiledir):
            os.mkdir(normalfiledir)  #create patchbackfiledir case dir
        if not os.path.exists(patchbackfiledir):
            os.mkdir(patchbackfiledir)  #create patchbackfiledir case dir
        sourcefiles = listdir_fullpath(sourcefiledir)
        for sourcefile in sourcefiles:
            if not sourcefile.lower().endswith(".java"): continue
            src_filename = sourcefiledir + "\\" + sourcefile
            patched_filename = pachedfiledir + "\\" + sourcefile
            normalized_filename = normalfiledir + "\\" + sourcefile
            print("src_filename: ", src_filename)
            #Step 1. compute normalized code
            newCode = nor.normalizeCode(patched_filename) #normalized code
            myprint("normalized code: ", newCode)
            pyd.write_to_file(newCode, normalized_filename) #save to normalized code dir
            #pyd.write_to_file(newCode, patched_filename) #save to patched file code dir (怕正規化後與程式有差異)

            #Step 2. compute patching rules 
            src_code = pyd.readJavaFile(src_filename) #read syntatic-error code from SourceDir
            #怕正規化後與程式有差異(例如，算數運算式)，所以用正規化後的程式
            patchRule =  dp.getTransformRule(normalized_filename, src_filename) #(error-free code, buggy code)
            myprint(patchRule)
            

            #Step 3. get patched back code 
            for i in range(copyNum):
                print("Augmenting case : ", case, "normalized_filename: ",normalized_filename, " copy num: ", i, " ......") 
                copyfiledir = patchbackfiledir + "\\" + str(i)  #create dest case/0 dir, e.g. XX/CASE/0
                if not os.path.exists(copyfiledir):
                    os.mkdir(copyfiledir)  #create dest case/0 dir
                patchback_filename = copyfiledir + "\\" + sourcefile
                transed_code =dp.transformJavaCode( patchRule, normalized_filename, True, True, task=2 ) #back to syntatic-error with normalized code
                pyd.write_to_file(transed_code, patchback_filename) #save to normalized code dir

