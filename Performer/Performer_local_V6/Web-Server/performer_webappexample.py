from __future__ import print_function
import sys
sys.path.append('C:/Users/user/Desktop/websever/keras_position_wise_feed_forward')
sys.path.append('C:/Users/user/Desktop/websever/keras_performer')
sys.path.append('C:/Users/user/Desktop/websever/tensorflow_fast_attention')
sys.path.append('C:/Users/user/Desktop/websever/keras_layer_normalization')

import os
import json
from flask import Flask, flash, request, redirect, render_template
from werkzeug.utils import secure_filename

import os
import python_debugger as pyd
import queue
import tensorflow as tf
import keras.callbacks as cb
import numpy as np
from keras.preprocessing.text import Tokenizer
from keras.preprocessing.sequence import pad_sequences
from tensorflow.keras.utils import to_categorical
#from keras.layers import Dense, Input, GlobalMaxPooling1D,Activation,Dropout
#from keras.layers import Conv1D, MaxPooling1D, Embedding
#from keras.layers.normalization import BatchNormalization
from keras.models import Model
from keras.initializers import Constant
from matplotlib import pyplot as plt
from tensorflow.keras.models import load_model, Sequential
import chardet
import glob
from chardet.universaldetector import UniversalDetector
import javalang
import pickle

from keras_performer import performer as tfr
from tensorflow import keras
from keras_embed_sim import EmbeddingRet, EmbeddingSim
from keras_pos_embd import TrigPosEmbedding
from tensorflow_fast_attention.fast_attention import softmax_kernel_transformation, Attention, SelfAttention
from keras_position_wise_feed_forward.feed_forward import FeedForward  

import nltk
nltk.download('punkt')
from nltk.tokenize import RegexpTokenizer

UPLOAD_FOLDER = './uploads/'

app = Flask(__name__)
app.jinja_env.add_extension('jinja2.ext.do')
app.secret_key = "1234"
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
app.config['MAX_CONTENT_LENGTH'] = 16 * 1024 * 1024
ALLOWED_EXTENSIONS = set(['java'])
detector = UniversalDetector()


def find_first_sublist(seq, sublist, start=0):
    length = len(sublist)
    for index in range(start, len(seq)):
        if seq[index:index+length] == sublist:
            return index, index+length

def replace_sublist(seq, sublist, replacement):
    length = len(replacement)
    index = 0
    for start, end in iter(lambda: find_first_sublist(seq, sublist, index), None):
        seq[start:end] = replacement
        index = start + length
        
def replaceTAGS(x):
    replace_sublist(x, ['<', 'BOC', '>'], ["<BOC>"])
    replace_sublist(x, ['<', 'EOC', '>'], ["<EOC>"])
    replace_sublist(x, ['<', 'BOTM', '>'], ["<BOTM>"])
    replace_sublist(x, ['<', 'BOT', '>'], ["<BOT>"])
    replace_sublist(x, ['<', 'EOT', '>'], ["<EOT>"])
    replace_sublist(x, ['<', 'BOM', '>'], ["<BOM>"])
    replace_sublist(x, ['<', 'EOM', '>'], ["<EOM>"])
    replace_sublist(x, ['<', 'EOTM', '>'], ["<EOTM>"])
    replace_sublist(x, ['<', 'CR', '>'], ["<CR>"])
    replace_sublist(x, ['<', 'STRING', '>'], ["<STRING>"])
    replace_sublist(x, ['<', 'NUM_INT', '>'], ["<NUM_INT>"])
    replace_sublist(x, ['<', 'NUM_FLOAT', '>'], ["<NUM_FLOAT>"])
    return x

