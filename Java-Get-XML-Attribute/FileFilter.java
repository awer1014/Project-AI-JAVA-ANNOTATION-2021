import java.io.*;

public class FileFilter implements FilenameFilter
{
    public boolean containXML (String file) {
        return file.contains(".xml");
    }
    
    public boolean accept(File dir, String name) {
        return containXML(name);  
    }
}

