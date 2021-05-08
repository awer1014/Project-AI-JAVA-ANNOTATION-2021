import java.io.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
public class WriteToTxt {
    public static void writetxt(NodeList SourceCode_List, String path, String filename) {
        try {
            Mapper map = new Mapper();
            String file = filename + ".txt";
            File f = new File(path,file);
            f.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(f));
            for (int i = 0; i < SourceCode_List.getLength() ; i++ ) {
                Node SourceCode_item = SourceCode_List.item(i);
                NamedNodeMap node_att = SourceCode_item.getAttributes();
                out.write(" <BOTM> ");
                for(int j=0; j<node_att.getLength(); j++) {
                    Node node = node_att.item(j);
                    out.write(" <BOT> ");
                    out.write((map.get_Index(node.getNodeValue())).toString());
                    out.write(" <EOT> ");
            }
                NodeList SourceCode_child=SourceCode_item.getChildNodes();
            out.write(" <BOM> ");
                for(int k=0;k<SourceCode_child.getLength();k++) {
                Node SourceCode_child_ele=SourceCode_child.item(k);
                if(SourceCode_child_ele.getNodeType() == Node.ELEMENT_NODE) {                                        
                    out.write(SourceCode_child_ele.getTextContent());
                }
            }
            out.write(" <EOM> ");
            out.write(" <EOTM> ");
                //out.write(SourceCode.getTextContent());
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
