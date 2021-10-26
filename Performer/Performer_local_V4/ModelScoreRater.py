from keras_performer import performer as tfr
import numpy as np
def loadDictionary(file):
    import pickle
    a_file = open(file, "rb")
    dt = pickle.load(a_file)
    return dt

def loadTestTrainData(filename): # e.g., 'test.npy'
    with open(filename, "rb") as f:
        a = np.load(f)
        return a

def load(model_path, model_name):
    import sys
    sys.path.append("Perfomer_local_V4/keras_layer_normalization")
    sys.path.append("Perfomer_local_V4/keras_position_wise_feed_forward")
    sys.path.append("Perfomer_local_V4/tensorflow_fast_attention")
    sys.path.append("Perfomer_local_V4/keras_performer")
    sys.path.append("Perfomer_local_V4/keras_pos_embed")

    from keras_performer import performer
    from tensorflow import keras
    from keras_embed_sim import EmbeddingRet, EmbeddingSim
    from keras_pos_embd import TrigPosEmbedding
    from tensorflow_fast_attention.fast_attention import softmax_kernel_transformation, Attention, SelfAttention
    from keras_position_wise_feed_forward.feed_forward import FeedForward

    co = performer.get_custom_objects()
    co["softmax_kernel_transformation"] = softmax_kernel_transformation
    model = keras.models.load_model(model_path + "/" + model_name, custom_objects = co)
    # t = loadDictionary(target_token_dict, 'target_token_dict.pickle')
    # t_inv = loadDictionary(target_token_dict_inv, 'target_token_dict_inv.pickle')
    return model

def max_length_adjustment(loaded_model, training_source_max_len):
    adjusted_model = []
    for i in range(len(loaded_model)):
        #print("i: ", i)
        d_two = []
        for k in range(training_source_max_len): #set length
            #print("k: ", k)
            d_two.append(loaded_model[i][k])
        #switch to np array
        d_two = np.array(d_two)
        #give np array
        adjusted_model.append(d_two)
    #switch to np array
    adjusted_model = np.array(adjusted_model)

    return adjusted_model

def out_line_adjustment(out_line):
    #get each dim length
    sample_size = len(out_line[0])
    blocks = len(out_line)
    lines_length = len(out_line[0][0])
    for sample in range(sample_size):
        #inintial max_index
        max_index = None
        for block in range((blocks)):
            #find biggest index
            max_index = out_line[block][sample].argmax()#index(max(out_line[block][sample]))
            #build the new array for out_line
            out_line[block][sample] = [0 for vlaue in range(lines_length)]
            #set max value to lines
            out_line[block][sample][max_index] = 1
    return out_line


