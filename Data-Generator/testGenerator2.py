import numpy as np
import DataGenerator2_2 as DG2
import math
import os
import DataBuffer as db
from random import randrange


def saveTestTrainData(filename, data): # e.g., 'test.npy'
    with open(filename, 'wb') as f:
        np.save(f, data)
		
def loadTestTrainData(filename): # e.g., 'test.npy'
    with open(filename, 'rb') as f:
        a = np.load(f)
    return a		

def generateInputBLKData(offset = 0, datafilename, destDir1,filename1="blk_", block_size=1000, fext=".npy"):
    if not os.path.exists(destDir1):
        os.mkdir(destDir1)  #create patchbackfiledir case dir
        
    data = list( loadTestTrainData(datafilename) )
    print("type(train):", type(data))
    print("train[0].shape:", data[0].shape) #encoder inputs
    lines = data[0].shape[0] # 279 lines
    
    block_numer = math.ceil( lines / block_size )
    
    for blk_id in range(block_numer):
        print("blk_idx: ", blk_id)
        blk_offset = blk_id*block_size
        blk_end = min((blk_id+1)*block_size, lines)
        saveTestTrainData(destDir1 + filename1 +str(offset+blk_id) + ".npy", data[0][blk_offset:blk_end])


def generateOutputBLKData(datafilename, destDir1, filename="blk_", block_size=1000, fext=".npy"):
    if not os.path.exists(destDir1):
        os.mkdir(destDir1)  #create patchbackfiledir case dir
    
    data = list( loadTestTrainData(datafilename) )
    print("len(data):", len(data))
    print("type(data):", type(data))
    print("data.shape:", data[0].shape) #encoder inputs
    lines = len(data) # 279 lines
    print("lines:", lines) #encoder inputs
   
    block_numer = math.ceil( lines / block_size )
    
    for blk_id in range(block_numer):
        print("blk_idx: ", blk_id)
        blk_offset = blk_id*block_size
        blk_end = min((blk_id+1)*block_size, lines)
        saveTestTrainData(destDir1 + "blk_"+str(blk_id) + ".npy", data[blk_offset:blk_end])
        

if __name__ == "__main__":
#### Split data to blocks  ##########
    """
    destDir1 = "model\\"
	for i in range(6):
        generateInputBLKData(i, "x_train_"+ str(i)+ ".npy", destDir1, destDir2, block_size = 48048, filename1="x_train[0]_", filename2="x_train[1]_")
    
    destDir3 = "Data\\DecoderOutput1\\"
    destDir4 = "Data\\DecoderOutput2\\"
    generateOutputBLKData("y_train[0].npy", destDir3, block_size = 50)
    generateOutputBLKData("y_train[1].npy", destDir4, block_size = 50)
    """
######################################################
    #for output
    
   input_buffer_params = { 
        "data_path": "Data\\DecoderOutput1\\", 
        "data_number":270670,
        "data_type": int,
        "block_size": 48048
        }
    
    
    # for input
    output_buffer_params = {
        "data_path": ["Data\\Encoder\\", "Data\\Decoder\\"],
        "data_number":[270670, 270670],
        "data_type": [int, int],
        "block_size": [48048, 48048]
        }
    
    input_data_path = input_buffer_params["data_path"]
    input_data_number = input_buffer_params["data_number"]
    input_data_type = input_buffer_params["data_type"]
    input_block_size = input_buffer_params["block_size"]
    
    output_data_path = output_buffer_params["data_path"]
    output_data_number = output_buffer_params["data_number"]
    output_data_type = output_buffer_params["data_type"]
    output_block_size = output_buffer_params["block_size"]
    
    input_db1 = db.DataBuffer(input_data_path, input_data_number, input_data_type, input_block_size,file_name="x_train[0]_" )
   
    
    output_db1 = db.DataBuffer(output_data_path[0], output_data_number[0],output_data_type[0], output_block_size[0], file_name="y_train[0]_" )
	
    output_db2 = db.DataBuffer(output_data_path[1], output_data_number[1],output_data_type[1], output_block_size[1], file_name="y_train[1]_")

    for i in range(10):
        data_id  = randrange(338338)
        data1 = input_db1.get_data(data_id)
        data3 = output_db1.get_data(data_id)
        data4 = output_db2.get_data(data_id)
        print("data1 :", (data1))
        print("data2 :", (data2))
        print("data3 :", (data3))
        print("data4 :", (data4))

        """
        data = db1.get_data(200)
        print("data type:", type(data), "data.shape: ", data.shape)
        #print("data :", (data))
        
        data = db1.get_data(10)
        print("data type:", type(data), "data.shape: ", data.shape)
        #print("data :", (data))

        data = db1.get_data(110)
        print("data type:", type(data), "data.shape: ", data.shape)
        #print("data :", (data))
        
        data = db1.get_data(113)
        print("data type:", type(data), "data.shape: ", data.shape)
        """
    #print("data :", (data))
    
    #x = DG2.DataGenerator1(buffer_params, [list(range(279)),list(range(279))] , None, None)
