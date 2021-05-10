import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Write_to_txt {
    private static FileWriter file;
    public static void create_New_file(String file_name){
        try {
            file = new FileWriter(file_name + ".txt");
        } catch (Exception e) {
            System.out.println("create_New_file went wrong here");
            e.printStackTrace();
        }
    }

    public static void write_Error_type(String type) {
      try {
        file.write("Error type: " + type);
      } catch(Exception e) {
        System.out.println("write_Error_type went wrong here");
        e.printStackTrace();
      }

    }

    public static void write_Error_src(String src) {
        try {
          file.write("Source code: " + src);
        } catch(Exception e) {
          System.out.println("write_Error_src went wrong here");
          e.printStackTrace();
        }
    }

    public static void write_Error_Begin(String Begin) {
      try {
        file.write("Begin: " + Begin);
      } catch(Exception e) {
        System.out.println("write_Error_Begin went wrong here");
        e.printStackTrace();
      }

    }

    public static void write_Error_End(String End) {
      try {
        file.write("End: " + End);
      } catch(Exception e) {
        System.out.println("write_Error_End went wrong here");
        e.printStackTrace();
      }
    }

    public static void save_file(){
        try {
            file.close();
            System.out.println("Successfully wrote to the file.");
        } catch(Exception e) {
            System.out.println("save_file went wrong here");
            e.printStackTrace();
        }

    }
}
