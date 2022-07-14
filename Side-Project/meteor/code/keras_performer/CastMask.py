import tensorflow as tf
import tensorflow.keras as keras
from tensorflow.keras import backend as K



class CastMask(keras.layers.Layer):
    """Multi-head attention layer.
    See: https://arxiv.org/pdf/1706.03762.pdf
    """

    def __init__(self,          
                 **kwargs):
        """Initialize the layer.    
        """
        self.supports_masking = True
        
        super(CastMask, self).__init__(**kwargs)

    def get_config(self):
        config = {            
        }
        base_config = super(CastMask, self).get_config()
        return dict(list(base_config.items()) + list(config.items()))

    def compute_output_shape(self, input_shape):
        '''
        if isinstance(input_shape, list):
            q, k = input_shape
            return q[:-1] + (k[-2],)
        '''
        return input_shape

    def compute_mask(self, inputs, input_mask=None):
        '''
        if isinstance(input_mask, list):
            return input_mask[0]
        '''
        return input_mask

    def build(self, input_shape):
        '''
        if isinstance(input_shape, list):
            q, k= input_shape
        else:
            q = k = input_shape
        feature_dim = int(k[-1])
        '''
        
        super(CastMask, self).build(input_shape)
    """
    @staticmethod

    def _reshape_to_batches(x, head_num):
        input_shape = K.shape(x)
        batch_size, seq_len, feature_dim = input_shape[0], input_shape[1], input_shape[2]
        head_dim = feature_dim // head_num
        x = K.reshape(x, (batch_size, seq_len, head_num, head_dim))
        x = K.permute_dimensions(x, [0, 2, 1, 3])
        return K.reshape(x, (batch_size * head_num, seq_len, head_dim))

    @staticmethod
    def _reshape_attention_from_batches(x, head_num):
        input_shape = K.shape(x)
        batch_size, seq_len, feature_dim = input_shape[0], input_shape[1], input_shape[2]
        x = K.reshape(x, (batch_size // head_num, head_num, seq_len, feature_dim))
        return K.permute_dimensions(x, [0, 2, 1, 3])

    @staticmethod
    def _reshape_from_batches(x, head_num):
        input_shape = K.shape(x)
        batch_size, seq_len, feature_dim = input_shape[0], input_shape[1], input_shape[2]
        x = K.reshape(x, (batch_size // head_num, head_num, seq_len, feature_dim))
        x = K.permute_dimensions(x, [0, 2, 1, 3])
        return K.reshape(x, (batch_size // head_num, seq_len, feature_dim * head_num))
    
    @staticmethod
    def _reshape_mask(mask, head_num):
        if mask is None:
            return mask
        seq_len = K.shape(mask)[1]
        mask = K.expand_dims(mask, axis=1)
        mask = K.tile(mask, [1, head_num, 1])
        return K.reshape(mask, (-1, seq_len))
    """
    def call(self, inputs, mask=None):
        
        mask_encoder_input = tf.cast(inputs[1], tf.bool)
        output = tf.where(mask_encoder_input, inputs[0], 0)
        #output = tf.multiply(inputs[1], inputs[0])        
        #output = inputs
        print('--------------------------------------------------------------------')
        print('output:' , output)
        print('--------------------------------------------------------------------')
        return output