import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class ExcelWriter {
    HSSFWorkbook workbook;
    HSSFSheet sheet;
    int error_type =36;
    int max_error_type =6;
    int max_block=16;
    int total_cell =1+error_type+(max_error_type*max_block*2);
    private void createSheet(){
        workbook = new HSSFWorkbook();
        //create file
        //add file name
        sheet = workbook.createSheet("ErrorLocation");
        // create first Row ----> id
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("id");
        for(int i = 1;i <= error_type; i++){
            headRow.createCell(i).setCellValue("er"+i);
        }
        //create Row for error gegin and error end
        for(int i = 1;i <= max_error_type; i++){
            for(int k = 0;k < max_block; k++){
                headRow.createCell(36+(i-1)*32+k*2+1).setCellValue("block_er"+i+"_b"+(k+1));
                //System.out.println("i = "+i+" k = "+k+" ,block_er"+i+"_k"+(k+1)+" : "+(36+(i-1)*32+k*2+1));
                headRow.createCell(36+(i-1)*32+k*2+2).setCellValue("block_er"+i+"_e"+(k+1));
                //System.out.println("i = "+i+" k = "+k+" ,block_er"+i+"_e"+(k+1)+" : "+(36+(i-1)*32+k*2+2));
            }
        }
        //headRow.createCell(1).setCellValue("分數");
        // 往Excel表中遍歷寫入數據
    }

    private HSSFRow createRow(){
        HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
        //for(int i=0; i<97; i++){
        for(int i=0; i<total_cell; i++){
            dataRow.createCell(i).setCellValue(0);
        }
        return dataRow;
    }

    public void write(String fileName, ArrayList<ArrayList<Line_Block>> list,ArrayList<String> file_id_list)  {
        //public void write(String fileName, ArrayList<Line_Block> list)  {

        createSheet();
        Read_Xml_file rxf = new Read_Xml_file();
        //for (ArrayList<ArrayList<Line_Block>> ls: list) {
        for(int i=0,j=0; i<list.size()&&j<file_id_list.size(); i++,j++ ){//String fil : file_id_list&&ArrayList<Line_Block> ls ;ls<list.size(); ls++
            createCell(list.get(i), sheet,file_id_list.get(j));
        }

        try {
            //System.out.println("XXXXXX:" + fileName);
            File xlsFile = new File(fileName);
            workbook.write(xlsFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // 創建Excel的一行數據。
    private void createCell(/*String file_id,*/ List<Line_Block>  list, HSSFSheet sheet,String file_id_list) {
        HSSFRow dataRow=createRow();
        //int id_count = 0;
        int ecount=0;
        for (Line_Block lb : list) {
            //System.out.println("file name: " + files[id_count].getName());
            //System.out.println("id_count: " + id_count);
            //String [] tokens=files[id_count].getName().split(".xml",2);
            //System.out.println("token: " + tokens[0]);
            dataRow.getCell(0).setCellValue(file_id_list);//.setCellValue(file_id);

            //id_count++;
            int errorid = lb.key;
            dataRow.getCell(errorid).setCellValue(1);
            int pos = 36+(ecount)*32;
            //System.out.println(36+(ecount-1)*32);
            //System.out.println("最初pos : "+pos+" 最初ecount : "+ecount);
            for(int i=0; i<lb.block_begin.size(); i++){
                int begin = lb.block_begin.get(i);
                int end = lb.block_end.get(i);
                //System.out.println("pos: "+pos);
                int begin_c = pos+1+i*2;
                //System.out.println("begin_c: "+begin_c+", i="+i+" ecount : "+ecount);
                int end_c = pos+2+i*2;
                //System.out.println("end_c: "+end_c+", i="+i+" ecount : "+ecount);
                //System.out.println("--------------------------------------------------");
                dataRow.getCell(begin_c).setCellValue(begin);
                dataRow.getCell(end_c).setCellValue(end);

            }
            ecount++;
        }
        //dataRow.createCell(0).setCellValue(lb.getName());
        //dataRow.createCell(1).setCellValue(lb.getScore());
    }
}
