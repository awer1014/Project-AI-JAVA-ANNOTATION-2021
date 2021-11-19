import numpy as np
def loadTestTrainData(filename): # e.g., 'test.npy'
    with open(filename, "rb") as f:
        data = np.load(f)
        return data

def loadDictionary(file):
    import pickle
    a_file = open(file, "rb")
    dt = pickle.load(a_file)#, allow_pickle=True)
    return dt

def typeDebug(type_model):
    #set default count
    error_type_count = np.asarray([0 for value in range(len(type_model[0]))])
    error_type_count = np.sum(type_model, axis = 0)
    print("error_type_count: ", error_type_count)

def showXfile(x_file, loaded_dict, debugMode = False):
    file_length = len(x_file)
    if debugMode == True:
        print("file_length: ", file_length)
    print("x file [0] :", x_file[0])
    org_code = [loaded_dict[token] for token in (x_file[0])]
    org_code = " ".join(org_code)
    print("=============org_code=============")
    print(org_code)
    """for file in range(file_length):
        print("=============file" + file + "=============")
        print(files[file])
"""
def debugXfile(file_path, x_filename, dict_name):
    x_file = loadTestTrainData(file_path + "/" + x_filename)
    loaded_dict = loadDictionary(file_path + "/" + dict_name)
    print("=======================")
    print(loaded_dict)
    loaded_dict_inv = {v:k for k , v in loaded_dict.items()} #reverse dictionary
    """
    loaded_dict[9] = "UNK"
    loaded_dict[10] = "UNK"
    loaded_dict[159] = "UNK"
    loaded_dict[159] = "UNK"
    """
    showXfile(x_file, loaded_dict_inv)

def showYfile(y0_file, y1_file, sample, endPlace = 10):
    print("y0_file[0]: ", y0_file[0])
    print("y1.shape: ", y1_file.shape)
    print("len(y1_file): ", len(y1_file))
    print("endPlace: ", endPlace)
    for line_block in range(0, endPlace, 2): #start end step
        print("endPlace: ", endPlace)
        begin = line_block
        end = begin + 1
        print("y1_file[0][", sample, "]: ", y1_file[begin][sample])
        print("y1_file[0][", sample, "]: ", y1_file[end][sample])
        print("y1_file[0] index: ", np.where(y1_file[begin][sample] == 1))
        print("y1_file[1] index: ", np.where(y1_file[end][sample] == 1 ))

def debugYfile(y0_file, y1_file, sample = 0, endPlace =10):
    showYfile(y0_file, y1_file, sample, endPlace)
