import numpy as np
from keras_layer_normalization.layer_normalization import LayerNormalization
#from keras_multi_head import MultiHeadAttention
from tensorflow_fast_attention.fast_attention import  Attention, SelfAttention #New here
#from keras_position_wise_feed_forward import FeedForward
from keras_position_wise_feed_forward.feed_forward import FeedForward
from keras_pos_embd import TrigPosEmbedding
from keras_embed_sim import EmbeddingRet, EmbeddingSim
#from backend import keras
import keras
#from gelu import gelu
from keras_performer import gelu
import tensorflow as tf
import sys

__all__ = [
    'get_custom_objects', 'get_encoders', 'get_decoders', 'get_model', 'decode',
    'attention_builder', 'feed_forward_builder', 'get_encoder_component', 'get_decoder_component',
]


def get_custom_objects():
    return {
        'gelu': gelu,
        'LayerNormalization': LayerNormalization,
        'Attention': Attention,
        'SelfAttention': SelfAttention,
        'FeedForward': FeedForward,
        'TrigPosEmbedding': TrigPosEmbedding,
        'EmbeddingRet': EmbeddingRet,
        'EmbeddingSim': EmbeddingSim,
    }


def _wrap_layer(name,
                input_layer,
                build_func,
                dropout_rate=0.0,
                trainable=True):
    """Wrap layers with residual, normalization and dropout.

    :param name: Prefix of names for internal layers.
    :param input_layer: Input layer.
    :param build_func: A callable that takes the input tensor and generates the output tensor.
    :param dropout_rate: Dropout rate.
    :param trainable: Whether the layers are trainable.
    :return: Output layer.
    """
    print("Start Warpping...")

    if isinstance(input_layer, list):
        build_output = build_func(input_layer[0], input_layer[1])
    else:
        build_output = build_func(input_layer)
    if dropout_rate > 0.0:
        dropout_layer = keras.layers.Dropout(
            rate=dropout_rate,
            name='%s-Dropout' % name,
        )(build_output)
    else:
        dropout_layer = build_output
    if isinstance(input_layer, list):
        input_layer = input_layer[0]
    add_layer = keras.layers.Add(name='%s-Add' % name)([input_layer, dropout_layer])
    normal_layer = LayerNormalization(
        trainable=trainable,
        name='%s-Norm' % name,
    )(add_layer)
    return normal_layer


def attention_builder(name,
                      embed_dim,
                      head_num,
                      activation,
                      history_only,
					  dropout_rate=0.0,
                      trainable=True):
    """Get multi-head self-attention builder.

    :param name: Prefix of names for internal layers.
	:param embed_dim: d dimension of query, key and value vectors
    :param head_num: Number of heads in multi-head self-attention.
    :param activation: Activation for multi-head self-attention.
    :param history_only: Only use history data.
    :param trainable: Whether the layer is trainable.
    :return:
    """
    """ OLD:
	def _attention_builder(x):
        return MultiHeadAttention(
            head_num=head_num,
            activation=activation,
            history_only=history_only,
            trainable=trainable,
            name=name,
        )(x)
	SelfAttention: New Params
               hidden_size,
               num_heads,
               attention_dropout,
               kernel_transformation=relu_kernel_transformation,
               numerical_stabilizer=0.001,
               causal=False,
               projection_matrix_type=None,
               nb_random_features=0):
    """
    def _attention_builder(x, y): #Attention(hidden_size, num_heads, dropout)
        return Attention(
			hidden_size=embed_dim,
			num_heads=head_num,
			attention_dropout=dropout_rate,
			nb_random_features= int(embed_dim / 2), #16
			causal=False,
			projection_matrix_type=True
        )(x, y)
    return _attention_builder

def self_attention_builder(name,
                      embed_dim,
                      head_num,
                      activation,
                      history_only,
                      masked=False,
					  dropout_rate=0.0,
                      trainable=True):
    """Get multi-head self-attention builder.

    :param name: Prefix of names for internal layers.
	:param embed_dim: d dimension of query, key and value vectors
    :param head_num: Number of heads in multi-head self-attention.
    :param activation: Activation for multi-head self-attention.
    :param history_only: Only use history data.
    :param trainable: Whether the layer is trainable.
    :return:
    """
    """ OLD:
	def _attention_builder(x):
        return MultiHeadAttention(
            head_num=head_num,
            activation=activation,
            history_only=history_only,
            trainable=trainable,
            name=name,
        )(x)
	SelfAttention: New Params
               hidden_size,
               num_heads,
               attention_dropout,
               kernel_transformation=relu_kernel_transformation,
               numerical_stabilizer=0.001,
               causal=False,
               projection_matrix_type=None,
               nb_random_features=0):
    """
    def _attention_builder(x): #SelfAttention(hidden_size, num_heads, dropout)
        '''
        print("OOOO:", x)
        print("embed_dim:", embed_dim)
        print("head_num:", head_num)
        print("dropout_rate:", dropout_rate)
        print("masked:", masked)
        '''
        return SelfAttention(
			hidden_size=embed_dim,
			num_heads=head_num,
			attention_dropout=dropout_rate,
			nb_random_features= int(embed_dim / 2), #16
			causal=masked,
			projection_matrix_type=True
        )(x)
    return _attention_builder


