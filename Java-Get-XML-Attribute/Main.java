import java.io.*;
import java.util.*;
import javax.xml.*;
//import javax.xml.parsers.*;
//import javax.xml.transform.*;
import org.w3c.dom.*;
//import javax.xml.xpath.*;
//import org.xml.sax.SAXException;
//import java.util.ArrayList;

public class Main {
    //static ArrayList<Line_Block> line_List;
    public static void main(String[] args) {
        String file_name = "323";
        ExcelWriter ew = new ExcelWriter();
        Get_Attribute_Value gav = new Get_Attribute_Value();
        Read_Xml_file rxf = new Read_Xml_file();
        rxf.load_Xml_file(file_name, gav);
        for(int i = 0; i < gav.get_list_size(); i++){
          for (int j =0; j<gav.get_list_element_size(i); j++) {
              System.out.println(gav.get_list_Error_type(i, j));
              System.out.println(gav.get_list_file_name(i, j));
              System.out.println(gav.get_list_Error_begin(i, j));
              System.out.println(gav.get_list_Error_end(i, j));
          }
        }

        //need to change to sort file
        //need to make file to excle
        //w.write(file_name,atv.line_List);
        //write(file_name,line_list)
    }
}