def parseSentence(x):
    tokenizer =  RegexpTokenizer(r"[\w']+|[].,:!?;=+-\\*/@#$%^&_(){}~|\"[]")
    tokens=[]
    state="START"
    chrs=""
    for i in range(len(x)):
        if (ord(x[i])>255):
            inp="U"
        elif (ord(x[i])>=48 and ord(x[i])<=57):
            inp="D"
        else:
            inp="E"

        if state=="START":
            if inp=="D":
                state="NUMBER"
                tokens.append(x[i])
            elif inp=="E":
                state="ASCII"
                chrs=x[i]
            else:#U
                state="UNICODE"
                tokens.append(x[i])                
            
        elif state=="ASCII":	
            if inp=="D" or inp=="E":
                state="ASCII"
                chrs += x[i]
            else:#U
                state="UNICODE"
                tokens += tokenizer.tokenize(chrs)
                chrs=""
                tokens.append(x[i])

        elif state=="NUMBER":
            if inp=="D":
                state="NUMBER"
                tokens.append(x[i])
            elif inp=="E":
                state="ASCII"
                chrs=x[i]
            else:#U
                state="UNICODE"
                tokens.append(x[i])		

        elif state=="UNICODE":
            if inp=="D":
                state="NUMBER"
                tokens.append(x[i])
            elif inp=="E":
                state="ASCII"
                chrs=x[i]
            else:#U
                state="UNICODE"
                tokens.append(x[i])

    if len(chrs)>0:
        tokens += tokenizer.tokenize(chrs) #wordpunct_tokenize(chrs) #nltk.word_tokenize(chrs)
    return replaceTAGS(tokens)
    
def tokenize(source_tokens, token_dict, source_max_len):
    encode_tokens = [['<START>'] + source_tokens + ['<END>']]
    encode_tokens = [[x for tokens in encode_tokens for x in tokens if x in token_dict.keys() ]]
    encode_tokens = [encode_tokens[0] + ['<PAD>'] * (source_max_len - len(encode_tokens[0]))]
    encode_input = [[token_dict[x] for tokens in encode_tokens for x in tokens if x in token_dict.keys() ]]
    return encode_input

def loadpatterns():
    patterns=[]
    with open('36pattern.json',encoding="utf-8") as json_file:
        data = json.load(json_file)
        for d in data['patterns']:
            p=d["p"]
            #url=d["url"]
            patterns.append((p))#patterns.append((p,url))
        
    return patterns
	
def xx(filename):
    detector.reset()
    for line in open(filename, 'rb'):
        detector.feed(line)
        if detector.done: break
    detector.close()
    return( detector.result)

def allowed_file(filename):
	return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS
	
qum = queue.Queue(maxsize = 1)
qug = queue.Queue(maxsize = 1)
qut = queue.Queue(maxsize = 1)
qust = queue.Queue(maxsize = 1)
model = None
source_token_dict = None
texts=[]
errordict={}
errorcodes=[]
MAX_NUM_WORDS=20
MAX_SEQUENCE_LENGTH = 992
EMBEDDING_DIM = 50

def getTokenizer():
    global tokenizer
    with open('labels.txt') as f:
        readdata = f.readlines()

    for r in readdata:
        errordict.setdefault(r[0:r.index('\t')],r[r.index('\t')+1:])

    with open('sourcecodes.txt', encoding = "utf8") as f:
        readdata = f.readlines()
    errorcodes=[]
    for r in readdata:
        try:
            key=r[0:r.index('\t')]
            e=errordict[key]
            e=e.strip("\n")
            ee=e.split("\t")
            errorcodes.append(ee)

            texts.append(r[r.index('\t')+1:])
        except KeyError:
            print(key+" error")

    errorcodes=np.array(errorcodes)
    labels_size=errorcodes.shape[1]

    #

    print('Indexing word vectors.')
    GLOVE_DIR="./"
    embeddings_index = {}
    with open(os.path.join(GLOVE_DIR, 'glove.6B.50d.txt'), encoding='utf8') as f:
        for line in f:
            values = line.split()
            word = values[0]
            coefs = np.asarray(values[1:], dtype='float32')
            embeddings_index[word] = coefs

    print('Found %s word vectors.' % len(embeddings_index))


    tokenizer = Tokenizer(num_words=MAX_NUM_WORDS, filters=' ', lower=False, oov_token=('{', '}', '=', ';'))
    tokenizer.fit_on_texts([texts])
    return tokenizer