def feed_forward_builder(name,
                         hidden_dim,
                         activation,
                         trainable=True):
    """Get position-wise feed-forward layer builder.

    :param name: Prefix of names for internal layers.
    :param hidden_dim: Hidden dimension of feed forward layer.
    :param activation: Activation for feed-forward layer.
    :param trainable: Whether the layer is trainable.
    :return:
    """
    def _feed_forward_builder(x):
        return FeedForward(
            units=hidden_dim,
            activation=activation,
            trainable=trainable,
            name=name,
        )(x)
    return _feed_forward_builder


def get_encoder_component(name,
                          input_layer,
						  embed_dim,
                          head_num,
                          hidden_dim,
                          attention_activation=None,
                          feed_forward_activation=gelu,
                          dropout_rate=0.0,
                          trainable=True,):
    """Multi-head self-attention and feed-forward layer.

    :param name: Prefix of names for internal layers.
    :param input_layer: Input layer.
	:param embed_dim: d dimension of the query, key and value vectors
    :param head_num: Number of heads in multi-head self-attention.
    :param hidden_dim: Hidden dimension of feed forward layer.
    :param attention_activation: Activation for multi-head self-attention.
    :param feed_forward_activation: Activation for feed-forward layer.
    :param dropout_rate: Dropout rate.
    :param trainable: Whether the layers are trainable.
    :return: Output layer.
    """
    attention_name = '%s-MultiHeadSelfAttention' % name
    feed_forward_name = '%s-FeedForward' % name
    attention_layer = _wrap_layer(
        name=attention_name,
        input_layer=input_layer,
        build_func=self_attention_builder(
            name=attention_name,
            embed_dim=embed_dim,
            head_num=head_num,
            activation=attention_activation,
            history_only=False,
            dropout_rate=dropout_rate,
            trainable=trainable,
			masked=False,
        ),
        dropout_rate=dropout_rate,
        trainable=trainable
    )
    feed_forward_layer = _wrap_layer(
        name=feed_forward_name,
        input_layer=attention_layer,
        build_func=feed_forward_builder(
            name=feed_forward_name,
            hidden_dim=hidden_dim,
            activation=feed_forward_activation,
            trainable=trainable,
        ),
        dropout_rate=dropout_rate,
        trainable=trainable,
    )
    return feed_forward_layer


def get_decoder_component(name,
                          input_layer,
                          encoded_layer,
                          embed_dim,
                          head_num,
                          hidden_dim,
                          attention_activation=None,
                          feed_forward_activation=gelu,
                          dropout_rate=0.0,
                          trainable=True):
    """Multi-head self-attention, multi-head query attention and feed-forward layer.

    :param name: Prefix of names for internal layers.
    :param input_layer: Input layer.
    :param encoded_layer: Encoded layer from encoder.
    :param head_num: Number of heads in multi-head self-attention.
    :param hidden_dim: Hidden dimension of feed forward layer.
    :param attention_activation: Activation for multi-head self-attention.
    :param feed_forward_activation: Activation for feed-forward layer.
    :param dropout_rate: Dropout rate.
    :param trainable: Whether the layers are trainable.
    :return: Output layer.
    """
    self_attention_name = '%s-MultiHeadSelfAttention' % name
    query_attention_name = '%s-MultiHeadQueryAttention' % name
    feed_forward_name = '%s-FeedForward' % name
    self_attention_layer = _wrap_layer(
        name=self_attention_name,
        input_layer=input_layer,
        build_func=self_attention_builder(
            name=self_attention_name,
            embed_dim=embed_dim,
            head_num=head_num,
            activation=attention_activation,
            history_only=True,
            dropout_rate=dropout_rate,
            trainable=trainable,
			masked=True,
        ),
        dropout_rate=dropout_rate,
        trainable=trainable,
    )
    query_attention_layer = _wrap_layer(
        name=query_attention_name,
        input_layer=[self_attention_layer, encoded_layer],
        build_func=attention_builder(
            name=query_attention_name,
            embed_dim=embed_dim,
            head_num=head_num,
            activation=attention_activation,
            history_only=False,
            dropout_rate=dropout_rate,
            trainable=trainable,
        ),
        dropout_rate=dropout_rate,
        trainable=trainable,
    )
    feed_forward_layer = _wrap_layer(
        name=feed_forward_name,
        input_layer=query_attention_layer,
        build_func=feed_forward_builder(
            name=feed_forward_name,
            hidden_dim=hidden_dim,
            activation=feed_forward_activation,
            trainable=trainable,
        ),
        dropout_rate=dropout_rate,
        trainable=trainable,
    )
    return feed_forward_layer