#load model
def loadmodel(model_path, model_name, x_y_path, x_test_model, y_test_model1, y_test_model2, source_max_lan):
    import numpy as np
    #load model and dic ps. dic is not use
    #model, source_token_dict = load(model_name)
    model = load(model_path, model_name)
    #print(model.summary())
    #load

    '''
    Para:
        x_test_loaded  : answer model           (include type and lineblock)
        out1           : model's predict        (type)
        out2           : model's predict        (lineblock)
        y_test_loaded_0: answer of model output (type)
        y_test_loaded_1: answer of model output (lineblock)
    '''

    ''' <-------dust switch
    print("y_test_loaded_1 shape: ", y_test_loaded_1.shape)
    print("x_test_loaded length: ", len(x_test_loaded))
    #'''

    #load x & y test model
    #load model for predict
    x_test_loaded = loadTestTrainData(x_y_path + "/" + x_test_model)
    #loda ans model
    y_test_loaded_0 = loadTestTrainData(x_y_path + "/" + y_test_model1)
    y_test_loaded_1 = loadTestTrainData(x_y_path + "/" + y_test_model2)

    #''' <-------dust switch
    print("x_test_loaded shape: ", x_test_loaded.shape)
    print("x_test_loaded type: ", type(x_test_loaded))
    print("x_test_loaded[0] type: ", type(x_test_loaded[0]))
    print("y_test_loaded_0 shape: ", y_test_loaded_0.shape)
    print("y_test_loaded_1 shape: ", y_test_loaded_1.shape)
    #'''

    #get model perdict result

    out1, out2 = tfr.decode(model, x_test_loaded, max_len = source_max_lan)

    #==============show org result================
    ''' <-------dust switch
    print("out1 type: ", type(out1))
    print("out2 type: ", type(out2))
    print("out1 shape: ", (out1).shape)#prob upper then 0.5
    print("out2 length: ", len(out2))
    print("out2[0] shape: ", (out2[0]).shape)#prob upper then 0.5
    print("out2[0] length: ", len(out2[0]))#prob lb
    #'''

    #=============================================
    #error type adjustment
    #solution: find the value upper than 0.5 ------> use np.around()
    print("=========type adjust 1=========")
    print("org out type: ", type(out1))
    test_ep = np.rint(out1)
    print("arounded out type: ", type(out1))

    print("=========type adjust 2=========")
    print("org y_test_loaded_0: ", type(y_test_loaded_0))
    ans_ep = (y_test_loaded_0) #np.around(y_test_loaded_0)
    print("arounded y_test_loaded_0: ", type(y_test_loaded_0))
    #error line adjustment
    #solution: find the maximum vlaue

    print("=========line adjust 1=========")
    test_lb = out_line_adjustment(out2)

    print("=========line adjust 2=========")
    ans_lb = (y_test_loaded_1) #out_line_adjustment(y_test_loaded_1)

    print("========adjust complete========")
    #==============show toint result==============

    ''' <-------dust switch
    print("=========test_ep[1]=========")
    print(test_ep[1])
    print("=========test_lb[1]=========")
    print(test_lb[1])
    print("=========ans_ep[1]==========")
    print(ans_ep[1])
    print("=========ans_lb[1]==========")
    print(ans_lb[1])
    #'''
    #=============================================
    return test_ep, ans_ep, test_lb, ans_lb

def intersect(pre_errortype, ans_errortype):
    #print("inter :", pre_errortype)
    #print("inter: ", ans_errortype)
    #ans_errortype = float(ans_errortype)
    #ref = "https://www.796t.com/post/Mjc4am8=.html"
    inter = [pre_value for pre_value, ans_value in zip(pre_errortype, ans_errortype) if (pre_value == ans_value == 1)]
    inter_two = [pre_value for pre_value, ans_value in zip(pre_errortype, ans_errortype) if (pre_value == ans_value)]
    #print("sort inter: ", inter)
    return inter, inter_two

#get new ans array contains only 1 in array
def ans_typefilter(ans_errortype):
    #print("org ans type: ", ans_errortype)
    new_ans_errortype = [value for value in ans_errortype if value == 1]
    #print("new ans errortype: ", new_ans_errortype)
    return new_ans_errortype

#get new pre array contains only 1 in array
def pre_typefilter(pre_errortype):
    #print("org error type: ", pre_errortype)
    new_pre_errortype = [value for value in pre_errortype if value == 1]
    #print("new pre errortype: ", new_pre_errortype)
    return new_pre_errortype

#calculate error type score
#Note:
    #Predict score:
        # score = inter of ans and pre divide by len of pre
    #Recall score:
        # score = inter of ans and pre divide by len of ans
    #Accuracy score:
        # score = inter_two of ans and pre divide by original len of pre
def errortype_score(pre_errortype, ans_errortype):
    #find pre length
    new_pre_errortype = pre_typefilter(pre_errortype) #make new array for score
    pre_length = len(new_pre_errortype) #get length from ans_type
    #find ans length
    new_ans_errortype = ans_typefilter(ans_errortype) #make new array for score
    ans_length = len(new_ans_errortype) #get length from ans_type
    #print("ans length: ", ans_length)
    inter, inter_two = intersect(pre_errortype, ans_errortype) #get intersection
    inter_length = len(inter)
    inter_two_length = len(inter_two)
    #print("inter_length: ", inter_length)
    #print("pre length: ", pre_length)

    #calculate Predict score
    if (inter_length == 0 and pre_length == 0):
        pre_score = 1
    elif (pre_length == 0):
        pre_score = 0
    else:
        pre_score = inter_length/pre_length
    #print("predict score: ", pre_score) #show pre score

    #calculate Recall score
    if (inter_length == 0 and ans_length == 0):
        rec_score = 1
    elif (ans_length == 0):
        rec_score = 0
    else:
        rec_score = inter_length/ans_length
    #print("recall score: ", rec_score) #show ans score

    #calculate Accuarcy score
    acc_score = inter_two_length/len(pre_errortype)

    return pre_score, rec_score , acc_score #return float

