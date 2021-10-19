import numpy as np
from matplotlib import pyplot as plt
#draw model total losses

def loadDictionary(file):
    import pickle
    a_file = open(file, "rb")
    dt = pickle.load(a_file)
    return dt

def plotTrainingLoss(history):
    plt.plot(history["loss"], label = "loss")
    plt.plot(history["val_loss"], label = "val_loss")
    plt.title("model loss")
    plt.ylabel("loss")
    plt.xlabel("epoch")
    plt.legend()
    plt.show()

#draw output1 binary acc
def plotTrainingErrorTypeAcc(history):
    plt.plot(history["error_feed_forward_output1_binary_accuracy"], label = "error_type_acc")
    plt.plot(history["val_error_feed_forward_output1_binary_accuracy"], label = "val_error_type_acc")
    plt.title("model_error_feed_forward_output1_binary_accuracy")
    plt.ylabel("error_feed_forward_output1_binary_accuracy")
    plt.xlabel("epoch")
    plt.legend()
    plt.show()

#draw output1 binary loss
def plotTrainingErrorTypeLoss(history):
    plt.plot(history["error_feed_forward_output1_loss"], label = "error_type_loss")
    plt.plot(history["val_error_feed_forward_output1_loss"], label = "val_error_type_loss")
    plt.title("model_error_feed_forward_output1_loss")
    plt.ylabel("error_feed_forward_output1_loss")
    plt.xlabel("epoch")
    plt.legend()
    plt.show()

#draw output2 acc
def plotTrainingErrorLineAcc(history):
    #======================accuracy======================
    acc = []
    #get all sample name
    names = []
    for sample in range(360):
        #get NN name
        name = "LNout" + str(sample) + "_categorical_accuracy"
        names.append(name)
    #get each epoch avg value
    for epoch in range(len(history[names[0]])): #get sample length
        one_epoch_sum = 0.0
        #print("epoch loop: ", epoch)
        for name in (names):
            one_epoch_sum += history[name][epoch]
        one_epoch_avg = one_epoch_sum / len(names) #get one epoch avg
        acc.append(one_epoch_avg) #return to acc
    plt.plot(acc, label = "acc")

    #======================validation accuary======================
    val_acc = []
    #get all sample name
    val_names = []
    for val_sample in range(360):
        #get NN name
        val_name = "val_LNout" + str(val_sample) +"_categorical_accuracy"
        val_names.append(val_name)

    #get each epoch avg value
    for epoch in range(len(history[names[0]])): #get sample length
        val_one_epoch_sum = 0.0
        for name in (val_names):
            val_one_epoch_sum += history[name][epoch]
        val_one_epoch_avg = val_one_epoch_sum / len(val_names) #get one epoch avg
        val_acc.append(val_one_epoch_avg) #return to acc
    plt.plot(val_acc, label = "val_acc")
    plt.title("model_error_line_accuracy")
    plt.ylabel("output2_acc")
    plt.xlabel("epoch")
    plt.legend()
    plt.show()

#draw output2 loss
def plotTrainingErrorLineLoss(history):
    #======================accuracy======================
    acc = []
    #get all sample name
    names = []
    for sample in range(360):
        #get NN name
        name = "LNout" + str(sample) + "_loss"
        names.append(name)
    #get each epoch avg value
    for epoch in range(len(history[names[0]])): #get sample length
        one_epoch_sum = 0.0
        #print("epoch loop: ", epoch)
        for name in (names):
            one_epoch_sum += history[name][epoch]
        one_epoch_avg = one_epoch_sum / len(names) #get one epoch avg
        acc.append(one_epoch_avg) #return to acc
    plt.plot(acc, label = "loss")

    #======================validation accuary======================
    val_acc = []
    #get all sample name
    val_names = []
    for val_sample in range(360):
        #get NN name
        val_name = "val_LNout" + str(val_sample) +"_loss"
        val_names.append(val_name)

    #get each epoch avg value
    for epoch in range(len(history[names[0]])): #get sample length
        val_one_epoch_sum = 0.0
        for name in (val_names):
            val_one_epoch_sum += history[name][epoch]
        val_one_epoch_avg = val_one_epoch_sum / len(val_names) #get one epoch avg
        val_acc.append(val_one_epoch_avg) #return to acc
    plt.plot(val_acc, label = "val_loss")
    plt.title("model_error_line_loss")
    plt.ylabel('output2_loss')
    plt.xlabel('epoch')
    plt.legend()
    plt.show()

def showAllGraphic(file_path, file):
    history = loadDictionary(file_path + "/" + file)
    plotTrainingLoss(history)
    plotTrainingErrorTypeLoss(history)
    plotTrainingErrorTypeAcc(history)
    plotTrainingErrorLineLoss(history)
    plotTrainingErrorLineAcc(history)
    pass
