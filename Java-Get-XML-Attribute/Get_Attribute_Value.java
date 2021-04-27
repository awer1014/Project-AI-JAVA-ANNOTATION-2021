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
//import org.w3c.dom.NodeList;
import javax.xml.xpath.*;
import org.xml.sax.SAXException;
import java.util.*;

public class Get_Attribute_Value {
    static int Max_Error_num = 0; //check max Error number
    public static void get_Error_Value(String file_name, Document doc) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        //ArrayList-->Mapper
        ArrayList<Line_Block> line_List = new ArrayList<Line_Block>();
        Mapper map = new Mapper();
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        NodeList Error_List = (NodeList) xpath.evaluate("/ErrorList/Errors/Error", doc, XPathConstants.NODESET);
        for (int i = 0; i < Error_List.getLength(); i++){
            Element Error = (Element)Error_List.item(i);
            NodeList Line_List = (NodeList) xpath.evaluate("/ErrorList/Errors/Error/Linelist/line", Error, XPathConstants.NODESET);
            String type = Error.getAttribute("tpye");

            int key = map.get_Index(type);
            Line_Block lb = new Line_Block(key);
            for(int k = 0; k < Line_List.getLength(); k++){
                Element Line = (Element)Line_List.item(k);
                //print Error Source code
                String src = Line.getAttribute("src");
                //wtt.write_Error_src(src);
                //print Error Begin
                int Begin = Integer.parseInt(Line.getAttribute("Begin"));
                //wtt.write_Error_Begin(Begin);
                //print Error End
                int End = Integer.parseInt(Line.getAttribute("End"));
                //wtt.write_Error_End(End);
                lb.add_Block(Begin, End, src);
                Max_Error_num += 1;
            }
            line_List.add(lb);
        }
        //line_List.add(lb);
        Collections.sort(line_List);

        //===============================================================================================
        //test Attribute output

        System.out.println("===========NEW TEST===========");
        for(int k =0; k<line_List.get(0).get_Array_length(); k++){
            System.out.print(line_List.get(0).get_file_name(k));
            System.out.print(line_List.get(0).get_begin(k));
            System.out.println(line_List.get(0).get_end(k));
        }

        //================================================================================================
    }

    public static void get_Source_Value(String file_name, Document doc) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        //get source code detail
        NodeList Source_Code_List = (NodeList) xpath.evaluate("/ErrorList/SourceCode_List/SourceCode", doc, XPathConstants.NODESET);
        //set ArrayList for SourceCode Attribute
        //add Source Code file name
        //ArrayList<String> Source_Code = new ArrayList<>();
        //get Source Code Value and add to ArrayList
        //int line_sum = 0;
        //add Source code file's lines
        //ArrayList<Integer> Source_code_file_line = new ArrayList<>();
        //use Source_Code_Sorter to manage source code
        Source_Code_Sorter scs = new Source_Code_Sorter();
        for(int i = 0; i < Source_Code_List.getLength(); i++){
            //check ArrayList is empty or not
            //boolean ArrayListisEmpty = Source_Code.isEmpty();
            //get Source Code
            Element Source = (Element)Source_Code_List.item(i);
            String Code_name = Source.getAttribute("name");
            int code_line = Integer.parseInt(Source.getAttribute("lines"));
            scs.add_SourceCode_and_line(Code_name, code_line);
        }

        //================================================================================================
        //TEST
        for(int i = 0; i < Source_Code_List.getLength(); i++) {
          System.out.println("Source Code :" + scs.get_SourceCode(i));
          System.out.println("Source Code Begin Line :" + scs.get_SourceCode_Begin_line(i));
        }
        //================================================================================================
    }

    
    public static int get_Max_Error_num() {
      return Max_Error_num;
    }
    //try to make excel

}
