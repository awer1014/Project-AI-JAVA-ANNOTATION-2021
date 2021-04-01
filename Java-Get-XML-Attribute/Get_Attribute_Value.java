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
import java.util.*;
public class Get_Attribute_Value {
    public static void get_Value(String file_name, Document doc) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        
        ArrayList<Line_Block> line_List = new ArrayList<Line_Block>();
        Mapper map = new Mapper();
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        NodeList Error_List = (NodeList) xpath.evaluate("/ErrorList/Errors/Error", doc, XPathConstants.NODESET);
        for (int i=0;i<Error_List.getLength();i++){
            Element Error = (Element)Error_List.item(i);
            NodeList Line_List = (NodeList) xpath.evaluate("/ErrorList/Errors/Error/Linelist/line", Error, XPathConstants.NODESET);
            String type = Error.getAttribute("tpye");
            //wtt.write_Error_type(type);
            //System.out.println("Error type: "+type);
            //System.out.println("Source code: "+Src);
            //System.out.println("Begin: "+Begin);
            //System.out.println("End: "+End);
            int key = map.get_Index(type);
            Line_Block lb = new Line_Block(key);
            for(int k=0;k<Line_List.getLength();k++){
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
            }
            line_List.add(lb);
        }
        Collections.sort(line_List);
        //adjust lines position ...
    }

}

