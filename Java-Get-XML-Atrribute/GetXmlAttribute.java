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

    public void Get_Attribute_value(String file_name) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new FileInputStream(new File(file_name +".xml")));// same xml comments as above.
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        NodeList Errors = (NodeList) xpath.evaluate("ErrorList/Errors/Error", doc, XPathConstants.NODESET);
        //NodeList Lines = (NodeList) xpath.evaluate("ErrorList/Errors/Error/Linelist/line",doc, XPathConstants.NODESET);


        //NodeList Begins = (NodeList) xpath.evaluate("ErrorList/Errors/Error/Linelist/line",doc, XPathConstants.NODESET);
        //NodeList Ends = (NodeList) xpath.evaluate("ErrorList/Errors/Error/Linelist/line",doc, XPathConstants.NODESET);
        for (int i = 0; i < Errors.getLength(); i++) {
            Node Error_type_value = Errors.item(i);
            //Node Line_node = Lines.item(i);
            //print Error type
            System.out.println(Error_type_value.getAttributes().getNamedItem("tpye").getNodeValue());

            for (int k = 0; k < this.Error_type_value.getLength(); k++) {
              NodeList Lines = (NodeList) xpath.evaluate(this.Error_type_value + "/Error/Linelist/line",doc, XPathConstants.NODESET);
              Node Line = Lines.item(k);
              System.out.println(Line.getAttributes().getNamedItem("src").getNodeValue());
              System.out.println(Line.getAttributes().getNamedItem("Begin").getNodeValue());
              System.out.println(Line.getAttributes().getNamedItem("End").getNodeValue());
            }
        }
        //System.out.println(Error_type);
        //System.out.println(Error_type.getAttribute("name"));
    }

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException{
        //==============TestData==================
        String file_name = "230";
        //================Test====================
        GetXmlAttribute test = new GetXmlAttribute();
        test.Read_Xml_file(file_name);
        test.Get_Attribute_value(file_name);
    }
}
