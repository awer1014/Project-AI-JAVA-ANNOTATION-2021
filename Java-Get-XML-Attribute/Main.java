import java.io.*;
//import java.io.File;
import javax.xml.parsers.*;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
//import javax.xml.transform.dom.*;
//import javax.xml.transform.stream.*;
//import java.io.*;
import org.w3c.dom.*;
//import org.w3c.dom.CDATASection;
//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
import javax.xml.xpath.*;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) {
        String file_name = "323";
        Read_Xml_file rxf = new Read_Xml_file();
        rxf.load_Xml_file(file_name);
        Get_Attribute_Value atv = new Get_Attribute_Value();
        ExcelWriter ew = new ExcelWriter();
        //need to change to sort file 
        //need to make file to excle
        //w.write(file_name,atv.line_List);
        //write(file_name,line_list)
    }
}