#show total perdict score and recall score
def errortype_totalscore(pre_errortype,ans_errortype):
    #initial para
    pre_total = 0.0
    rec_total = 0.0
    acc_total = 0.0
    #get each score then get total and avg score
    for i in range(len(pre_errortype)):
        #print("Sample: ", i)
        pre, rec, acc = errortype_score(pre_errortype[i], ans_errortype[i])
        pre_total = pre_total + pre
        rec_total = rec_total + rec
        acc_total = acc_total + acc
    """
    print("pre_total: ", pre_total)
    print("rec_total: ", rec_total)
    print("acc_total: ", acc_total)
    """
    pre_avg_score = pre_total/len(pre_errortype)
    rec_avg_socre = rec_total/len(ans_errortype)
    acc_avg_score = acc_total/len(ans_errortype)
    print("avg_pre: ", pre_avg_score)#pre_total/len(pre_errortype))
    print("avg_rec: ", rec_avg_socre)#rec_total/len(ans_errortype))
    print("avg_acc: ", acc_avg_score)#acc_total/len(ans_errortype))
    return pre_avg_score, rec_avg_socre, acc_avg_score

def get_start_end_line(pre_begin_index, pre_end_index, ans_begin_index, ans_end_index):
    #Note:
            #use softmax: [0.....150] total == 1
            #begin: index[0]
            #end: index[0]

    #get [value] first and get [value] again to get value
    #"""
    pre_begin_line = pre_begin_index[0]
    pre_begin_line = pre_begin_line[0]
    pre_end_line = pre_end_index[0]
    pre_end_line = pre_end_line[0]
    ans_begin_line = ans_begin_index[0]
    ans_begin_line = ans_begin_line[0]
    ans_end_line = ans_end_index[0]
    ans_end_line = ans_end_line[0]
    #"""

    """
    pre_begin_line = pre_begin_index
    pre_begin_line = pre_begin_line
    pre_end_line = pre_end_index
    pre_end_line = pre_end_line
    ans_begin_line = ans_begin_index
    ans_begin_line = ans_begin_line
    ans_end_line = ans_end_index
    ans_end_line = ans_end_line
    #"""

    return pre_begin_line, pre_end_line, ans_begin_line, ans_end_line

def line_adjustment(line):
    """
    print("line: ", line)
    print("line type: ", type(line))
    #"""
    if(line > 1):
        line = line-1
        return line
    else:
        return line

# get each pre and ans lineblocks and make them to pre begin/end ans begin/end array
def make_lineblock(begin, end):
    if (end < begin):
        block = [value for value in range(end, begin+1)]
        return block
    else:
        block = [value for value in range(begin, end+1)]
        return block

# ref = "https://www.geeksforgeeks.org/python-intersection-two-lists/"
def lineblock_intersect(pre_block, ans_block):
    inter = [value for value in pre_block if value in ans_block]
    return inter

# return index with int datatype
def get_block_index(pre_begins, pre_ends, ans_begins, ans_ends):
    pre_begin = int(pre_begins)
    pre_end = int(pre_ends)
    ans_begin = int(ans_begins)
    ans_end = int(ans_ends)
    #print(pre_begin, pre_end, ans_begin, ans_end)
    return pre_begin, pre_end, ans_begin, ans_end

#get new pre array contains only 1 in array
def linefilter(line_block):
    #print("org error type: ", pre_errortype)
    new_line = [value for value in line_block if value == 1]
    #print("new pre errortype: ", new_pre_errortype)
    return new_line

 #Note:
    #Predict score:
        # score = inter of ans and pre divide by len of pre
    #Recall score:
        # score = inter of ans and pre divide by len of ans
    #                 b   s   l
    #line sturcture [84][36][150]
