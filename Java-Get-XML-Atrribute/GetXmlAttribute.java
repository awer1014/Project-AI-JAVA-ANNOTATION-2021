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

public class GetXmlAttribute {
    Document doc;
    /////////////////////////////////
    //String file_name;
    /////////////////////////////////
    public void Read_Xml_file(String file_name) {
        try {
            //read Xml file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            //read target file
            doc = builder.parse(new File (file_name+".xml") );
            System.out.println("Load OK!");

        } catch(Exception e) {
            System.out.println("I went wrong here");
            e.printStackTrace();
        }
    }

    public void Get_Attribute_value(/*String file_name*/) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

        //DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //DocumentBuilder builder = factory.newDocumentBuilder();
        //doc = builder.parse(new FileInputStream(new File(file_name +".xml")));// same xml comments as above.
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        NodeList Error_List = (NodeList) xpath.evaluate("/ErrorList/Errors/Error", doc, XPathConstants.NODESET);
        NodeList Line_List = (NodeList) xpath.evaluate("/ErrorList/Errors/Error/Linelist/line", doc, XPathConstants.NODESET);
        for (int i=0;i<Error_List.getLength();i++){
            Element Error = (Element)Error_List.item(i);
            //Element Line = (Element)Line_List.item(i);
            String type = Error.getAttribute("tpye");//print Error type
            //String Begin = Line.getAttribute("Begin");
            //String End = Line.getAttribute("End");
            //String Src = Line.getAttribute("src");
            System.out.println("Error type: "+type);
            //System.out.println("Source code: "+Src);
            //System.out.println("Begin: "+Begin);
            //System.out.println("End: "+End);

            for(int k=0;k<Error_List.getLength();k++) {
              Element Line = (Element)Line_List.item(i);
              String Begin = Line.getAttribute("Begin");
              String End = Line.getAttribute("End");
              String Src = Line.getAttribute("src");
              System.out.println("Source code: "+Src);
              System.out.println("Begin: "+Begin);
              System.out.println("End: "+End);
            }

        }
    }

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException{
        //==============TestData==================
        String file_name = "230";

        //================Test====================
        GetXmlAttribute test = new GetXmlAttribute();
        test.Read_Xml_file(file_name);
        test.Get_Attribute_value();
        /*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new FileInputStream(new File(file_name +".xml")));// same xml comments as above.
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        Element Error_type = (Element) xpath.evaluate("/ErrorList/Errors/Error", doc, XPathConstants.NODE);
        System.out.println(Error_type.getAttribute("tpye"));*/
    }
}
