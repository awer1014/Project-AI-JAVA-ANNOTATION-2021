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
    private void createSheet(){
        workbook = new HSSFWorkbook();
        //create file
        //add file name
        sheet = workbook.createSheet("ErrorLocation");
        // create first Row ----> id
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("id");
        for(int i = 1;i <= 36; i++){
            headRow.createCell(i).setCellValue("er"+i);
        }
        //create Row for error gegin and error end
        for(int i = 1;i <= 6; i++){
            for(int k = 1;k <= 5; k++){
                headRow.createCell(36+(i-1)*10+k).setCellValue("block_er"+i+"_b"+k);
                headRow.createCell(36+(i-1)*10+k+1).setCellValue("block_er"+i+"_e"+k);
            }
        }
        //headRow.createCell(1).setCellValue("分數");
        // 往Excel表中遍歷寫入數據
    }

    private HSSFRow createRow(){
        HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
        for(int i=0; i<97; i++){
            dataRow.createCell(i).setCellValue(0);
        }
        return dataRow;
    }

    public void write(String fileName, List<List<Line_Block>> list)  {
        createSheet();

        for (List<Line_Block> ls : list) {
            createCell(ls, sheet);

        }
        try {
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
    private void createCell(List<Line_Block>  list, HSSFSheet sheet) {
        HSSFRow dataRow=createRow();
        int ecount = 0;
        for (Line_Block lb : list) {
            ecount++;
            int errorid = lb.key;
            dataRow.getCell(errorid).setCellValue(1);
            int pos = 36+(ecount-1)*10;
            for(int i=0; i<lb.block_begin.size(); i++){
                int begin = lb.block_begin.get(i);
                int end = lb.block_end.get(i);
                int begin_c = pos+1+i*2;
                int end_c = pos+2+i*2;
                dataRow.getCell(begin_c).setCellValue(begin);
                dataRow.getCell(end_c).setCellValue(end);
            }

        }
        //dataRow.createCell(0).setCellValue(lb.getName());
        //dataRow.createCell(1).setCellValue(lb.getScore());
    }
}
