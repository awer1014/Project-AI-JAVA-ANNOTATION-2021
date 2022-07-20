import numpy as np


def loadTestTrainData(filename):  # e.g., 'test.npy'
    with open(filename, "rb") as f:
        data = np.load(f)
        return data


def loadDictionary(file):
    import pickle
    a_file = open(file, "rb")
    dt = pickle.load(a_file)  # , allow_pickle=True)
    return dt


def typeDebug(type_model):
    # set default count
    error_type_count = np.asarray([0 for value in range(len(type_model[0]))])
    error_type_count = np.sum(type_model, axis=0)
    print("error_type_count: ", error_type_count)


def showXfile(x_file, loaded_dict, sample, debugMode=False):
    file_length = len(x_file)
    if debugMode == True:
        print("file_length: ", file_length)
        print("x file [", sample, "] :", x_file[sample])
    org_code = [loaded_dict[token] for token in (x_file[sample])]
    org_code = " ".join(org_code)
    print("=============org_code=============")
    print(org_code)
    """
    for file in range(file_length):
        print("=============file" + file + "=============")
        print(files[file])
    """


def showYfile(yfile, sample, endPlace, debugMode):
    if debugMode == True:
        print("yfile[0]: ", yfile[0])
        print("yfile.shape: ", yfile.shape)
        print("len(yfile): ", len(yfile))
        print("endPlace: ", endPlace)
    for sample in range(endPlace):  # start end step
        print("===============================")
        print("yfile[", sample, "][0]", yfile[sample][0])
        print("===============================")
        print("yfile[", sample, "][1]", yfile[sample][1])


def showY0Y1file(y0_file, y1_file, samples, lines, debugMode):
    if debugMode == True:
        print("y0_file[0]: ", y_file[0])
        print("y0_file.shape: ", y0_file.shape)
        print("len(yfile): ", len(yfile))
        print("endPlace: ", endPlace)
    for sample in range(samples):
        print("y0_file[", sample, "]", y0_file[sample])
        for line in range(0, lines, 2):  # start end step
            begin = line
            end = line + 1
            print("y1_file[", sample, "] index: ",
                  np.where(y1_file[begin][sample] == 1))
            print("y1_file[begin][" + str(sample) + "]: ",
                  y1_file[begin][sample])
            print("y1_file[", sample, "] index: ",
                  np.where(y1_file[end][sample] == 1))
            print("y1_file[end][" + str(sample) + "]: ", y1_file[end][sample])


def debugXfile(x_file, loaded_dict, sample=0, debugMode=False):
    if debugMode == True:
        print("===============loaded_dict===============")
        print(loaded_dict)
    # reverse dictionary
    dict_inv = {v: k for k, v in loaded_dict.items()}
    showXfile(x_file, dict_inv, sample, debugMode)


def debugYfile(yfile, samples=0, endPlace=10, debugMode=False):
    showYfile(yfile, samples, endPlace, debugMode)


def debugY_zero_Y_onefile(y0_file, y1_file, samples=10, lines=10, debugMode=False):
    showY0Y1file(y0_file, y1_file, samples, lines, debugMode)
