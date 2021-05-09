import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import org.w3c.dom.*;
import javax.xml.xpath.*;
import org.xml.sax.SAXException;
import java.util.*;

public class Get_Attribute_Value {
    static int Max_Error_num = 0; //check max Error number
    private static Document doc;
    static List<Line_Block> line_List;

    public static void load_Xml_file(String file_name) {
        try {
            //read Xml file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            //read target file
            doc = builder.parse(new File (file_name+".xml") );
            System.out.println("Load OK!");
        } catch(Exception e) {
            System.out.println("load_Xml_file went wrong here");
            e.printStackTrace();
        }
    }

    public static void get_Error_Value( Document doc) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        //ArrayList-->Mapper
        line_List = new ArrayList<Line_Block>();
        Mapper map = new Mapper();

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        NodeList Error_List = (NodeList) xpath.evaluate("/ErrorList/Errors/Error", doc, XPathConstants.NODESET);
        for (int i = 0; i < Error_List.getLength(); i++) {
            Element Error = (Element)Error_List.item(i);
            NodeList Line_List = (NodeList) xpath.evaluate("Linelist/line", Error, XPathConstants.NODESET);
            String type = Error.getAttribute("tpye");
            //=====================Test======================
            //System.out.println(type);
            //=====================Test======================
            int key = map.get_Index(type);
            Line_Block lb = new Line_Block(key);

            for(int k = 0; k < Line_List.getLength(); k++) {
                //String type = Error.getAttribute("tpye");
                Element Line = (Element)Line_List.item(k);
                //print Error Source code
                String src = Line.getAttribute("src");
                //print Error Begin-
                int Begin = Integer.parseInt(Line.getAttribute("Begin"));
                //print Error End
                int End = Integer.parseInt(Line.getAttribute("End"));
                //wtt.write_Error_End(End);
                lb.add_Block(src,type,Begin,End);
                //lb.add_Block(src,Begin, End);
                Max_Error_num += 1;
            }
            line_List.add(lb);
        }
        //line_List.add(lb);
        Collections.sort(line_List); //need to sort for safe

        //===============================================================================================
        //test Attribute output
        /*
        System.out.println("===========NEW TEST===========");

        for(int k = 0; k < line_List.size(); k++) {
            for(int i = 0; i < line_List.get(k).get_error_type_length(); i++) {
                System.out.print(line_List.get(k).get_Error_type(i) +" "+ line_List.get(k).key);
                System.out.print(line_List.get(k).get_file_name(i));
                System.out.print(line_List.get(k).get_begin(i));
                System.out.println(line_List.get(k).get_end(i));
            }
        }
        //================================================================================================
        /*/
    }



    public static void get_Source_Value( Document doc) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        NodeList Source_Code_List = (NodeList) xpath.evaluate("/ErrorList/SourceCode_List/SourceCode", doc, XPathConstants.NODESET);
        Source_Code_Sorter scs = new Source_Code_Sorter();
        for(int i = 0; i < Source_Code_List.getLength(); i++) {
            //get Source Code
            Element Source = (Element)Source_Code_List.item(i);
            String Code_name = Source.getAttribute("name");
            int code_line = Integer.parseInt(Source.getAttribute("lines"));
            scs.add_SourceCode_and_line(Code_name, code_line);
        }

        //================================================================================================
        //TEST
        /*
        for(int i = 0; i < Source_Code_List.getLength(); i++) {
            System.out.println("Source Code :" + scs.get_SourceCode(i));
            System.out.println("Source Code Begin Line :" + scs.get_SourceCode_Begin_line(i));
        }
        //*/
        //================================================================================================
    }

    //*
    public static String get_list_Error_type(int list_Index, int Index) {
      return line_List.get(list_Index).get_Error_type(Index);
    }
    //*/

    public static String get_list_file_name(int list_Index, int Index) {
      return line_List.get(list_Index).get_file_name(Index);
    }

    public static int get_list_Error_begin(int list_Index, int Index) {
      return line_List.get(list_Index).get_begin(Index);
    }

    public static int get_list_Error_end(int list_Index, int Index) {
      return line_List.get(list_Index).get_end(Index);
    }

    public static int get_list_size() {
      return line_List.size();
    }

    public static int get_list_element_size(int list_Index) {
      return line_List.get(list_Index).get_error_type_length();
    }

    public static int get_Max_Error_num() {
        return Max_Error_num;
    }
    public static List<Line_Block> get_List_Line_Block_() {
        return line_List;
    }
}
