
'''
 In this version, we route the err classification output 
 as an input to the decoder, using it as Ck, Cv and perform attention 
 with Dq
'''
import numpy as np
from keras_layer_normalization import LayerNormalization
#from keras_multi_head import MultiHeadAttention
from tensorflow_fast_attention.fast_attention_2 import  Attention, SelfAttention ,softmax_kernel_transformation#New here
#from keras_position_wise_feed_forward import FeedForward
from keras_position_wise_feed_forward.feed_forward import FeedForward
from keras_pos_embd import TrigPosEmbedding
from keras_embed_sim import EmbeddingRet, EmbeddingSim
from backend import keras
from gelu import gelu
import tensorflow as tf
import tensorflow.keras as keras
from CastMask import CastMask
from classification_expanding_layer  import ClassificationExpandingLayer 
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
        'CastMask': CastMask,        
        'ClassificationExpandingLayer':ClassificationExpandingLayer,
        'softmax_kernel_transformation':softmax_kernel_transformation,        
    }

def load(model_name):   
    

    co = get_custom_objects()

    model = keras.models.load_model(model_name, custom_objects= co)    
    
    
    return model



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
    #print("Start Warpping................................")
    
    if isinstance(input_layer, list):
        build_output = build_func(input_layer[0], input_layer[1])
    else:
        build_output = build_func(input_layer)
    if dropout_rate > 0.0:
        dropout_layer = keras.layers.Dropout(
            rate=dropout_rate,
            name='%s-Dropout1' % name,
        )(build_output)
    else:
        dropout_layer = build_output
    if isinstance(input_layer, list):
        input_layer = input_layer[0]
    add_layer = keras.layers.Add(name='%s-Add1' % name)([input_layer, dropout_layer])
    normal_layer = LayerNormalization(
        trainable=trainable,
        name='%s-Norm1' % name,
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
    def _attention_builder(x, mask): #Attention(hidden_size, num_heads, dropout)
        return Attention(
			hidden_size=embed_dim,
            mask = None,
            num_heads=head_num,            
            attention_dropout=dropout_rate,
			nb_random_features= int(embed_dim / 2), #16
			causal=history_only,
			projection_matrix_type=True,
            name = '%s-Attention1' % name
        )(x,mask = mask)
    return _attention_builder

def self_attention_builder(name,
                      embed_dim,
                      mask,
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
    def _attention_builder(x, mask): #SelfAttention(hidden_size, num_heads, dropout)
        print("X is OOOO:", x)
        
        return SelfAttention(
			hidden_size=embed_dim,
            num_heads=head_num,
            mask = None,            
			attention_dropout=dropout_rate,
			nb_random_features= int(embed_dim / 2), #16
			causal=history_only,
			projection_matrix_type=True,
            name = '%s-SelfAttention2' % name
        )(x,mask = mask)
    return _attention_builder
    
def self_attention_builder_de(name,
                      embed_dim,
                      mask,
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
    def _attention_builder(x, mask): #SelfAttention(hidden_size, num_heads, dropout)
        #print("OOOO:", x)
        return SelfAttention(
			hidden_size=embed_dim,
            num_heads=head_num,
            mask = None,
			attention_dropout=dropout_rate,
			nb_random_features= int(embed_dim / 2), #16
			causal=history_only,
			projection_matrix_type=True,
            name = '%s-SelfAttention1' % name
        )(x, mask = mask)
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
                          mask,
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
    attention_name = '%s-MultiHeadSelfAttention1' % name
    feed_forward_name = '%s-FeedForward1' % name
    attention_layer = _wrap_layer( 
        name=attention_name,
        input_layer=[input_layer, mask],
        build_func=self_attention_builder(  
            name=attention_name,
            embed_dim=embed_dim, 
            mask = mask,
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
                          mask,
                          classification_layer,
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
    self_attention_name = '%s-MultiHeadSelfAttention1' % name
    query_attention_name = '%s-MultiHeadQueryAttention1' % name
    classification_attention_name = '%s-MultiHeadClassificationAttention1' % name
    feed_forward_name = '%s-FeedForward1' % name
    self_attention_layer = _wrap_layer(
        name=self_attention_name,
        input_layer=[input_layer,mask],
        build_func=self_attention_builder_de(
            name=self_attention_name,
            embed_dim=embed_dim,
            mask = mask,
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
    classification_attention_layer = _wrap_layer(
        name=classification_attention_name,
        input_layer=[query_attention_layer, classification_layer],
        build_func=attention_builder(
            name=classification_attention_name,
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
        input_layer=classification_attention_layer,
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
                 mask,                 
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
            name='1Encoder-%d' % (i + 1),
            input_layer=last_layer,
            mask = mask,
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
                 mask,
                 classification_layer,
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
    print("classification_layer shape: ",classification_layer.shape )
    #First, redim and repeat the classification layer
    classification_layer = ClassificationExpandingLayer(embed_dim)(classification_layer)
    last_layer = input_layer
    for i in range(decoder_num):
        last_layer = get_decoder_component(
            name='1Decoder-%d' % (i + 1),
            input_layer=last_layer,
            encoded_layer=encoded_layer,
            mask = mask,
            classification_layer=classification_layer,
            embed_dim=embed_dim,
            head_num=head_num,
            hidden_dim=hidden_dim,
            attention_activation=attention_activation,
            feed_forward_activation=feed_forward_activation,
            dropout_rate=dropout_rate,
            trainable=trainable,
        )
    return last_layer


def get_model(encoder_max_input_len,
              decoder_max_input_len,
              token_num,
              embed_dim,
              encoder_num,
              decoder_num,
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
    #encoder_max_input_len=999
    #tn = token_num
    if not isinstance(token_num, list) and not isinstance(token_num, tuple):
        token_num = [token_num, token_num]
    encoder_token_num, decoder_token_num = token_num
    '''
    if not isinstance(max_input_len, list) and not isinstance(max_input_len, tuple):
        max_input_len = [max_input_len, max_input_len]
    encoder_max_input_len, decoder_max_input_len = max_input_len
    '''
    if not isinstance(embed_weights, list):
        embed_weights = [embed_weights, embed_weights]
    encoder_embed_weights, decoder_embed_weights = embed_weights
    if encoder_embed_weights is not None:
        encoder_embed_weights = [encoder_embed_weights]
    if decoder_embed_weights is not None:
        decoder_embed_weights = [decoder_embed_weights]

    if not isinstance(embed_trainable, list):
        embed_trainable = [embed_trainable, embed_trainable]
    encoder_embed_trainable, decoder_embed_trainable = embed_trainable
    if encoder_embed_trainable is None:
        encoder_embed_trainable = encoder_embed_weights is None
    if decoder_embed_trainable is None:
        decoder_embed_trainable = decoder_embed_weights is None
    """    
    with tf.device('cpu:0'):
        if use_same_embed:
            encoder_embed_layer = decoder_embed_layer = EmbeddingRet(
            input_dim=encoder_token_num,
            output_dim=embed_dim,
            mask_zero=True,
            weights=encoder_embed_weights,
            trainable=encoder_embed_trainable,
            name='Token-Embedding',
            )
        else:
            encoder_embed_layer = EmbeddingRet(
            input_dim=encoder_token_num,
            output_dim=embed_dim,
            mask_zero=True,
            weights=encoder_embed_weights,
            trainable=encoder_embed_trainable,
            name='Encoder-Token-Embedding',
            )
            decoder_embed_layer = EmbeddingRet(
            input_dim=decoder_token_num,
            output_dim=embed_dim,
            mask_zero=True,
            weights=decoder_embed_weights,
            trainable=decoder_embed_trainable,
            name='Decoder-Token-Embedding',
            )
        encoder_embed_layer.build()
        decoder_embed_layer.build()
    """
    if use_same_embed:
        encoder_embed_layer = decoder_embed_layer1 = EmbeddingRet(
            input_dim=encoder_token_num,
            output_dim=embed_dim,
            mask_zero=True,
            weights=encoder_embed_weights,
            trainable=encoder_embed_trainable,
            name='Token-Embedding',
        )
    else:
        encoder_embed_layer = EmbeddingRet(
            input_dim=encoder_token_num,
            output_dim=embed_dim,
            mask_zero=True,
            weights=encoder_embed_weights,
            trainable=encoder_embed_trainable,
            name='Encoder-Token-Embedding2',
        )
        
    
    #--------mask embedding------
    #輸入   
    m_drive_path= 'C:/Users/YOLOHsu/Desktop/10366021/code/model/'
    mask_model = load(m_drive_path + 'embeddim_32_enocoder_6_500.h5') 
    mask_model.trainable = False
    mask_model.summary()
    #test_input = []
    inp = mask_model.input
    out = mask_model.output
    print('model ok')
    print('---perfomer mask---')
    '''
    org_encoder_input = tf.keras.Input(shape=(encoder_max_input_len,), name='Encoder-Input') #None-> 32, 11
    print('--------------------------------------------------------------------')
    print("org_encoder_input:", org_encoder_input)
    print("org_encoder_input shape:", org_encoder_input.shape)
    print('--------------------------------------------------------------------')
   
    org_mask_input = tf.keras.Input(shape=(encoder_max_input_len,), name='Mask-Input2')
    #test_input.append(org_encoder_input)
    #test_input.append(org_mask_input)

    #org_mask_input = tf.keras.Input(shape=(encoder_max_input_len,), name='Mask-Encoder-Input',dtype=tf.int32)
    print("org_mask_input:", org_mask_input)
    print("org_mask_input shape:", org_mask_input.shape)
    print('--------------------------------------------------------------------')
    
    '''
    
    #encoded_layer = mask_model([inp,out])    
    #print("encoded_layer:", encoded_layer)
    #print("encoded_layer shape:", encoded_layer.shape)
    print('--------------------------------------------------------------------')
    
    
    print('model creat is ok')
    
    
    #mask_encoder_input = tf.cast(org_mask_input, tf.bool, name='Change-Mask-Type')
    #encoder_input_1 = CastMask()([org_encoder_input, org_mask_input])   
    #encoder_input_1 = CastMask()(org_encoder_input) 
    '''
    print("mask_encoder_input:", mask_encoder_input)
    print("mask_encoder_input shape:", mask_encoder_input.shape)
    print('--------------------------------------------------------------------')
    
    encoder_input_1 = tf.where(org_mask_input, org_encoder_input, 0, name='Masking_input_to_0') # masking input to 0
    print("encoder_input_1:", encoder_input_1)
    print("encoder_input_1 shape:", encoder_input_1.shape)
    print('--------------------------------------------------------------------')
    
    
    #encoder_input = tf.keras.layers.Masking(mask_value=0.)(encoder_input_1)
    print("encoder_input:", encoder_input)
    print("encoder_input shape:", encoder_input.shape)
    print('--------------------------------------------------------------------')
    
    encoder_embed2 = encoder_embed_layer(encoder_embed1)
    
    encoder_embed = TrigPosEmbedding(
    mode=TrigPosEmbedding.MODE_ADD,
    name='Encoder-Input-Mask',
    )(encoder_embed2)
     
    
    
    print("encoder_input:", encoder_input)
    print("encoder_input shape:", encoder_input.shape)
    print('--------------------------------------------------------------------')
    '''
    #print("mask_encoder_input:", mask_encoder_input)
    #print("mask_encoder_input shape:", mask_encoder_input.shape)
    #print('--------------------------------------------------------------------')
    
    #mask_embed = tf.keras.layers.Embedding(input_dim=encoder_token_num, output_dim=embed_dim, mask_zero=True, name='Encoder-Input-Mask')    
    #encoder_embed = mask_embed(encoder_input)
    #encoder_input = keras.layers.Reshape((encoder_max_input_len*embed_dim,))
    
    #encoder_input = tf.keras.Input(shape=(encoder_max_input_len,), name='Encoder-Input') #None-> 32, 11
    #print("In get_model: encoder_input: ", encoder_input.get_shape())
    #print("In get_model: encoder_inputp[0]: ", encoder_input[0])
    #print("encoder_embed_layer: encoder_input: ", encoder_embed_layer.shape)
    '''
    encoder_embed = TrigPosEmbedding(
        mode=TrigPosEmbedding.MODE_ADD,
        name='Encoder-Input-Mask',
    )(encoder_embed_layer(encoder_input)[0])
    
    mask_encoder_input_dem = TrigPosEmbedding(
        mode=TrigPosEmbedding.MODE_ADD,
        name='Mask-dem-Input',
    )(encoder_embed_layer(mask_encoder_input)[0])
    
    print("In get_model: encoder_embed: ", encoder_embed)
    print('--------------------------------------------------------------------')
    #print("In get_model: mask_encoder_input_dem: ", mask_encoder_input_dem.shape)
    
    encoded_layer = get_encoders(
        encoder_num=encoder_num,
        input_layer=encoder_embed,
        mask = None,
        embed_dim=embed_dim,
        head_num=head_num,
        hidden_dim=hidden_dim,
        attention_activation=attention_activation,
        feed_forward_activation=feed_forward_activation,
        dropout_rate=dropout_rate,
        trainable=trainable
    )
    print("encoded_layer:", encoded_layer)
    print("encoded_layer shape:", encoded_layer.shape)
    print('--------------------------------------------------------------------')
    '''
    #########################################################################################
    ## MLP for MASK
    #mask_forward_layer = keras.layers.Dense(embed_dim, activation="gelu", name='Mask_forward_layer')(encoded_layer)
    #mask_norm_layer = tf.keras.layers.LayerNormalization(axis=1,name = 'Mask_Norm_Layer')(mask_forward_layer)
    
    '''
    mask_norm_layer = keras.layers.Dense(embed_dim, activation="gelu", name='Mask_forward_layer')(encoded_layer)
    
    
    
    
    
    #mask_norm_layer_weights = encoder_embed_layer(mask_norm_layer)
    #encoder_embed_weights = encoder_embed_layer(encoder_input)
    
    #print('mask_norm_layer : ', mask_norm_layer_weights)
    #print('encoder_embed_weights : ', encoder_embed_weights)
           
    #mask_forward_layer_out = keras.layers.Dense(embed_dim, activation="gelu", name='Mask_forward_layer_out')(mask_norm_layer)
    #print("Mask_forward_layer shape : ", mask_forward_layer.shape)
    #print("Mask_forward_layer: ", mask_forward_layer)
    print('--------------------------------------------------------------------')
    print("mask_norm_layer shape : ", mask_norm_layer.shape)
    print("mask_norm_layer: ", mask_norm_layer)
    print('--------------------------------------------------------------------')
    
    mask_forward_layer_out = EmbeddingSim(
    trainable=trainable,
    name='Mask_forward_layer_out',
    )([mask_norm_layer, encoder_embed_weights])
    '''
    #########################################################################################    
    
    ###################################
    ## we need MLP error classifier
    #print("encoded_layer:", encoded_layer)
    #print("encoded_layer shape:", encoded_layer.shape)
    flatten_state = keras.layers.Reshape((encoder_max_input_len*embed_dim,))(out)
    #print("flatten_state:", flatten_state.shape)
    #error_feed_forward_layer1 = keras.layers.Dense(hidden_dim, activation="relu" )(flatten_state)
    
    error_feed_forward_layer1 = keras.layers.Dense(hidden_dim, activation="relu" )(flatten_state)
    print("error_feed_forward_layer1 shape : ", error_feed_forward_layer1.shape)
    print("error_feed_forward_layer1: ", error_feed_forward_layer1)
    print('--------------------------------------------------------------------')
    #error_feed_forward_layer1 = keras.layers.Dense(hidden_dim, activation="relu" )(error_feed_forward_layer1)
    
    error_feed_forward_output1 = keras.layers.Dense(36,activation="sigmoid",name="error_feed_forward_output1")(error_feed_forward_layer1)
    #print("flatten_state : ", flatten_state)
    #print("flatten_state shape :", flatten_state.shape)
    print('--------------------------------------------------------------------')
    print("error_feed_forward_output1 : ", error_feed_forward_output1)
    print("error_feed_forward_output1 shape : ", error_feed_forward_output1.shape)
    print('--------------------------------------------------------------------')
    ###################################
    
    
    
    ###Decoder for message
    decoder_input =  tf.keras.Input(shape=(decoder_max_input_len,), name='Decoder-Input') #None-> 32, 13
    decoder_embed_layer11 = EmbeddingRet(
            input_dim=encoder_token_num,
            output_dim=embed_dim,
            mask_zero=True,
            weights=encoder_embed_weights,
            trainable=encoder_embed_trainable,
            name='Encoder-Token-Embedding1',
        )
    
    decoder_embed, decoder_embed_weights = decoder_embed_layer11(decoder_input)
    decoder_embed = TrigPosEmbedding(
        mode=TrigPosEmbedding.MODE_ADD,
        name='Decoder-Embedding',
    )(decoder_embed)
    decoded_layer = get_decoders(
        decoder_num=decoder_num,
        input_layer=decoder_embed,
        encoded_layer=out,
        mask = None,
        classification_layer = error_feed_forward_output1,
		embed_dim=embed_dim,
        head_num=head_num,
        hidden_dim=hidden_dim,
        attention_activation=attention_activation,
        feed_forward_activation=feed_forward_activation,
        dropout_rate=dropout_rate,
        trainable=trainable,
    )
    output_layer = EmbeddingSim(
        trainable=trainable,
        name='Decoder-Output',
    )([decoded_layer, decoder_embed_weights])
    
        
    #return keras.models.Model(inputs=[encoder_input, decoder_input], outputs=output_layer)
    
    
    #return keras.models.Model(inputs=[encoder_input, mask_encoder_input, decoder_input], outputs=[error_feed_forward_output1, output_layer])
    #return keras.models.Model(inputs=[org_encoder_input, org_mask_input], outputs=[mask_forward_layer_out])
    return keras.models.Model(inputs= inp+[decoder_input], outputs=[error_feed_forward_output1,output_layer])


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
           tokens,
           start_token,
           end_token,
           pad_token,
           top_k=1,
           temperature=1.0,
           max_len=10000,
           max_repeat=10,
           max_repeat_block=10):
    """Decode with the given model and input tokens.

    :param model: The trained model.
    :param tokens: The input tokens of encoder. It should be a list of list structure:範例個數(235)*範例長度(799)
    :param start_token: The token that represents the start of a sentence.
    :param end_token: The token that represents the end of a sentence.
    :param pad_token: The token that represents padding.
    :param top_k: Choose the last token from top K.
    :param temperature: Randomness in boltzmann distribution.
    :param max_len: Maximum length of decoded list. 例如799
    :param max_repeat: Maximum number of repeating blocks.
    :param max_repeat_block: Maximum length of the repeating block.
    :return: Decoded tokens.
    """
    is_single = not isinstance(tokens[0], list) #it should be [[t1,t2, t799], s2, ..., s235]
    if is_single:
        tokens = [tokens]
    batch_size = len(tokens)#number of inputs to translate
    print('~~~~~~batch_size : ', batch_size)
    decoder_inputs = [[start_token] for _ in range(batch_size)] #[ ['<START>'], .....]
    errorTypes = [None for _ in range(batch_size)]#[NONE, ...]
    outputs = [None for _ in range(batch_size)]#[NONE, ...]
    output_len = 1 # Current prediction length (1 because of <START>)
    while len(list(filter(lambda x: x is None, outputs))) > 0: # if there are unsolved predictions
        output_len += 1
        #print("Step: ", output_len)
        lmmodel, batch_inputs, batch_outputs = [], [], []
        index_map = {}
        for i in range(batch_size): #collect unsolved inputs
            if outputs[i] is None: #it is unsolved
                index_map[len(batch_inputs)] = i #collect its sample index
                batch_inputs.append(tokens[i][:]) #the ith sample
                lmmodel.append(tokens[i][:])
                batch_outputs.append(decoder_inputs[i].copy())
        print(type(max_len), max_len)
        
        for i in range(len(lmmodel)): #Padding 
            #print(type(batch_inputs[i]), batch_inputs[i])        
            lmmodel[i] += [pad_token] * (max_len - len(lmmodel[i]))        
        for i in range(len(batch_inputs)): #Padding 
            #print(type(batch_inputs[i]), batch_inputs[i])        
            batch_inputs[i] += [pad_token] * (max_len - len(batch_inputs[i])) 
        for i in range(len(batch_outputs)):	
            batch_outputs[i] += [pad_token] * (776 - len(batch_outputs[i]))
        print("Predict inputs:",np.array(lmmodel).shape, np.array(batch_inputs).shape, np.array(batch_outputs).shape)
        print("lmmodel:",np.array(lmmodel))
        print("batch_inputs:",np.array(batch_inputs))
        print("batch_outputs:",np.array(batch_outputs))
        #lmans, predictErrorTypes, predicts = model.predict([np.array(lmmodel), np.array(batch_inputs), np.array(batch_outputs)], batch_size=512)
        predictErrorTypes, predicts = model.predict([np.array(lmmodel), np.array(batch_inputs), np.array(batch_outputs)], batch_size=2)
        for i in range(len(predicts)): #for i'th predict
            if top_k == 1:
#                print("predicts[i][-1]: ", predicts[i][-1])
                last_token = predicts[i][output_len-2].argmax(axis=-1)
#                print("last_token: ", last_token)
            else:
                probs = [(prob, j) for j, prob in enumerate(predicts[i][-1])]
                probs.sort(reverse=True)
                probs = probs[:top_k]
                indices, probs = list(map(lambda x: x[1], probs)), list(map(lambda x: x[0], probs))
                probs = np.array(probs) / temperature
                probs = probs - np.max(probs)
                probs = np.exp(probs)
                probs = probs / np.sum(probs)
                last_token = np.random.choice(indices, p=probs)

            decoder_inputs[index_map[i]].append(last_token)#add the last token
            #print("new decoder_inputs:",np.array(decoder_inputs))
            #print("type(last_token):",type(last_token))
            #print("last_token:",last_token)
            if last_token == end_token or\
                    (max_len is not None and output_len >= max_len) or\
                    _get_max_suffix_repeat_times(decoder_inputs[index_map[i]],
                                                 max_repeat * max_repeat_block) >= max_repeat:
                outputs[index_map[i]] = decoder_inputs[index_map[i]]
                errorTypes[index_map[i]] = 	predictErrorTypes[i]
                #lmmodelans[index_map[i]] = 	lmans[i]                
#        print("Predited Outputs: ", outputs)	
    if is_single:
        outputs = outputs[0]
    return errorTypes, outputs
    