def get_encoders(encoder_num,
                 input_layer,
				 embed_dim,
                 head_num,
                 hidden_dim,
                 attention_activation=None,
                 feed_forward_activation=gelu,
                 dropout_rate=0.0,
                 trainable=True):
    """Get encoders.

    :param encoder_num: Number of encoder components.
    :param input_layer: Input layer.
    :param head_num: Number of heads in multi-head self-attention.
	:param embed_dim: d diemsion of kqery, key, value vectors
    :param hidden_dim: Hidden dimension of feed forward layer.
    :param attention_activation: Activation for multi-head self-attention.
    :param feed_forward_activation: Activation for feed-forward layer.
    :param dropout_rate: Dropout rate.
    :param trainable: Whether the layers are trainable.
    :return: Output layer.
    """
    last_layer = input_layer
    for i in range(encoder_num):
        last_layer = get_encoder_component(
            name='Encoder-%d' % (i + 1),
            input_layer=last_layer,
            embed_dim=embed_dim,
            head_num=head_num,
            hidden_dim=hidden_dim,
            attention_activation=attention_activation,
            feed_forward_activation=feed_forward_activation,
            dropout_rate=dropout_rate,
            trainable=trainable
        )
    return last_layer


def get_decoders(decoder_num,
                 input_layer,
                 encoded_layer,
				 embed_dim,
                 head_num,
                 hidden_dim,
                 attention_activation=None,
                 feed_forward_activation=gelu,
                 dropout_rate=0.0,
                 trainable=True):
    """Get decoders.

    :param decoder_num: Number of decoder components.
    :param input_layer: Input layer.
    :param encoded_layer: Encoded layer from encoder.
    :param head_num: Number of heads in multi-head self-attention.
    :param hidden_dim: Hidden dimension of feed forward layer.
    :param attention_activation: Activation for multi-head self-attention.
    :param feed_forward_activation: Activation for feed-forward layer.
    :param dropout_rate: Dropout rate.
    :param trainable: Whether the layers are trainable.
    :return: Output layer.
    """
    last_layer = input_layer
    for i in range(decoder_num):
        last_layer = get_decoder_component(
            name='Decoder-%d' % (i + 1),
            input_layer=last_layer,
            encoded_layer=encoded_layer,
            embed_dim=embed_dim,
            head_num=head_num,
            hidden_dim=hidden_dim,
            attention_activation=attention_activation,
            feed_forward_activation=feed_forward_activation,
            dropout_rate=dropout_rate,
            trainable=trainable,
        )
    return last_layer


