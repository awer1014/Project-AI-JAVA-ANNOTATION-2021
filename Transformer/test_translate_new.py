# encoding: utf-8
from __future__ import unicode_literals

import unittest
import numpy as np
from keras_transformer import transformer as tfr


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
            'i need more power'.split(' '),
            'eat jujube and pill'.split(' '),
        ]


        # Generate dictionaries
        source_token_dict = self._build_token_dict(source_tokens)

        # Add special tokens
        encode_tokens = [['<START>'] + tokens + ['<END>'] for tokens in source_tokens]

        # Padding
        source_max_len = max(map(len, encode_tokens))
        encode_tokens = [tokens + ['<PAD>'] * (source_max_len - len(tokens)) for tokens in encode_tokens]
        encode_input = [list(map(lambda x: source_token_dict[x], tokens)) for tokens in encode_tokens]
        decode_output1 =[ [0]*36, [0]*36]
        decode_output1[0][0] = 1
        decode_output1[1][35] = 1
        print(decode_output1)
        decode_output2 =[ [0]*60, [0]*60]
        print("token_num: ",len(source_token_dict))
        # Build & fit model
        model = tfr.get_model(
            input_len = 6,
            token_num=len(source_token_dict),
            embed_dim=32,
            encoder_num=2,
            decoder_num=2,
            head_num=4,
            hidden_dim=128,
            dropout_rate=0.05,
            use_same_embed=False,  # Use different embeddings for different languages
        )
        losses = {
                "error_feed_forward_output1": "categorical_crossentropy",
                "error_feed_forward_output2": "mse",
        }
        lossWeights = {"error_feed_forward_output1": 1.0, "error_feed_forward_output2": 1.0}
        model.compile('adam',  loss=losses, loss_weights=lossWeights,)
        model.summary()
        print("encode_input: ",encode_input)
        model.fit(
            x=[np.array(encode_input * 1024)],
            y={"error_feed_forward_output1": np.array(decode_output1 * 1024), "error_feed_forward_output2": np.array(decode_output2 * 1024)},
            epochs=10,
            batch_size=32,
        )

        # Predict
        o1,o2= model.predict([np.array(encode_input)])
        print("o1: ", o1)
        for o in o1:
          print(np.argmax(o))
        print("o2: ", o2)
x=TestTranslate()
x.test_translate()
