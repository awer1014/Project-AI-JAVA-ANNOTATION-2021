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
public class Read_Xml_file {
    private static Document doc;
    public static void load_Xml_file(String file_name) {
        try {
            //read Xml file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            //read target file
            doc = builder.parse(new File (file_name+".xml") );
            System.out.println("Load OK!");
            Get_Attribute_Value gav = new Get_Attribute_Value();
            gav.get_Value(file_name, doc);
        } catch(Exception e) {
            System.out.println("load_Xml_file went wrong here");
            e.printStackTrace();
        }

    }
}