def get_model(max_input_len,
              errNum,
              token_num,
              embed_dim,
              encoder_num,
              head_num,
              hidden_dim,
              attention_activation=None,
              feed_forward_activation=gelu,
              dropout_rate=0.0,
              use_same_embed=True,
              embed_weights=None,
              embed_trainable=None,
              trainable=True):
    """Get full model without compilation.

    :param token_num: Number of distinct tokens.
    :param embed_dim: Dimension of token embedding.
    :param encoder_num: Number of encoder components.
    :param decoder_num: Number of decoder components.
    :param head_num: Number of heads in multi-head self-attention.
    :param hidden_dim: Hidden dimension of feed forward layer.
    :param attention_activation: Activation for multi-head self-attention.
    :param feed_forward_activation: Activation for feed-forward layer.
    :param dropout_rate: Dropout rate.
    :param use_same_embed: Whether to use the same token embedding layer. `token_num`, `embed_weights` and
                           `embed_trainable` should be lists of two elements if it is False.
    :param embed_weights: Initial weights of token embedding.
    :param embed_trainable: Whether the token embedding is trainable. It will automatically set to False if the given
                            value is None when embedding weights has been provided.
    :param trainable: Whether the layers are trainable.
    :return: Keras model.
    """
    #tn = token_num

    encoder_token_num = token_num

    encoder_max_input_len = max_input_len

    encoder_embed_weights = embed_weights
    if encoder_embed_weights is not None:
        encoder_embed_weights = [encoder_embed_weights]

    encoder_embed_trainable = embed_trainable
    if encoder_embed_trainable is None:
        encoder_embed_trainable = encoder_embed_weights is None

    encoder_embed_layer = EmbeddingRet(
            input_dim=encoder_token_num,
            output_dim=embed_dim,
            mask_zero=True,
            weights=encoder_embed_weights,
            trainable=encoder_embed_trainable,
            name='Token-Embedding',
    )

    encoder_input = tf.keras.Input(shape = (encoder_max_input_len,), name = "Encoder-Input") #None-> 32, 11
    #print("In get_model: encoder_input: ", encoder_input.shape)
    encoder_embed = TrigPosEmbedding(
        mode=TrigPosEmbedding.MODE_ADD,
        name='Encoder-Embedding',
    )(encoder_embed_layer(encoder_input)[0])
    #print("In get_model: encoder_embed: ", encoder_embed.shape)
    encoded_layer = get_encoders(
        encoder_num=encoder_num,
        input_layer=encoder_embed,
		embed_dim=embed_dim,
        head_num=head_num,
        hidden_dim=hidden_dim,
        attention_activation=attention_activation,
        feed_forward_activation=feed_forward_activation,
        dropout_rate=dropout_rate,
        trainable=trainable
    )

    #print("encoded_layer:", encoded_layer)
    #print("encoded_layer shape:", encoded_layer.shape)
    #print("max_input_len, embed_dim:", max_input_len, embed_dim)
    flatten_state = keras.layers.Reshape((max_input_len*embed_dim,))(encoded_layer)
    #print("flatten_state:", flatten_state.shape)
    error_feed_forward_layer1 = keras.layers.Dense(hidden_dim,
                                                   activation="relu")(flatten_state)
    error_feed_forward_output1 = keras.layers.Dense(errNum,
                                                    activation="sigmoid",
                                                    name="error_feed_forward_output1")(error_feed_forward_layer1)
    #print("flatten_state:", flatten_state.shape)
    #print("error_feed_forward_output1:", error_feed_forward_output1.shape)
    concatted = keras.layers.Concatenate()([error_feed_forward_output1, flatten_state])

    """
    #分類器2

    LNoutputs=[]

    error_feed_forward_layer2 = keras.layers.Dense(hidden_dim,
                                                   activation="relu")(concatted)
    error_feed_forward_output2 = keras.layers.Dense(lbNum,
                                                    activation="relu",
                                                    name="error_feed_forward_output2")(error_feed_forward_layer2)

    #print("error_feed_forward_output2:", error_feed_forward_output2.shape)

    #子網路層

    for i in range(lbNum):
        output2_name = "LNout"+str(i)

        LNoutputs.append(keras.layers.Dense(max_javaline_length,activation="softmax",name=output2_name)(error_feed_forward_output2))
    """




    #print("LNoutputs:", LNoutputs)


    """
    model = keras.models.Model(inputs=[encoder_input], outputs=[error_feed_forward_output1] + LNoutputs)
    """
    model = keras.models.Model(inputs=[encoder_input], outputs=[error_feed_forward_output1])
    #model.summary()

    return model


def _get_max_suffix_repeat_times(tokens, max_len):
    detect_len = min(max_len, len(tokens))
    next = [-1] * detect_len
    k = -1
    for i in range(1, detect_len):
        while k >= 0 and tokens[len(tokens) - i - 1] != tokens[len(tokens) - k - 2]:
            k = next[k]
        if tokens[len(tokens) - i - 1] == tokens[len(tokens) - k - 2]:
            k += 1
        next[i] = k
    max_repeat = 1
    for i in range(2, detect_len):
        if next[i] >= 0 and (i + 1) % (i - next[i]) == 0:
            max_repeat = max(max_repeat, (i + 1) // (i - next[i]))
    return max_repeat


def decode(model,
           tokens, #that is , encode_input
           top_k=1,
           temperature=1.0,
           max_len=10000,
           max_repeat=10,
           max_repeat_block=10):
    """Decode with the given model and input tokens.

    :param model: The trained model.
    :param tokens: The input tokens of encoder.
    :param start_token: The token that represents the start of a sentence.
    :param end_token: The token that represents the end of a sentence.
    :param pad_token: The token that represents padding.
    :param top_k: Choose the last token from top K.
    :param temperature: Randomness in boltzmann distribution.
    :param max_len: Maximum length of decoded list.
    :param max_repeat: Maximum number of repeating blocks.
    :param max_repeat_block: Maximum length of the repeating block.
    :return: Decoded tokens.
    """
    is_single = not isinstance(tokens[0], list)
    if is_single:
        tokens = [tokens]
    #print("tokens length: ", len(tokens))
    batch_size = len(tokens)#number of inputs to translate
    #model = keras.models.Model(inputs=[encoder_input], outputs=[error_feed_forward_output1, #error_feed_forward_output2])
    """out1, *out2 = model.predict(tokens)"""
    out1 = model.predict(tokens)
    #print("out1.shape: ", out1.shape)
    #print("out2 length: ", len(out2))
    #print("out2[0] shape: ", out2[0].shape)

    return out1
