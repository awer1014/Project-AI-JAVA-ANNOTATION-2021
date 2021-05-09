import java.io.*;
import java.util.ArrayList;

public class XmlFileSearch 
{
    private static File f;
    private static File[] files;

    public static File[] getXmlFileList (String directory) {
        f = new File (directory);
        files = f.listFiles(new FileFilter());
        return files;
        }
}
