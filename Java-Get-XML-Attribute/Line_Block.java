import java.util.*;
public class Line_Block implements Comparable {
    int key;
    int sum = 0;
    ArrayList<Integer> block_begin = new ArrayList<>();
    ArrayList<Integer> block_end = new ArrayList<>();
    ArrayList<String> file_name = new ArrayList<>();

    public Line_Block (int k) {
        key = k;
    }

    public void add_Block (int b, int e, String name) {
        block_begin.add(b);
        block_end.add(e);
        file_name.add(name);
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

    public int get_begin (int p) {
      return block_begin.get(p);
    }

    public int get_end (int p) {
      return block_end.get(p);
    }

    /*public String get_file_name (String p) {
      return file_name.get(p);
    }*/

    public int get_Array_length(){
      return sum;
    }
}
