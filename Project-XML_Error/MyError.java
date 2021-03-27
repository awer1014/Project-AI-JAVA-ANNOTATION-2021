import java.util.*;
public class MyError implements Comparable
{
    Map<Integer, String> map =  new HashMap<>();
    MyError( String type,String filename,String begin,String end,String message){
        map.put(0,type);
        map.put(1,filename);
        map.put(2,begin);
        map.put(3,end);
        map.put(4,message ) ;
    }

    public int compareTo(Object a){
        return this.get(0).compareTo(((MyError)a).get(0));
    }

    public void delete(int idx){
        map.remove(idx);
    }        

    public String set(int idx,String value){
        System.out.println("I set: "+ idx + " value: " + value);
        return map.put(idx,value);
    }

    public String get(int idx){
        return map.get(idx);
    }

    public String getType(){
        return map.get(0);
    }

    public String getFilename(){
        return map.get(1);
    }

    public String getBegin(){
        return map.get(2);
    }

    public String getEnd(){
        return map.get(3);
    }

    public String getMessage(){
        return map.get(4);
    }

    public void display (){
        System.out.println("type: "+map.get(0));
        System.out.println("file: "+map.get(1));
        System.out.println("begin: "+map.get(2));
        System.out.println("end: "+map.get(3));
        System.out.println("message: "+map.get(4));
    }
}
