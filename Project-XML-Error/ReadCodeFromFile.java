import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class ReadCodeFromFile extends JPanel implements ReadCodeStrategy
{
    JFileChooser fc = new JFileChooser(WorkWindow.workingDirectory);
    File[] files = null;
    Vector<CodeData> codes = new Vector<>();
    int idx = 0;
    public void openFileList() {
        fc.setMultiSelectionEnabled(true); //允许选择多个文件
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Codes", "java");
        fc.setFileFilter(filter);
        int state = fc.showOpenDialog(this);//顯示開啟檔案窗
        if (state == JFileChooser.APPROVE_OPTION) {
            files = fc.getSelectedFiles(); 
            for (File f : files) {
                WorkWindow.workingDirectory = f.getParent();
                codes.add(new CodeData(f.getName(), readFileAsString(f)));
            }
        }
    }
    
    public CodeData nextCode()
    {
        if (idx < codes.size()) {
            return codes.get(idx++);
        } else
           return null;
    }
    public  String readFileAsString(File f)  { 
      try {
          String data = ""; 
          data = new String(Files.readAllBytes(Paths.get(f.getAbsolutePath()))); 
          return data; 
        }   catch (Exception e) { e.printStackTrace(); return null;}
  } 
}
