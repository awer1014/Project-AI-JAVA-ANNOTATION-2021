
'''
 In this version, we route the err classification output 
 as an input to the decoder, using it as Ck, Cv and perform attention 
 with Dq
'''
import numpy as np
from keras_layer_normalization import LayerNormalization
#from keras_multi_head import MultiHeadAttention
from tensorflow_fast_attention.fast_attention_2 import  Attention, SelfAttention #New here
#from keras_position_wise_feed_forward import FeedForward
from keras_position_wise_feed_forward.feed_forward import FeedForward
from keras_pos_embd import TrigPosEmbedding
from keras_embed_sim import EmbeddingRet, EmbeddingSim
from backend import keras
from gelu import gelu
import tensorflow as tf
from classification_expanding_layer  import ClassificationExpandingLayer 
import sys
from transformers import TFBertModel
from bert import BertModelLayer
import creatmodel
import model

__all__ = [
    'get_custom_objects', 'get_encoders', 'get_decoders', 'get_model', 'decode',
    'attention_builder', 'feed_forward_builder', 'get_encoder_component', 'get_decoder_component',
]
'''
class Bert_layer(TFBertModel):
    def __init__(self, config):
        super(Bert_layer, self).__init__(config)
        self.num_hidden_layers = config.num_hidden_layers
        self.index = -1
 
    def get_encoder_layer(self,
                          inputs,
                          attention_mask=None,
                          token_type_ids=None,
                          position_ids=None,
                          head_mask=None,
                          inputs_embeds=None,
                          output_attentions=None,
                          output_hidden_states=None,
                          return_dict=None,
                          training=False
                          ):
        if isinstance(inputs, (tuple, list)):
            input_ids = inputs[0]
            attention_mask = inputs[1] if len(inputs) > 1 else attention_mask
            token_type_ids = inputs[2] if len(inputs) > 2 else token_type_ids
            position_ids = inputs[3] if len(inputs) > 3 else position_ids
            head_mask = inputs[4] if len(inputs) > 4 else head_mask
            inputs_embeds = inputs[5] if len(inputs) > 5 else inputs_embeds
            output_attentions = inputs[6] if len(inputs) > 6 else output_attentions
            output_hidden_states = inputs[7] if len(inputs) > 7 else output_hidden_states
            return_dict = inputs[8] if len(inputs) > 8 else return_dict
            assert len(inputs) <= 9, "Too many inputs."
        elif isinstance(inputs, (dict)):
            input_ids = inputs.get("input_ids")
            attention_mask = inputs.get("attention_mask", attention_mask)
            token_type_ids = inputs.get("token_type_ids", token_type_ids)
            position_ids = inputs.get("position_ids", position_ids)
            head_mask = inputs.get("head_mask", head_mask)
            inputs_embeds = inputs.get("inputs_embeds", inputs_embeds)
            output_attentions = inputs.get("output_attentions", output_attentions)
            output_hidden_states = inputs.get("output_hidden_states", output_hidden_states)
            return_dict = inputs.get("return_dict", return_dict)
            assert len(inputs) <= 9, "Too many inputs."
        else:
            input_ids = inputs
 
        output_hidden_states = True
        print(attention_mask)
        extended_attention_mask = attention_mask[:, tf.newaxis, tf.newaxis, :]
        extended_attention_mask = tf.cast(extended_attention_mask, tf.float32)
        extended_attention_mask = (1.0 - extended_attention_mask) * -10000.0
        if head_mask is not None:
            raise NotImplementedError
        else:
            head_mask = [None] * self.num_hidden_layers
            # head_mask = tf.constant([0] * self.num_hidden_layers)
 
        embedding_output = self.bert.embeddings(input_ids, position_ids, token_type_ids, inputs_embeds, training=training)
        
        encoder_outputs = self.bert.encoder(
            embedding_output,
            encoder_attention_mask = None,
            attention_mask = extended_attention_mask,
            head_mask=head_mask,
            output_attentions=output_attentions,
            output_hidden_states=output_hidden_states,
            return_dict=return_dict,
            use_cache = False,
            past_key_values = None,
            encoder_hidden_states = None,
 
        )
        print('encoder_outputs: ', encoder_outputs ,"end")
        
        return encoder_outputs[0][self.index]
'''
def loadDictionary(file):
    import pickle
    a_file = open(file, "rb")
    dt = pickle.load(a_file)
    return dt
    
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
        'ClassificationExpandingLayer':ClassificationExpandingLayer,
    }

