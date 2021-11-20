import java.util.*;
public class Source_Code_Sorter {
    //set ArrayList for SourceCode Attribute
    ArrayList<String> Source_Code = new ArrayList<>();
    ArrayList<Integer> Source_code_being_line = new ArrayList<>();
    //get Source Code Value and add to ArrayList
     int line_sum = 0;
    
    Source_Code_Sorter(){
        //this.Source_Code.clear();// = Source_Code.clear();
        //this.Source_code_being_line.clear();
    }

    public void add_SourceCode_and_line(String Code_name, int code_line, boolean array_last) {
        boolean ArrayListisEmpty = Source_Code.isEmpty();
        if ((ArrayListisEmpty == true) && (array_last == false)) {
            Source_Code.add(Code_name);
            //System.out.println("scs.Code_name:"+Code_name);
            Source_code_being_line.add(0);
            //System.out.println("scs.line:"+code_line);
            line_sum += code_line;
        }
        else if (array_last == false) {
            Source_Code.add(Code_name);
            //System.out.println("scs.Code_name:"+Code_name);
            Source_code_being_line.add(line_sum);
            //System.out.println("scs.line:"+code_line);
            line_sum += code_line;
        }
        else if (array_last == true) {
            Source_Code.add(Code_name);
            //System.out.println("scs.Code_name:"+Code_name);
            Source_code_being_line.add(line_sum);
            //System.out.println("scs.line:"+code_line);
            line_sum = 0;
        }

    }

    public String get_SourceCode (int Index) {
        return Source_Code.get(Index);
    }

    public int get_SourceCode_Begin_line (int Index) {
        return Source_code_being_line.get(Index);
    }

    public int get_Array_length() {
        return Source_Code.size();
    }

    public int find_IndexOf_Source_Code (String Index){
        return Source_Code.indexOf(Index);
    }
}
