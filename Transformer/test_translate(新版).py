# encoding: utf-8
from __future__ import unicode_literals

import unittest
import numpy as np
from keras_transformer import transformer as tfr
import nltk
nltk.download('punkt')
def parseSentence1(x):	#甲班用
	tokens=[]
	state="START"
	for i in range(len(x)):
		#print(ord(x[i]))
		if (ord(x[i])>255):
			inp="U"
		elif (ord(x[i])>=48 and ord(x[i])<=57)
            inp="D"
        else:
			inp="E"
	
		if state=="START":
			if inp=="E":
				state="ASCII"
				chrs=x[i]
			else:
				state="UNICODE"
				tokens.append(x[i])
			
		elif state=="ASCII":
			if inp=="E":
				chrs += x[i]
			else:#U
				state="UNICODE"
				tokens += nltk.word_tokenize(chrs)
				chrs=""
				tokens.append(x[i])
	
		elif state=="UNICODE":
			if inp=="E":
				state="ASCII"
				chrs=x[i]
			else:
				state="UNICODE"
				tokens.append(x[i])
	if len(chrs)>0:
		tokens += nltk.word_tokenize(chrs) 
	return tokens
def parseSentence2(x):	#乙班用
	tokens=[]
	state="START"
	for i in range(len(x)):
		#print(ord(x[i]))
		if (ord(x[i])>255):
			inp="U"
        else:
			inp="E"
	
		if state=="START":
			if inp=="E":
				state="ASCII"
				chrs=x[i]
			else:
				state="UNICODE"
				tokens.append(x[i])
			
		elif state=="ASCII":
			if inp=="E":
				chrs += x[i]
			else:#U
				state="UNICODE"
				tokens += nltk.word_tokenize(chrs)
				chrs=""
				tokens.append(x[i])
	
		elif state=="UNICODE":
			if inp=="E":
				state="ASCII"
				chrs=x[i]
			else:
				state="UNICODE"
				tokens.append(x[i])
	if len(chrs)>0:
		tokens += nltk.word_tokenize(chrs) 
	return tokens
   
def readcode(fname):
    with open(fname) as f:
        data = f.read()
        return data
        
class TestTranslate(unittest.TestCase):

    @staticmethod
    def _build_token_dict(token_list):
        token_dict = {
            '<PAD>': 0,
            '<START>': 1,
            '<END>': 2,
        }
        for tokens in token_list:
            for token in tokens:
                if token not in token_dict:
                    token_dict[token] = len(token_dict)
        return token_dict

    def test_translate(self):
        source_tokens = [
            parseSentence2(readcode('codes/newTest0.java')),
            parseSentence2(readcode('codes/code 3.txt')),
        ]
        target_tokens = [
            parseSentence2(readcode('labels/label1.txt')),
            parseSentence2(readcode('labels/label2.txt')),
        ]

        # Generate dictionaries
        source_token_dict = self._build_token_dict(source_tokens)
        target_token_dict = self._build_token_dict(target_tokens)
        target_token_dict_inv = {v: k for k, v in target_token_dict.items()}

        # Add special tokens
        encode_tokens = [['<START>'] + tokens + ['<END>'] for tokens in source_tokens]
        decode_tokens = [['<START>'] + tokens + ['<END>'] for tokens in target_tokens]
        output_tokens = [tokens + ['<END>', '<PAD>'] for tokens in target_tokens]

        # Padding
        source_max_len = max(map(len, encode_tokens))
        target_max_len = max(map(len, decode_tokens))

        encode_tokens = [tokens + ['<PAD>'] * (source_max_len - len(tokens)) for tokens in encode_tokens]
        decode_tokens = [tokens + ['<PAD>'] * (target_max_len - len(tokens)) for tokens in decode_tokens]
        output_tokens = [tokens + ['<PAD>'] * (target_max_len - len(tokens)) for tokens in output_tokens]

        encode_input = [list(map(lambda x: source_token_dict[x], tokens)) for tokens in encode_tokens]
        decode_input = [list(map(lambda x: target_token_dict[x], tokens)) for tokens in decode_tokens]
        decode_output = [list(map(lambda x: [target_token_dict[x]], tokens)) for tokens in output_tokens]

        # Build & fit model
        model = tfr.get_model(
            token_num=max(len(source_token_dict), len(target_token_dict)),
            embed_dim=32,
            encoder_num=2,
            decoder_num=2,
            head_num=4,
            hidden_dim=128,
            dropout_rate=0.05,
            use_same_embed=False,  # Use different embeddings for different languages
        )
        losses = {
                "error_feed_forward_output": "binary_crossentropy",
                "Decoder-Output": "sparse_categorical_crossentropy",
        }
        lossWeights = {"error_feed_forward_output-Norm": 1.0, "Decoder-Output": 1.0}
        model.compile('adam', loss=losses, loss_weights=lossWeights)
        model.summary()
        model.fit(
            x=[np.array(encode_input * 1024), np.array(decode_input * 1024)],
            y=np.array(decode_output * 1024),
            epochs=10,
            batch_size=32,
        )

        # Predict
        decoded = tfr.decode(
            model,
            encode_input,
            start_token=target_token_dict['<START>'],
            end_token=target_token_dict['<END>'],
            pad_token=target_token_dict['<PAD>'],
        )
        #for i in range(len(encode_input)):
        predicted = ''.join(map(lambda x: target_token_dict_inv[x], decoded[0][1:-1]))
        print(''.join(target_tokens[0]), predicted)
        predicted = ''.join(map(lambda x: target_token_dict_inv[x], decoded[1][1:-1]))
        print(''.join(target_tokens[1]), predicted)
x=TestTranslate()
x.test_translate()