def go_model(bert_params):
            l_bert = model.BertModelLayer.from_params(bert_params)

            token_ids = keras.layers.Input(shape=(21,))
            seq_out = l_bert(token_ids)
            model = keras.Model(inputs=[token_ids], outputs=seq_out)

            model.build(input_shape=(None, 21))
            l_bert.apply_adapter_freeze()

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
			causal=history_only,
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
        #print("OOOO:", x)
        return SelfAttention(
			hidden_size=embed_dim,
			num_heads=head_num,
			attention_dropout=dropout_rate,
			nb_random_features= int(embed_dim / 2), #16
			causal=history_only,
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
    self_attention_name = '%s-MultiHeadSelfAttention' % name
    query_attention_name = '%s-MultiHeadQueryAttention' % name
    classification_attention_name = '%s-MultiHeadClassificationAttention' % name
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

#model = Bert_layer.from_pretrained('bert-base-uncased')





def get_encoders(encoder_num,
         input_layer,
				 embed_dim,
         head_num,
         hidden_dim,
         attention_activation=None,
         feed_forward_activation=gelu,
         dropout_rate=0.0,
         trainable=True): 
    
     
    

    last_layer  = Input()
    return last_layer     
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
    """

    


def get_decoders(decoder_num,
                 input_layer,
                 encoded_layer,
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
            name='Decoder-%d' % (i + 1),
            input_layer=last_layer,
            encoded_layer=encoded_layer,
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


def get_model(encoder_max_input_len,#999
              decoder_max_input_len,#767
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
    #tn = token_num    
    decoder_token_num = token_num 
    decoder_embed_weights = embed_weights
    
    if decoder_embed_weights is not None:
        decoder_embed_weights = [decoder_embed_weights]

    
    decoder_embed_trainable = embed_trainable
    
    if decoder_embed_trainable is None:
        decoder_embed_trainable = decoder_embed_weights is None    
         
    print('decoder_token_num  :  ',decoder_token_num)
    
    decoder_embed_layer = EmbeddingRet(
        input_dim=decoder_token_num,
        output_dim=embed_dim,
        mask_zero=True,
        weights=decoder_embed_weights,
        trainable=decoder_embed_trainable,
        name='Decoder-Token-Embedding',
    )
    '''
    l_bert = BertModelLayer(**BertModelLayer.Params(
      vocab_size               = encoder_max_input_len,        # embedding params
      use_token_type           = True,
      use_position_embeddings  = True,
      token_type_vocab_size    = 2,
      num_layers               = 12,           # transformer encoder params
      hidden_size              = 768,#768
      hidden_dropout           = 0.1,
      intermediate_size        = 4*768,
      intermediate_activation  = "gelu",
      adapter_size             = None,      # see arXiv:1902.00751 (adapter-BERT)
      shared_layer             = False,        # True for ALBERT (arXiv:1909.11942)
      embedding_size           = None,         # None for BERT, wordpiece embedding size for ALBERT
    ),
      name                     = "bert"        # any other Keras layer params
    )
    
    max_seq_len = 128
    l_input_ids      = keras.layers.Input(shape=(max_seq_len,), dtype='int32')
    l_token_type_ids = keras.layers.Input(shape=(max_seq_len,), dtype='int32')
    output = l_bert(l_input_ids)                              # output: [batch_size, max_seq_len, hidden_size]
    bert_model = keras.Model(inputs=l_input_ids, outputs=output)
    bert_model.build(input_shape=(None, max_seq_len))    
    '''
    #bert_model = TFBertModel.from_pretrained('bert-base-uncased')
    bert_params = model.BertModelLayer.Params(  hidden_size=32, #每一個token長度32
                                                vocab_size=67,  #總字數
                                                max_position_embeddings=1000,  #一個token編碼1000 
                                                #num_layers=6,     #transformer 層數
                                                num_heads=1,
                                                intermediate_size=4,#隱藏層神經元個數 128
                                                dropout_rate=0.0,
                                                #use_token_type=False,
                                                #embedding_size=False,  # using ALBERT instead of BERT #16
                                                #project_embeddings_with_bias=False,
                                                #shared_layer=True,
                                                #extra_tokens_vocab_size=3,
                                                )    
    bert_model = go_model(bert_params)
    
    embedding_layer = bert_model.bert.embeddings
    encoder_layer = bert_model.bert.encoder 

    input_ids = tf.keras.Input(shape=(encoder_max_input_len,), name='Encoder-Input', dtype=tf.int32)
    #input_ids = tf.keras.Input(shape=(768,)) 
    position_ids = None 
    token_type_ids = tf.keras.Input(shape=(encoder_max_input_len,), name='token_type_ids-Encoder-Input', dtype=tf.int32)
    inputs_embeds = None
    #training = False

    print('input_ids  : ',input_ids )
    print('position_ids : ',position_ids)
    print('token_type_ids : ',token_type_ids)
    print('inputs_embeds : ',inputs_embeds)
    
    encoder_embedding = embedding_layer(input_ids,position_ids,token_type_ids,inputs_embeds)
    
    
    
    embedding_output = encoder_embedding
    encoder_attention_mask = None
    attention_mask = tf.keras.Input(shape=(encoder_max_input_len,), name='attention_mask-Encoder-Input')
    head_mask = None
    output_attentions = None
    output_hidden_states = None
    return_dict = None
    use_cache = False
    past_key_values = None
    encoder_hidden_states = None
    print('attention_mask  : ',attention_mask )
    print('encoder_attention_mask : ',encoder_attention_mask)
    print('head_mask : ',head_mask)
    print('output_attentions : ',output_attentions)
    print(' output_hidden_states : ', output_hidden_states)
    print('return_dict : ',return_dict)
    print('past_key_values : ',past_key_values)
    print('encoder_hidden_states : ', encoder_hidden_states)

    encoded_layer = encoder_layer ( embedding_output,
                      encoder_attention_mask,
                      attention_mask,head_mask,
                      output_attentions,
                      output_hidden_states,
                      return_dict,
                      use_cache,
                      past_key_values,
                      encoder_hidden_states)
    print('encoded_layer : ',encoded_layer)
    encoded_layer = encoded_layer [0]
    #encoded_layer = model = Bert_layer.from_pretrained('bert-base-uncased')
    ###################################
    ## we need MLP error classifier
    #print("encoded_layer:", encoded_layer)
    #print("encoded_layer shape:", encoded_layer.shape)
    flatten_state = keras.layers.Reshape((encoder_max_input_len*embed_dim,))(encoded_layer)
    #print("flatten_state:", flatten_state.shape)
    error_feed_forward_layer1 = keras.layers.Dense(hidden_dim, activation="relu" )(flatten_state)
    #error_feed_forward_layer1 = keras.layers.Dense(hidden_dim, activation="relu" )(error_feed_forward_layer1)
    error_feed_forward_output1 = keras.layers.Dense(36,activation="sigmoid",name="error_feed_forward_output1")(error_feed_forward_layer1)
    #print("flatten_state:", flatten_state.shape)
    #print("error_feed_forward_output1:", error_feed_forward_output1.shape)
    ###################################
    
    decoder_input =  tf.keras.Input(shape=(decoder_max_input_len,), name='Decoder-Input') #None-> 32, 13
    decoder_embed, decoder_embed_weights = decoder_embed_layer(decoder_input)
    decoder_embed = TrigPosEmbedding(
        mode=TrigPosEmbedding.MODE_ADD,
        name='Decoder-Embedding',
    )(decoder_embed)
    decoded_layer = get_decoders(
        decoder_num=decoder_num,
        input_layer=decoder_embed,
        encoded_layer=encoded_layer,
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
    return keras.models.Model(inputs=[input_ids, token_type_ids, attention_mask, decoder_input], outputs=[error_feed_forward_output1, output_layer])


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
    decoder_inputs = [[start_token] for _ in range(batch_size)] #[ ['<START>'], .....]
    errorTypes = [None for _ in range(batch_size)]#[NONE, ...]
    outputs = [None for _ in range(batch_size)]#[NONE, ...]
    output_len = 1 # Current prediction length (1 because of <START>)
    while len(list(filter(lambda x: x is None, outputs))) > 0: # if there are unsolved predictions
        output_len += 1
        #print("Step: ", output_len)
        batch_inputs, batch_outputs = [], []
        index_map = {}
        for i in range(batch_size): #collect unsolved inputs
            if outputs[i] is None: #it is unsolved
                index_map[len(batch_inputs)] = i #collect its sample index
                batch_inputs.append(tokens[i][:]) #the ith sample
                batch_outputs.append(decoder_inputs[i].copy())
        #print(type(max_len), max_len)
        for i in range(len(batch_inputs)): #Padding 
            #print(type(batch_inputs[i]), batch_inputs[i])        
            batch_inputs[i] += [pad_token] * (max_len - len(batch_inputs[i])) 
        for i in range(len(batch_outputs)):	
            batch_outputs[i] += [pad_token] * (max_len - len(batch_outputs[i]))
        #print("Predict inputs:",np.array(batch_inputs).shape, np.array(batch_outputs).shape)
        #print("batch_inputs:",np.array(batch_inputs))
        #print("batch_outputs:",np.array(batch_outputs))
        #predictErrorTypes, predicts = model.predict([np.array(batch_inputs), np.array(batch_outputs)], batch_size=512)
        predictErrorTypes, predicts = model.predict([np.array(batch_inputs), np.array(batch_outputs)], batch_size=875)
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
#        print("Predited Outputs: ", outputs)	
    if is_single:
        outputs = outputs[0]
    return errorTypes, outputs