def errorline_totalscore(pre_errorline, ans_errorline):
    #get sample size
    sample_size = len(pre_errorline[1])
    #get block size
    lineblock_size = len(pre_errorline)
    """ <-------dust switch
    print("sample_size: ", sample_size)
    print("lineblock_size type : ", type(lineblock_size))
    print("lineblock_size: ", lineblock_size)
    #"""
    total_pre = 0.0
    total_rec = 0.0
    total_sample_pre = 0.0
    total_sample_rec = 0.0
    for sample in range(sample_size):
        sample_totalline_pre = 0.0
        sample_totalline_rec = 0.0
        for lineblock in range(0, lineblock_size, 2):
            #give index blocks value
            pre_begin = pre_errorline[lineblock][sample]
            pre_end = pre_errorline[lineblock+1][sample]
            ans_begin = ans_errorline[lineblock][sample]
            ans_end = ans_errorline[lineblock+1][sample]
            ''' <-------dust switch
            #show error block value
            print("pre_errorline begin[lineblock][sample]: ", lineblock, " ", sample)
            print(pre_begin)
            print("pre_errorline begin[lineblock][sample]: ", lineblock+1, " ", sample)
            print(pre_end)
            print("ans_errorline begin [lineblock][sample]: ", lineblock, " ", sample)
            print(ans_begin)
            print("ans_errorline end[lineblock][sample]: ", lineblock+1, " ", sample)
            print(ans_end)
            #'''
            #get block start/end index
            pre_begin_index = np.where(pre_begin == 1)
            pre_end_index = np.where(pre_end == 1)
            ans_begin_index = np.where(ans_begin == 1)
            ans_end_index = np.where(ans_end == 1)
            ''' <-------dust switch
            #show start/end index
            print("pre_begin_index: ", pre_begin_index)
            print("pre_begin_index type: ", type(pre_begin_index))
            print("pre_end_index: ", pre_end_index)
            print("ans_begin_index: ", ans_begin_index)
            print("ans_end_index: ", ans_end_index)
            #'''
            #give start value
            #Note:
                #use softmax: [0.....150] total == 1
                #begin: index[0]
                #end: index[0]

            #get [value] first and get [value] again to get value
            pre_begin_line, pre_end_line, ans_begin_line, ans_end_line = get_start_end_line(
                                                                                            pre_begin_index,
                                                                                            pre_end_index,
                                                                                            ans_begin_index,
                                                                                            ans_end_index
                                                                                            )
            ''' <-------dust switch
            #show start/end index
            print("pre_begin_line: ", pre_begin_line)
            print("pre_end_line: ", pre_end_line)
            print("ans_begin_line: ", ans_begin_line)
            print("ans_end_line: ", ans_end_line)
            #'''
            ''' <-------dust switch
            #'''
            #make line downgrade for 1
            #Note:
            #before:  0 -1  1  2  3  4
            #after:   0  1 <-----abs
                        #1  2  3  4
                    #[0, 1, 2, 3, 4, 5]
            pre_begin_line = line_adjustment(pre_begin_line)
            pre_end_line = line_adjustment(pre_end_line)
            ans_begin_line = line_adjustment(ans_begin_line)
            ans_end_line = line_adjustment(ans_end_line)
            ''' <-------dust switch
            print("pre_begin_line: ", pre_begin_line)
            print("pre_end_line: ", pre_end_line)
            print("ans_begin_line: ",ans_begin_line)
            print("ans_end_line: ", ans_end_line)
            #'''
            pre_lineblock = make_lineblock(pre_begin_line, pre_end_line)
            ans_lineblock = make_lineblock(ans_begin_line, ans_end_line)
            #print("pre_lineblock: ", pre_lineblock)
            #print("ans_lineblock: ", ans_lineblock)
            inter = lineblock_intersect(pre_lineblock, ans_lineblock)
            #print("inter: ", inter)
            inter_length = len(inter)
            pre_length = len(pre_lineblock)
            ans_length = len(ans_lineblock)
            #print("inter length: ", inter_length)
            #print("pre_length: ", pre_length)
            #print("ans_length: ", ans_length)
            #count pre_score
            if (inter_length == 0 and pre_length == 0):
                pre_score = 1
            elif (pre_length == 0):
                pre_score = 0
            else :
                pre_score = inter_length/pre_length
            #count rec_score
            if (inter_length == 0 and ans_length == 0):
                rec_score = 1
                #
            elif (ans_length == 0):
                rec_score = 0
            else:
                rec_score = inter_length/ans_length
            #print("Sample pre_score: ", pre_score)
            #print("Sample rec_score: ", rec_score)
            #cal each sample lineblock score
            sample_totalline_pre += pre_score
            sample_totalline_rec += rec_score
            #print("Sample total_pre: ", sample_pre)
            #print("Sample total_rec: ", sample_rec)
        #cal total sample score
        #print("lineblock_size/2 type: ", type(lineblock_size/2))
        #print("lineblock_size/2 : ", (lineblock_size/2))
        total_sample_pre += sample_totalline_pre/(lineblock_size/2)
        total_sample_rec += sample_totalline_rec/(lineblock_size/2)
    avg_pre = total_sample_pre/sample_size
    avg_rec = total_sample_rec/sample_size
    print("avg_pre: ", avg_pre)
    print("avg_rec: ", avg_rec)
    return avg_pre, avg_rec

