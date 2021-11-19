import numpy as np
import keras
import DataBuffer as db

#for error line block detection
#input: list_IDs=a list of encoder_input_ids
#output: [out1, out2]
#out1: [e1, e2, ..., e36], ei is either 0 or 1
#out2: [ tvector1, tvector2, .......], tvector_i: one-hot-encodeing [000,1,000](Max_Seq_Length)

class DataGeneratorValidation(keras.utils.Sequence):
    'Generates data for Keras'
    def __init__(self,
                 input_databuffer_params,
                 output_databuffer_params,
                 list_IDs,
                 batch_size=64, #defult 2048
                 total_error_types=36,
                 max_text_len=302, #default 2769
                 max_lines=160,
                 max_lbs=360,
                 shuffle=True
                ):
        'Initialization'
        self.total_error_types = total_error_types
        self.max_text_len = max_text_len
        self.batch_size = batch_size
        self.max_lines = max_lines
        self.max_lbs = max_lbs
        #self.err_labels = err_labels # out 1
        #self.messages = messages #out 2
        self.list_IDs = list_IDs
        self.shuffle = shuffle
        self.input_databuffer_params = input_databuffer_params
        self.output_databuffer_params = output_databuffer_params
        # Generate input data buffer
        data_path = self.input_databuffer_params["data_path"]
        data_number = self.input_databuffer_params["data_number"]
        data_type = self.input_databuffer_params["data_type"]
        block_size = self.input_databuffer_params["block_size"]
        self.dbx1 = db.DataBuffer(data_path, data_number, data_type, block_size, file_name = "x_validation_")
        # Generate output data buffer
        data_path = self.output_databuffer_params["data_path"]
        data_number = self.output_databuffer_params["data_number"]
        data_type = self.output_databuffer_params["data_type"]
        block_size = self.output_databuffer_params["block_size"]
        self.dby1 = db.DataBuffer(data_path[0], data_number[0], data_type[0], block_size[0], file_name = "y_validation[0]_")
        self.dby2 = db.DataBuffer(data_path[1], data_number[1], data_type[1], block_size[1], file_name = "y_validation[1]_")

        self.on_epoch_end()

    def __len__(self):
        'Denotes the number of batches per epoch'
        return int(np.floor(len(self.list_IDs[0]) / self.batch_size))

    def __getitem__(self, index):
        'Generate one batch of data'
        # Generate indexes of the batch
        #print("index type: ", type(index))
        #print("self.batch_size: ", self.batch_size)
        indexes = self.indexes[index*self.batch_size:(index+1)*self.batch_size]
        '''
        print("indexes type: ", type(indexes))
        print("indexes length: ", len(indexes))
        print("self.indexes type: ", type(self.indexes))
        print("self.indexes length: ", len(self.indexes))
        print("list_IDs type: ", type(self.list_IDs))
        print("list_IDs length: ", len(self.list_IDs))
        print("list_IDs[0] type: ", type(self.list_IDs[0]))
        print("list_IDs[0] length: ", len(self.list_IDs[0]))
        print("list_IDs[1] type: ", type(self.list_IDs[1]))
        print("list_IDs[1] length: ", len(self.list_IDs[1]))
        #'''
        # Find list of IDs
        list_IDs_temp = [self.list_IDs[0][k] for k in indexes]
        '''
        list_IDs_temp = [] #list_IDs have two of same dim use one to give list_IDs_temp
        for k in range(len(self.list_IDs[0])):
            list_IDs_temp.append(k)
        '''
        # Generate data
        X, y = self.__data_generation(list_IDs_temp)
        #print("X type and shape", type(X), " ", X.shape)
        #print("y type and shape", type(y), " ", y.shape)

        return X, y

    def on_epoch_end(self):
        'Updates indexes after each epoch'
        self.indexes = np.arange(len(self.list_IDs[0])) #choose one to use
        self.dbx1.initialize()
        self.dby1.initialize()
        self.dby2.initialize()

        if self.shuffle == True:
            np.random.shuffle(self.indexes)

    def __data_generation(self, list_IDs_temp):
        'Generates data containing batch_size samples' # X : (n_samples, *dim, n_channels)
        # Initialization
        X1 = np.empty((self.batch_size, self.max_text_len), dtype=int)#encoder input
        y1 = np.empty((self.batch_size, self.total_error_types), dtype=int)
        #y2 = [np.empty((self.batch_size, self.max_lines), dtype=int)] * self.max_lbs  # dim(84, batch_size, max_max_lines)
        '''
        print("y2 length: ", len(y2))
        print("y2[0] type: ", type(y2[0]))
        print("y2[0] shape: ", y2[0].shape)
        '''
        '''
        print("list_IDs_temp type: ", type(list_IDs_temp))
        print("list_IDs_temp lenght: ", len(list_IDs_temp))
        print("list_IDs_temp[0] type: ", type(list_IDs_temp[0]))
        #print("list_IDs_temp[0] lenght: ", len(list_IDs_temp[0]))
        print("list_IDs_temp[0]: ", list_IDs_temp[0])
        print("list_IDs_temp[1] type: ", type(list_IDs_temp[1]))
        #print("list_IDs_temp[1] lenght: ", len(list_IDs_temp[1]))
        print("list_IDs_temp[1]: ", list_IDs_temp[1])
        #for i in range(len(list_IDs_temp[0])):
        #    print("list_ID_temp[0] vs list_ID_temp[1]: ", list_IDs_temp[0][i], " vs ", list_IDs_temp[1][i])
        #'''
        count = 0
        #print("list_IDs_temp length: ", len(list_IDs_temp))
        y2 = None
        for i, ID in enumerate(list_IDs_temp):
            count += 1
            #print("loop: ", count)
            #print("ID type: ", type(ID))
            X1[i] = self.dbx1.get_data(ID) # a list of token ids
            #print("X1[i]: ", X1[i])
            #print("X1[i] lenght: ", len(X1[i]))
            #X1[i,] = self.dbx1.get_data(ID) # a list of token ids
            # out1: a binary vector of total_error_types (36) elements,
            y1[i] = self.dby1.get_data(ID) #a 36-length vector
            #print("y1[i]: ", y1[i])
            #print("y1[i] length: ", len(y1[i]))
            # out2 : a ector of one-hot-encoded vectors
            y2_temp = self.dby2.get_output_data(ID)
            y2_temp =[np.expand_dims(e, axis=0) for e in y2_temp]
            '''
            print("y2_temp type: ", type(y2_temp))
            print("y2_temp length :", len(y2_temp))
            print("y2_temp[0] shape: ", y2_temp[0].shape)
            '''
            #y2[i] = list(self.dby2.get_data(ID)) #a vector 84 160-length vector
            #y2[i] = self.dby2.get_output_data(ID)
            #y2[i] = list(self.dby2.get_output_data(ID))
            if y2 is None:
                y2 = y2_temp
            else:
                y2 = [(lambda a, b: np.concatenate((a, b)))(a1,a2) for a1, a2 in zip(y2, y2_temp)]
            #print("y2[i]: ", y2[i])
            '''
            print("y2 length: ", len(y2)) #show line block size
            print("y2[0] shape: ", y2[0].shape) #show sample
            '''
            #print("y2[i][1] length: ", len(y2[i][1])) #show single block size
            #print(len(y2))
            #print(type(y2[i]))
        return [X1], [y1] + y2
