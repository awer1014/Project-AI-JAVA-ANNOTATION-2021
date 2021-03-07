import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.math.*;
public class Pet
{
    String pid,name,type;
    //static Scanner keyboard = new Scanner(System.in);
    void speak(String name){
        if(name=="狗") System.out.println("汪汪~");
        else System.out.println("喵喵~");
    }
    void display(){
        System.out.println("寵物類別："+type);
        System.out.println("寵物編號："+pid);
        System.out.println("寵物名稱："+name);
    }
    public Pet(String p,String n,String t){
        name=n;
        pid=p;
        type=t;
    }
        
}