def classify(source_tokens):
    model = qum.get()
    #graph = qug.get()
    source_token_dict = qust.get()
    encoder_input=tokenize(source_tokens, source_token_dict, MAX_SEQUENCE_LENGTH) 
    #tokenizer = getTokenizer()#(num_words=MAX_NUM_WORDS, filters=' ', lower=False, oov_token=('{', '}', '=', ';'))
    errortypes, predicted = tfr.decode(
            model,
            encoder_input,
            max_len=MAX_SEQUENCE_LENGTH
        )
            
    #predicted = ''.join(map(lambda x: target_token_dict_inv[x], decoded[0][1:-1]))
    #print('predicted',predicted)
    #print('len(predicted)',len(predicted))
    #print('predicted[0].shape',predicted[0].shape)
    #print('predicted[0][0]',predicted[0][0])
    #print('predicted[0][0][0]',predicted[0][0][0])
    print('errortypes:',errortypes)
    ans=[]
    ans_CI=[]
    ans_count=0
    type_Gate = 0.1
    for a in errortypes[0]:
        if a>type_Gate:
            ans.append(1)
            a='('+str(round(a*100))+'%'+')'
            ans_CI.append(a)
            ans_count+=1   
        else :
            ans.append(0)
            ans_CI.append(0)
    print('ans',ans)
    print('ans_CI',ans_CI)
                
    lb_ans=[]
    lb_Gate=0.2
    z_index= np.argmax(predicted,axis=-1)#index
    z_value= np.amax(predicted,axis=-1)#value
    print("z_index.shape:",z_index.shape)
    print("z_value.shape:",z_value.shape)
    lb_count=0
    for i in range(len(ans)):
        lb=[]
        if(ans[i]==0):
            lb_ans.append(lb)
        else:
            for j in range(0,58,2):
                b_index=z_index[60*lb_count+j,0]
                #print("b_index[",60*lb_count+j,"]:",b_index)
                e_index=z_index[60*lb_count+j+1,0]
                #print("e_index[",60*lb_count+j+1,"]:",e_index)
                b_value=z_value[60*lb_count+j,0]
                e_value=z_value[60*lb_count+j+1,0]
                if b_value>lb_Gate and e_value>lb_Gate and b_index>1 and e_index>1:
                    if b_index>e_index:
                        b_index, e_index = e_index, b_index
                        b_value, e_value = e_value, b_value
                    lb_text = "第"+str(b_index-1)+"行("+str(round(b_value*100))+"%)到第"+str(e_index-1)+"行("+str(round(e_value*100))+"%),"
                    lb.append(lb_text)
            lb_ans.append(lb)
            lb_count+=1
    print('lb_ans',lb_ans)
            
    qum.put(model)
    #qug.put(graph)
    qust.put(source_token_dict)
    return ans, lb_ans, ans_CI

def loadDictionary(file):
    import pickle
    a_file = open(file, "rb")
    dt = pickle.load(a_file)
    return dt
    
    
def normalizeSingleCodeFromString(data, reducedCode=True):
    ok = True
    try:
        tree = javalang.parse.parse(data)
    except Exception as e: # work on python 3.x
        print(str(e))
        ok = False
    if ok:
        parser = pyd.AST(tree)
        if reducedCode: parser.reduceCode_StringNum()
        newCode,_, _ = parser.getAugmentedCode(changeVariable=False, permuStatement=False)
        res = []
        for token in newCode:
            if token == pyd.NEWLINE:
                res.append("<CR>")
            else:
                res.append(token)
        res.insert(0, "<BOC>")
        res.append("<EOC>")
        return res
    else:
        print("None:", data)
        return None

def normalizeSingleCodeFromString2(data, reducedCode=True):
    ok = True
    try:
        tree = javalang.parse.parse(data)
    except Exception as e: # work on python 3.x
        print(str(e))
        ok = False
    if ok:
        parser = pyd.AST(tree)
