import java.io.*;
import java.util.*;
import javax.xml.*;
//import javax.xml.parsers.*;
//import javax.xml.transform.*;
import org.w3c.dom.*;
//import javax.xml.xpath.*;
//import org.xml.sax.SAXException;
//import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class Main {
    private  String Filepath = "";
    private  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    //private static LoadXml lx;
    private  XmlFileSearch xfs;
    private  File inputFile;
    private  File[] files;
    ExcelWriter ew = new ExcelWriter();
    ArrayList<ArrayList<Line_Block>> list=new ArrayList<ArrayList<Line_Block>>();
    //static ArrayList<Line_Block> line_List;
    public static void main(String[] args) {
        /* <----- dust switch
        String file_name = "323";
        ExcelWriter ew = new ExcelWriter();
        Get_Attribute_Value gav = new Get_Attribute_Value();
        Read_Xml_file rxf = new Read_Xml_file();
        rxf.load_Xml_file(file_name, gav);
        list.add(gav.line_List);

        ew.write(file_name,list);
        //*/// <----- dust switch
        //=====================Test======================
        Main mn = new Main();
        try {
            Read_Xml_file rxf = new Read_Xml_file();
            Get_Attribute_Value gav = new Get_Attribute_Value();
            mn.Filepath = mn.in.readLine();
            mn.files = mn.xfs.getXmlFileList(mn.Filepath);
            //lx.getNode(files,Filepath);
            rxf.load_Xml_file(mn.files, mn.Filepath, mn.list);
            mn.ew.write("test.csv", mn.list, rxf.file_id_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* <----- dust switch
        for(int i = 0; i < gav.get_list_size(); i++) {
        for (int j =0; j<gav.get_list_element_size(i); j++) {
        System.out.println(gav.get_list_Error_type(i, j));
        System.out.println(gav.get_list_file_name(i, j));
        System.out.println(gav.get_list_Error_begin(i, j));
        System.out.println(gav.get_list_Error_end(i, j));
        }
        }
        //*/// <----- dust switch
        //=====================Test======================
        //need to change to sort file
        //need to make file to excle
        /* <----- dust switch

        w.write(file_name,atv.line_List);
        write(file_name,line_list);
        //*/// <----- dust switch
    }
}
