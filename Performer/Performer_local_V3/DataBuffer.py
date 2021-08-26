import math
import numpy as np

class DataBuffer:
    def __init__(self, data_dir,  data_number, dtype=int, block_size = 1000, buffer_number=2, file_name="blk_" ):
        self.block_size = block_size
        self.block_number = math.ceil(data_number / block_size)
        self.block_status = [False]*self.block_number # true if blk i in buffer
        self.block_access_counts = [0]*self.block_number # access count of blk i
        self.buffer_number = buffer_number
        self.data_dir = data_dir
        self.dtype = dtype
        self.file_name = file_name
        #self.data_buffer = [ np.empty((*self.dim), , dtype=self.dtype) ] * self.block_size #the data buffer
        self.data_buffer_map = {} #key: blick id, value: data block in the buffer

    def initialize(self):
        self.block_access_counts = [0]*self.block_number # access count of blk i

    def __get_block_id(self, dataID):
        return dataID // self.block_size

    def __get_data_idx(self, dataID):
        #print("dataID % self.block_size: ", dataID % self.block_size)
        return dataID % self.block_size

    def __check_exist(self, dataID):
        blk_id = self.__get_block_id( dataID )
        return self.block_status[blk_id]

    def __read_block(self, block_id):
        data_block =  None # [ np.empty((*self.dim), , dtype=self.dtype) ] * self.block_size
        if self.data_dir.endswith("\\"):
            data_block= list(np.load(self.data_dir + self.file_name + str(block_id) + '.npy'))
        else:
            data_block= list(np.load(self.data_dir + "\\"+ self.file_name + str(block_id) + '.npy'))

        return data_block

    def __replace_data_buffer(self, block_id, data_block):
        if len(self.data_buffer_map)>0:
            #print("self.data_buffer_map.keys():", list(self.data_buffer_map.keys()))
            counts = { blk_id:self.block_access_counts[blk_id] for blk_id in
            self.data_buffer_map.keys()}
            #print("counts: ", counts)
            max_access_block_id = max(counts.items(), key = lambda item: item[1])[0]
            #print("max_access_block_id:", max_access_block_id)
            del self.data_buffer_map[max_access_block_id]
            self.block_status[max_access_block_id] = False
            #print("block ", max_access_block_id , " is replaced by blk ",block_id )
        self.data_buffer_map[block_id] = data_block
        self.block_status[block_id] = True


    def get_data(self, dataID):
        blk_id = self.__get_block_id(dataID)
        data_block = None
        if self.__check_exist(dataID): #exist in buffer
            data_block = self.data_buffer_map[blk_id]
        else: # not in the buffer, read block in the disk
            data_block = self.__read_block(blk_id)
            self.__replace_data_buffer(blk_id, data_block)

        self.block_access_counts[blk_id] += 1
        #print("data_block len:", len(data_block))
        #print("data_block: ", data_block[self.__get_data_idx(dataID)])
        #print("data_block[self.__get_data_idx(dataID)] type: ", type(data_block[self.__get_data_idx(dataID)]))
        return data_block[self.__get_data_idx(dataID)]

    def get_output_data(self, dataID):
        import numpy as np
        blk_id = self.__get_block_id(dataID)
        data_block = None
        if self.__check_exist(dataID): #exist in buffer
            data_block = self.data_buffer_map[blk_id]
        else: # not in the buffer, read block in the disk
            data_block = self.__read_block(blk_id)
            self.__replace_data_buffer(blk_id, data_block)

        self.block_access_counts[blk_id] += 1
        sample_id = self.__get_data_idx(dataID) #get sample index
        first_dim_length = len(data_block)
        second_dim_length = len(data_block[1])
        third_dim_length = len(data_block[1][self.__get_data_idx(dataID)])
        #print("data_block type: ", type(data_block))
        #print("data_block[0] type: ", type(data_block[0]))
        #print("data_block[0][0] type: ", type(data_block[0][0]))
        #print("data_block first dim len: ", len(data_block))
        #print("data_block second dim len: ", len(data_block[1]))
        #print("data_block third dim len: ", len(data_block[1][self.__get_data_idx(dataID)]))
        #print("data_block: ", data_block[1][self.__get_data_idx(dataID)])
        #to nd.array
        data_block = np.array(data_block)
        #get array
        re_data_block = data_block[:, sample_id, :]
        #transfrom to list
        re_data_block = list(re_data_block)
        #print("re_data_block type: ", type(re_data_block))
        #print("re_data_block shape: ", re_data_block.shape)
        #print("re_data_block: ", re_data_block)
        return re_data_block
