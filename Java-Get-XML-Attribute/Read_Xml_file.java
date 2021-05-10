import java.io.*;
import java.util.*;
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
public class Read_Xml_file {
    
    //static List<List<Line_Block>> list;
    static Get_Attribute_Value target;
    public static void load_Xml_file/*(String file_name,Get_Attribute_Value target)*/(File[] files, String path,ArrayList<ArrayList<Line_Block>> list) {
        try {
            //read Xml file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;
            //read target file
            /*doc = builder.parse(new File (file_name+".xml") );
            System.out.println("Load OK!");
            target.get_Source_Value(file_name, doc);
            target.get_Error_Value( file_name,doc);
            target.add_total_lines();*/
            for (int i = 0; i < files.length ; i++ ) {
                System.out.println("檔案 : "+files[i].getName());
                doc = builder.parse(files[i]);
                //doc.getDocumentElement().normalize();
                //System.out.println("Root element : "+ doc.getDocumentElement().getNodeName());
                //SourceCode_List = doc.getElementsByTagName("SourceCode");
                target.get_Source_Value( doc);
                target.get_Error_Value( doc);
                //target.add_total_lines();
                for(int ii = 0; i < target.get_list_size(); i++) {
                    for (int j =0; j<target.get_list_element_size(ii); j++) {
                        System.out.println(target.get_list_Error_type(ii, j));
                        System.out.println(target.get_list_file_name(ii, j));
                        System.out.println(target.get_list_Error_begin(ii, j));
                        System.out.println(target.get_list_Error_end(ii, j));
                    }
                }
                list.add(target.line_List);
                //String [] tokens = files[i].getName().split(".xml",2);
                //wtt.writetxt(SourceCode_List, path, tokens[0]);
            }

        } catch(Exception e) {
            System.out.println("load_Xml_file went wrong here");
            e.printStackTrace();
        }

    }
    /*public static List<List<Line_Block>> get_List_List_Line_Block__() {
    return list;
    }*/
}