def error_type_F_score(pre_score, rec_score):
    f_one = (2*pre_score*rec_score)/(pre_score + rec_score)
    f_two = (3*pre_score*rec_score)/((2*pre_score) + rec_score)
    f_pointfive = (3*pre_score*rec_score)/(pre_score + (2*rec_score))
    print("F_one: ", f_one)
    print("F_two: ", f_two)
    print("F_pointfive: ", f_pointfive)

def error_line_F_score(pre_score, rec_score):
    f_one = (2*pre_score*rec_score)/(pre_score + rec_score)
    f_two = (3*pre_score*rec_score)/((2*pre_score) + rec_score)
    f_pointfive = (3*pre_score*rec_score)/(pre_score + (2*rec_score))
    print("F_one: ", f_one)
    print("F_two: ", f_two)
    print("F_pointfive: ", f_pointfive)

def sklearn_sample_score(test_ep, ans_ep):
    from sklearn import metrics as metrics
    sklearn_precision_sum = 0.0
    sklearn_recall_sum = 0.0
    sklearn_f_one_sum =0.0
    for sample in range(len(ans_ep)):
        sklearn_precision = metrics.precision_score(ans_ep[sample], test_ep[sample])
        sklearn_recall = metrics.recall_score(ans_ep[sample], test_ep[sample])
        sklearn_f_one = metrics.f1_score(ans_ep[sample], test_ep[sample])
        sklearn_precision_sum += sklearn_precision
        sklearn_recall_sum += sklearn_recall
        sklearn_f_one_sum += sklearn_f_one
    sklearn_precision_avg = sklearn_precision_sum/len(ans_ep)
    sklearn_recall_avg = sklearn_recall_sum/len(ans_ep)
    sklearn_f_one_avg = sklearn_f_one_sum/len(ans_ep)
    print("sklearn precision: ", sklearn_precision_avg)
    print("sklearn recall: ", sklearn_recall_avg)
    print("sklearn f_one score: ", sklearn_f_one_avg)



def showAllScore(model_path, model_name, x_y_path, x_mode_l, y_model_0, y_model_1, max_len_name):
    max_len = loadDictionary(model_path + "/" + max_len_name)
    #load model
    test_ep, ans_ep, test_lb, ans_lb = loadmodel(model_path, model_name, x_y_path, x_mode_l, y_model_0, y_model_1, max_len)
    print("\n"+"==========ErrorType Score==========")
    #type total score
    avg_pre, avg_rec, avg_acc = errortype_totalscore(test_ep, ans_ep)
    print("=========ErrorType F Score=========")
    #type f score
    error_type_F_score(avg_pre, avg_rec)
    print("==================================="+"\n")

    print("===========SKLearn Score==========="+"\n")
    sklearn_sample_score(test_ep, ans_ep)
    print("==================================="+"\n")
    #=====================================================

    print("==========ErrorLine Score==========")
    #line total score
    avg_pre, avg_rec = errorline_totalscore(test_lb, ans_lb)
    print("=========ErrorLine F score=========")
    #line f score
    error_line_F_score(avg_pre, avg_rec)
    print("==================================="+"\n")
