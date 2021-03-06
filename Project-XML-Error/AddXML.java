import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import org.w3c.dom.CDATASection;
import java.util.Scanner;

public class AddXML {
    Document doc;
    Element root;
    /////////////////////////////////
    Element Src_List_node;//SourceCode_List
    Element src_node;//SorceCode_file
    CDATASection src_content;//SourceCode_content

    /////////////////////////////////
    Element Ers_node;//Errors node
    Element Er_node;//Error node
    Element Er_file_node;//Error file node
    Element Er_line_list_node;//Error Linelist
    Element Er_line_node;//Error Linelist->line
    CDATASection Er_line_content;
    /////////////////////////////////
    Element Msg_node;//Message
    CDATASection Msg_contant;//Msg_contant

    ///////////////////////////
    String pre_state = null; //for Check previous state
    public void addXML_List(String errorList_name) {

        try {
            //Load XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(new File ("ErrorList.xml") ); //Load Choose file

            System.out.println("Load OK!");

            root = doc.getDocumentElement() ; //Get root Element

            root.setAttribute("id",errorList_name);//Set Attribute
            //Create element
            Src_List_node = doc.createElement("SourceCode_List");
            Ers_node = doc.createElement("Errors");
            //appendChild
            root.appendChild(Src_List_node);
            root.appendChild(Ers_node);
            //Check Test
            System.out.println("Created !");

        }
        catch(Exception e) {
            System.out.println("I went wrong here");
            e.printStackTrace();
        }
    }

    //add new node
    public void new_SrcNode(String src_name, String src_lines,String src_code){
        try {

            src_node = doc.createElement("SourceCode");//createElement
            src_node.setAttribute("lines",src_lines);//set SourceCode lines
            src_node.setAttribute("name",src_name);//setAttribute
            src_code = src_code.replaceAll("\\r","");
            src_content = doc.createCDATASection(src_code);//createCDATA node
            src_node.appendChild(src_content); //Create code node

            Src_List_node.appendChild(src_node);//appendChild to Src_List

        } catch(Exception e) {
            System.out.println("I went wrong here");
            e.printStackTrace();
        }
    }

    public void new_ErsNode(String er_type,String er_filename,String begin,String end, String msg_message){
        if (!er_type.equals(pre_state)){
          pre_state = er_type;
            try {
                Msg_node = doc.createElement("Message");
                Msg_contant = doc.createCDATASection(msg_message);
                Er_node = doc.createElement("Error");
                Er_node.setAttribute("tpye",er_type);
                Er_line_list_node = doc.createElement("Linelist");
                Er_line_node = doc.createElement("line");
                Er_line_node.setAttribute("End",end);
                Er_line_node.setAttribute("Begin",begin);
                Er_line_node.setAttribute("src",er_filename);
                //appendChild
                Ers_node.appendChild(Er_node);
                Er_node.appendChild(Msg_node);
                Msg_node.appendChild(Msg_contant);
                Er_node.appendChild(Er_line_list_node);
                Er_line_list_node.appendChild(Er_line_node);
            } catch(Exception e) {
                System.out.println("I went wrong here");
                e.printStackTrace();
            }  
        }
        else{
            try {
                //Create new line node
                Er_line_node = doc.createElement("line");
                Er_line_node.setAttribute("End",end);
                Er_line_node.setAttribute("Begin",begin);
                Er_line_node.setAttribute("src",er_filename);
                //appendChild
                Er_line_list_node.appendChild(Er_line_node);
            } catch(Exception e) {
                System.out.println("I went wrong here");
                e.printStackTrace();
            }
        }
    }

    //Save to new file
    public void save(String errorList_name){
        try {
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            Source src = new DOMSource(doc);
            Result dest = new StreamResult(new FileOutputStream(errorList_name));
            aTransformer.transform(src, dest);

            System.out.println("Save Successed!");
        }
        catch(Exception e) {
            System.out.println("I went wrong here");
            e.printStackTrace();
        }
    }

    /*public void send_Erdata(){
    AddXML send_ernode = new AddXML();
    for (int i = 0; i < Arrays.length();i++){
    new_ErsNode(Arrays[1],Arrays[2],Arrays[3],Arrays[4],Arrays[5]);// give
    }
    }

     */
    public static void main(String[] args) {

        //==============NodeTEST==================
        String errorList_name = "Test1";

        String src_name = "AAA";
        String src_lines = "1000";
        String er_type = "BBB";
        String er_filename ="CCC";
        String begin = "4";
        String end = "8";
        //==============NodeTEST==================

        //==============TextTEST==================
        String src_code = "a = b + c";

        String msg_message = "you need to do this....";
        //==============TextTEST==================

        //================TEST====================

        AddXML test = new AddXML();
        test.addXML_List(errorList_name);
        test.new_SrcNode(src_name, src_lines, src_code);
        test.new_ErsNode(er_type, er_filename, begin, end, msg_message);
        test.save(errorList_name);
    }

}
