import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import org.w3c.dom.CDATASection;
import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class ReadCodeFromXMLDoc extends JPanel implements ReadCodeStrategy
{
    private JFileChooser fc = new JFileChooser(WorkWindow.workingDirectory);

    private Vector<CodeData> codes = new Vector<>();
    private Vector<ErrorData> errors = new Vector<>();
    int idx = 0;
    private Document doc;
    private Element root;
    WorkWindow ww;
    
    public ReadCodeFromXMLDoc( WorkWindow ww) {        
       this.ww = ww;
    }
    public void openFileList() {
        fc.setMultiSelectionEnabled(false); //不允许选择多个文件
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
        fc.setFileFilter(filter);
        int state = fc.showOpenDialog(this);//顯示開啟檔案窗
        if (state == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile(); 
            WorkWindow.workingDirectory = f.getParent();
            
            doc = loadXMLDoc(f.getAbsolutePath());
           
            //get all file names and codes
           codes = getCodeDataList(doc);
               // codes.add(new CodeData(f.getName(), readFileAsString(f)));   
           ww.updateCurrentFileName(f.getAbsolutePath());
        }
                  
    }
    
    public CodeData nextCode()
    {
        if (idx < codes.size()) {
            return codes.get(idx++);
        } else
           return null;
    }
    private  Vector<CodeData> getCodeDataList(Document doc) {
       if (doc==null) return null;
       NodeList codeList = doc.getElementsByTagName("SourceCode") ; 
       Node n;
       for (int i=0; i<codeList.getLength(); i++) {
          n = codeList.item(i);
          String fname = ((Element)n).getAttribute("name");
          String code = ((CharacterData)n.getFirstChild()).getData();
          codes.add(new CodeData(fname, code));
        }
       
        return codes;
    }
    
    private Document loadXMLDoc(String fname) {
         try {
                    //Load XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new File (fname)) ; //Load Choose file

           
         }   catch(Exception e) {
            System.out.println("I went wrong here");
            e.printStackTrace();
            return null;
        } 
    }
    public Vector<ErrorData> readErrors() {
       if (doc==null) return null;
       NodeList errorList = doc.getElementsByTagName("Error") ; 
       Node n;
       for (int i=0; i<errorList.getLength(); i++) {
          n = errorList.item(i); //n is the Error element
          String[] errorTypes = ((Element)n).getAttribute("tpye").split(":",2);
          String mainType = errorTypes[0];
          String sybType = errorTypes[1];
          //get message
          NodeList mlist = ((Element)n).getElementsByTagName("Message");
          String message=null;
          if (mlist.getLength()>0) {
              message =  mlist.item(0).getFirstChild().getTextContent();
          }
          //get error line list
          NodeList elist = ((Element)n).getElementsByTagName("Linelist");
          if (elist.getLength()>0) {
            Element  lineList = (Element) elist.item(0) ;
            //get lines
            NodeList llist = lineList.getElementsByTagName("line");
            Node el;
            for (int ii=0; ii<llist.getLength(); ii++) {
                el = llist.item(ii);
                String fname = ((Element)el).getAttribute("src");
                String begin = ((Element)el).getAttribute("Begin");
                String end = ((Element)el).getAttribute("End");
                errors.add(new ErrorData(mainType, sybType, fname,  begin,  end,  message));
            }
          }

        }       
        return errors;
    }
    public Document getXMLDocument() { return doc;}
}
