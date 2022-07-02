from backend import keras
from backend import backend as K


class ClassificationExpandingLayer(keras.layers.Layer):
    """ 
    A wrapper that accept max_groups X max_group_size of token embeddings and do self-attention only within groups
    """

    def __init__(self,
                 embed_dim,
                 **kwargs):
        """Initialize the layer.

        :param encoder_num: Number of encoder components.
        :param input_layer: Input layer. (Samples, max_number_of_groups, max_group_size X embed_dim)
        :param head_num: Number of heads in multi-head self-attention.
        :param hidden_dim: Hidden dimension of feed forward layer.
        :param attention_activation: Activation for multi-head self-attention.
        :param feed_forward_activation: Activation for feed-forward layer.
        :param dropout_rate: Dropout rate.
        :param trainable: Whether the layers are trainable.
        :return: Output layer.
        :param history_only: Whether to only use history in attention layer.
        """
        self.supports_masking = True
        self.embed_dim = embed_dim     
        super(ClassificationExpandingLayer, self).__init__(**kwargs)

    def get_config(self):
        config = {
            'embed_dim': self.embed_dim,
        }
        base_config = super(ClassificationExpandingLayer, self).get_config()
        return dict(list(base_config.items()) + list(config.items()))

    def compute_output_shape(self, input_shape):
         return (input_shape[0], input_shape[1], self.embed_dim)


    def call(self, inputs, mask=None):
        #First, redim and repeat the classification layer
        expanded_inputs = K.expand_dims(inputs, axis=2)
        expanded_inputs = K.tile(expanded_inputs, (1,1,self.embed_dim))
        y = expanded_inputs
        return y
