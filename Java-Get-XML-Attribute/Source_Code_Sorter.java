import java.util.*;
public class Source_Code_Sorter {
    //set ArrayList for SourceCode Attribute
    static ArrayList<String> Source_Code = new ArrayList<>();
    static ArrayList<Integer> Source_code_being_line = new ArrayList<>();
    //get Source Code Value and add to ArrayList
    static int line_sum = 0;
    public static void add_SourceCode_and_line(String Code_name, int code_line) {
        boolean ArrayListisEmpty = Source_Code.isEmpty();
        if (ArrayListisEmpty == true) {
              Source_Code.add(Code_name);
              Source_code_being_line.add(0);
            }
            else {
              Source_Code.add(Code_name);
              Source_code_being_line.add(line_sum);
            }
            line_sum += code_line;
    }

    public String get_SourceCode (int key) {
      return Source_Code.get(key);
    }

    public Integer get_Sourceline (int key) {
      return Source_code_being_line.get(key);
    }

}
