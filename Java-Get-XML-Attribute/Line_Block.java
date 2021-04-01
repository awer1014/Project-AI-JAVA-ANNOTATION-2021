import java.util.*;
public class Line_Block implements Comparable {
    int key;
    ArrayList<Integer> block_begin = new ArrayList<>();
    ArrayList<Integer> block_end = new ArrayList<>();
    ArrayList<String> file_name = new ArrayList<>();
    public Line_Block (int k) {
        key = k;
    }

    public void add_Block(int b, int e, String name) {
        block_begin.add(b);
        block_end.add(e);
        file_name.add(name);
    }

    public int compareTo(Object o) {
        Line_Block obj = (Line_Block)o;
        if(key < obj.key) {
            return -1;
        }
        else if(key > obj.key) {
            return 1;
        }
        else return 0;
    }

}
