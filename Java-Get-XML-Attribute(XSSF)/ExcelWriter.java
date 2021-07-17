import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.OutputStream;

public class ExcelWriter {
    String path = "test.xlsx";
    //Workbook workbook = null;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    private int error_type =36;
    private int max_error_type =6;//最大錯誤類型總數
    private int max_block =14;//最大段落數為7,因為有"開始"與"結束",所以要乘2=14
    private int javaLength = 150;//java檔案最大行數
    private int total_cell =1+error_type+(max_error_type*max_block*javaLength);
    private void createSheet(){
        workbook = new XSSFWorkbook();
        int sum=0;
        //create file
        //add file name
        sheet = workbook.createSheet("ErrorLocation");
        // create first Row ----> id
        XSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("id");
        for(int i = 1;i <= error_type; i++){
            headRow.createCell(i).setCellValue("er"+i);
        }
        //create Row for error gegin and error end
        int pos=36;
        boolean next = false;
        for(int i = 1;i <= max_error_type; i++){
            int nextIndex = 1;
            for(int k = 1;k <= max_block; k++){
                
                for(int l = 1;l <= javaLength; l++){
                    pos += 1;
                    
                    if(k%2==1){
                        next =false;
                        headRow.createCell(pos).setCellValue("block_er"+i+"_b"+nextIndex+",line:"+l);
                        //System.out.println("pos: "+pos);
                        //System.out.println("block_er"+i+"_b"+nextIndex+",line:"+l+"----"+"i: "+i+" K: "+nextIndex+" l: "+l);
                        
                    }
                    else if(k%2==0){
                        next =true;
                        headRow.createCell(pos).setCellValue("block_er"+i+"_e"+nextIndex+",line:"+l);
                        //System.out.println("pos: "+pos);
                        //System.out.println("block_er"+i+"_e"+nextIndex+",line:"+l+"----"+"i: "+i+" K: "+nextIndex+" l: "+l);
                    }
                }
                if(next==true){ nextIndex+=1;}
            }
        }
        //headRow.createCell(1).setCellValue("分數");
        // 往Excel表中遍歷寫入數據
    }

    private XSSFRow createRow() {
        XSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
        //for(int i=0; i<97; i++){
        for(int i=0; i<total_cell; i++) {
            dataRow.createCell(i).setCellValue(0);
        }
        return dataRow;
    }

    public void write(String fileName, ArrayList<ArrayList<Line_Block>> list, ArrayList<String> file_id_list) {
        //public void write(String fileName, ArrayList<Line_Block> list)  {

        createSheet();
        Read_Xml_file rxf = new Read_Xml_file();
        //for (ArrayList<ArrayList<Line_Block>> ls: list) {
        for(int i=0,j=0; i<list.size()&&j<file_id_list.size(); i++,j++ ) {//String fil : file_id_list&&ArrayList<Line_Block> ls ;ls<list.size(); ls++
            createCell(list.get(i), sheet, file_id_list.get(j));
            //System.out.println("ew.list:" + list.get(i));
            //System.out.println("ew.file_id:" + file_id_list.get(j));
        }

        try(FileOutputStream os = new FileOutputStream(fileName)) {
            //System.out.println("XXXXXX:" + fileName);
            //File xlsxFile = new File(fileName);
            workbook.write(os);
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
    private void createCell(/*String file_id,*/ List<Line_Block>  list, XSSFSheet sheet, String file_id_list) {
        XSSFRow dataRow=createRow();
        //int id_count = 0;
        int ecount=0;
        int pos = error_type + ecount * javaLength;
        //System.out.println("ew file id: " + file_id_list);
        dataRow.getCell(0).setCellValue(file_id_list);
        for (Line_Block lb : list) {
            //System.out.println("file name: " + files[id_count].getName());
            //System.out.println("id_count: " + id_count);
            //String [] tokens=files[id_count].getName().split(".xml",2);
            //System.out.println("ew file id: " + file_id_list);
            //dataRow.getCell(0).setCellValue(file_id_list);//.setCellValue(file_id);

            //id_count++;
            int errorid = lb.key;
            dataRow.getCell(errorid).setCellValue(1);
            //System.out.println("下一個錯誤類型_pos: "+pos+ " ecount:"+ecount);
            //System.out.println(36+(ecount-1)*32);
            //System.out.println("最初pos : "+pos+" 最初ecount : "+ecount);
            for(int i=0; i < lb.block_begin.size(); i++){
                //System.out.println("block begin size: "+lb.block_begin.size());
                
                int begin = lb.block_begin.get(i);
                int end = lb.block_end.get(i);
                //System.out.println("pos: "+pos);
                int begin_c = pos+begin;
                dataRow.getCell(begin_c).setCellValue(1);
                //System.out.println("begin_c_pos: "+begin_c+" b"+i+": "+begin+ " ecount:"+ecount+" current_pos:"+pos);
                ecount++;
                pos +=javaLength;
                //System.out.println("begin_c: "+begin_c+", i="+i+" ecount : "+ecount);
                int end_c = pos+end;
                dataRow.getCell(end_c).setCellValue(1);
                //System.out.println("end_c_pos: "+end_c+" e"+i+": "+end+ " ecount:"+ecount+" current_pos:"+pos);
                ecount++;
                pos += javaLength;
                //System.out.println("end_c: "+end_c+", i="+i+" ecount : "+ecount);
                //System.out.println("--------------------------------------------------");
                
                

            }
            
        }
        //dataRow.createCell(0).setCellValue(lb.getName());
        //dataRow.createCell(1).setCellValue(lb.getScore());
    }
}
