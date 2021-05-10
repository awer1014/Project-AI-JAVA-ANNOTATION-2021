import java.util.*;
public class Line_Block implements Comparable {
    int key;
    int sum = 0;

    ArrayList<Integer> block_begin = new ArrayList<>();
    ArrayList<Integer> block_end = new ArrayList<>();
    ArrayList<String> file_name = new ArrayList<>();
    ArrayList<String> error_Type = new ArrayList<>();

    public Line_Block (int k) {
        key = k;
    }

    public void add_Block ( String name, String Error_type, int begin, int end) {
    //public void add_Block ( String name, int begin, int end) {
        file_name.add(name);
        error_Type.add(Error_type);
        block_begin.add(begin);
        block_end.add(end);
        sum ++;
    }

    public int compareTo (Object o) {
        Line_Block obj = (Line_Block)o;
        if(key < obj.key) {
            return -1;
        }
        else if(key > obj.key) {
            return 1;
        }
        else return 0;
    }

    public int get_begin (int Index) {
        return block_begin.get(Index);
    }
    
    public void set_begin (int Index,int temp) {
        block_begin.set(Index,temp);
    }

    public int get_end (int Index) {
        return block_end.get(Index);
    }
    
    public void set_end (int Index,int temp) {
        block_end.set(Index,temp);
    }

    public String get_file_name (int Index) {
        return file_name.get(Index);
    }

    public String get_Error_type (int Index) {
        return error_Type.get(Index);
    }

    public int get_Array_length() {
        return file_name.size();
    }

    public int get_error_type_length() {
      return error_Type.size();
    }
    public int get_file_name_length() {
      return file_name.size();
    }
}
