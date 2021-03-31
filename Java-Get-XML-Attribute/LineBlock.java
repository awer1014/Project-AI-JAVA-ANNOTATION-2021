import java.util.*;
public class LineBlock implements Comparable
{
    int key;
    ArrayList<Integer> bl_begin = new ArrayList<>();
    ArrayList<Integer> bl_end = new ArrayList<>();
    ArrayList<String> filename = new ArrayList<>();
    public LineBlock(int k){key = k;}

    public void addBL(int b, int e, String name){
        bl_begin.add(b);
        bl_end.add(e);
        filename.add(name);
    }

    public int compareTo(Object o){
        LineBlock obj = (LineBlock)o;
        if(key < obj.key) return -1;
        else if(key > obj.key) return 1;
        else return 0;
    }

}