#        if reducedCode: parser.reduceCode_StringNum()
        newCode,_, _ = parser.getAugmentedCode(changeVariable=False, permuStatement=False)
        res = []
        for token in newCode:
            if token == pyd.NEWLINE:
                res.append("\n")
            else:
                res.append(token)
#        res.insert(0, "<BOC>")
#        res.append("<EOC>")
        return res
    else:
        print("None:", data)
        return None
    
def normalizeCodeFromString(programs,reducedCode=True):
    result = []
    for program in programs:
        ans = normalizeSingleCodeFromString(program,reducedCode)
        if ans is None:
            print("program:",program)
            return None
        else:
            result+=ans
    return result

def normalizeCodeFromString2(programs,reducedCode=True):
    result = []
    for program in programs:
        ans = normalizeSingleCodeFromString2(program,reducedCode)
        if ans is None:
            print("program:",program)
            return None
        else:
            result+=ans
    return result

def load(model_name):
    co = tfr.get_custom_objects()
    co["softmax_kernel_transformation"] = softmax_kernel_transformation
    model = keras.models.load_model(model_name, custom_objects = co)
    s = loadDictionary('source_token_dict.pickle')
    return model,s
    

def load_model2():
    global model
    #global graph
    global source_token_dict 
    #load model
#    model, source_token_dict= load('checkpoint_model.h5')
    model, source_token_dict= load('test_model1.h5')
    #graph = tf.compat.v1.get_default_graph()
    qum.put(model)
    #model.summary()
    #qug.put(graph)
    qust.put(source_token_dict)
    

@app.route('/')
def index():
    return render_template('search_form.html')



@app.route('/search', methods=['POST'])
def search():
    old_srccode = request.form.get('query')
    srccode = request.form.getlist('query2')
    nor_old_srccode = normalizeCodeFromString2(srccode)
    nor_old_srccode = ' '.join(nor_old_srccode)
#    print("nor_old_srccode:",nor_old_srccode)
#    print("len(srccode):",len(srccode))
    nor_code = normalizeCodeFromString(srccode)
#    print("type(nor_code):",type(nor_code))
    if nor_code is None:
        message='程式有語法錯誤，請先用編譯器處理'
        return render_template('search_form.html',message=message)
    nor_srccode = ''.join(nor_code)
    nor_srccode= nor_srccode.replace("：",":")
    tokens = parseSentence(nor_srccode)
#    print("nor_srccode",nor_srccode)
#    print("tokens",tokens)
    if not srccode:
# query not given, show an error message
        message = 'Please enter a srccode!'
        return render_template('search_form.html', message=message)
    else:
# search
        if len(tokens)<MAX_SEQUENCE_LENGTH:
            ans, predicted, ans_CI = classify(tokens)
            unknow=['超出最多位置預測範圍']
            error_count=0
            print('回傳的ans',ans)
            print('回傳的predicted',predicted)
            patterns_CI = {}
            for i in range(36):
                patterns_CI[i] = str(patterns[i]) + str(ans_CI[i])
            newerrpatterns={patterns_CI[i]: predicted[i] for i,item in list(enumerate(ans)) if item==1}
            '''for i,item in list(enumerate(ans)):
                if error_count>5:
                    newerrpatterns={patterns[i]: unknow  }
                elif item==1:
                    newerrpatterns={patterns[i]: predicted[i]  }
                    error_count+=1'''
            if len(newerrpatterns)==0:
                   newerrpatterns={"偵測不到錯誤樣式":"null"}
            print ("newerrpatterns: ",newerrpatterns)
            return render_template('search_results.html',
                    query=nor_old_srccode,
                    results=newerrpatterns)
        else:
            message = 'Srccode tokens bigger than '+str(MAX_SEQUENCE_LENGTH)+' !'
            return render_template('search_form.html', message=message)


if __name__ == '__main__':
    patterns=loadpatterns()
    for i in range(1):
        load_model2()
    app.run(host='0.0.0.0', port=9000, debug=False)
