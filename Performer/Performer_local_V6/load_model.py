def load(model_name):
        import sys
        sys.path.append('C:/Users/fhwan/Downloads/修正資料量/local_training')
        sys.path.append('C:/Users/fhwan/Downloads/修正資料量/local_training/keras_position_wise_feed_forward')
        sys.path.append('C:/Users/fhwan/Downloads/修正資料量/local_training/tensorflow_fast_attention')
        sys.path.append('C:/Users/fhwan/Downloads/修正資料量/local_training/keras_performer')
        #sys.path.append('/content/keras_position_wise_feed_forward')
        #sys.path.append('/content/tensorflow_fast_attention')
        #sys.path.append('/content/keras_performer')

        from keras_performer import performerVer3 #需要更正是哪一版的performer
        from tensorflow import keras
        from keras_embed_sim import EmbeddingRet, EmbeddingSim
        from keras_pos_embd import TrigPosEmbedding
        from tensorflow_fast_attention.fast_attention import softmax_kernel_transformation , Attention, SelfAttention #新增softmax_kernel_transformation
        from keras_position_wise_feed_forward.feed_forward import FeedForward  

        co = performerVer3.get_custom_objects()  #需要更正是哪一版的performer
        co["softmax_kernel_transformation"] = softmax_kernel_transformation #新增softmax_kernel_transformation
        model = keras.models.load_model(model_name, custom_objects= co)
        s = loadDictionary('C:/Users/fhwan/Downloads/修正資料量/model/source_token_dict.pickle')
        t = loadDictionary('C:/Users/fhwan/Downloads/修正資料量/model/target_token_dict.pickle')
        t_inv = loadDictionary('C:/Users/fhwan/Downloads/修正資料量/model/target_token_dict_inv.pickle')
        return model, s, t, t_inv