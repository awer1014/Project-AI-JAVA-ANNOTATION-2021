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
        String file_name1 = "323";
        Get_Attribute_Value gav1 = new Get_Attribute_Value();
        Read_Xml_file rxf1 = new Read_Xml_file();
        rxf1.load_Xml_file(file_name1, gav1);
        String file_name2 = "230";
        Get_Attribute_Value gav2 = new Get_Attribute_Value();
        Read_Xml_file rxf2 = new Read_Xml_file();
        rxf2.load_Xml_file(file_name2, gav2);
        
        ExcelWriter ew = new ExcelWriter();
        //=====================Test======================
        //*
        for(int i = 0; i < gav1.get_list_size(); i++) {
          for (int j =0; j<gav1.get_list_element_size(i); j++) {
              System.out.println("==============================");
              System.out.println(gav1.get_list_Error_type(i, j));
              System.out.println(gav1.get_list_file_name(i, j));
              System.out.println(gav1.get_list_Error_begin(i, j));
              System.out.println(gav1.get_list_Error_end(i, j));
              System.out.println("==============================");
            }
        }
        
        for(int i = 0; i < gav2.get_list_size(); i++) {
          for (int j =0; j<gav2.get_list_element_size(i); j++) {
              System.out.println("==============================");
              System.out.println(gav2.get_list_Error_type(i, j));
              System.out.println(gav2.get_list_file_name(i, j));
              System.out.println(gav2.get_list_Error_begin(i, j));
              System.out.println(gav2.get_list_Error_end(i, j));
              System.out.println("==============================");
            }
        }
        
        //*/
        //=====================Test======================
        //need to change to sort file
        //need to make file to excle
        //w.write(file_name,atv.line_List);
        //write(file_name,line_list)
    }
}
