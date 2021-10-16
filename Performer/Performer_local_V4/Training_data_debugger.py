import numpy as np
def loadTestTrainData(filename): # e.g., 'test.npy'
    with open(filename, "rb") as f:
        data = np.load(f)
        return data

def typeDebug(type_model):
    #set default count
    error_type_count = np.asarray([0 for value in range(len(type_model[0]))])
    error_type_count = np.sum(type_model, axis = 0)
    print("error_type_count: ", error_type_count)

def debugAll(file_path, x_trainfile):
    type_train = loadTestTrainData(file_path + "/" + x_trainfile)
    typeDebug(type_train)
