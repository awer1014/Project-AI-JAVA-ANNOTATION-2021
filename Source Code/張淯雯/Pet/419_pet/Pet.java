import java.util.Scanner;
public class Pet
{
    String pid;  //編號
    String name;
    String type; //貓或狗
    static int count=0;
    Keeper kp;
    public Pet(String p, String n, String t, Keeper kp)
    {
        pid =p;
        name=n;
        type = t;
        this.kp = kp;
    }
    
    void speak(){
        if(type=="dog") System.out.println("汪汪");
        else if(type=="cat")System.out.println("喵喵");
    }
    void display(){
        System.out.println("寵物種類: "+type);
        System.out.println("寵物編號："+pid);
        System.out.println("寵物名稱："+name);
        kp.display();
        speak();
    }
}
