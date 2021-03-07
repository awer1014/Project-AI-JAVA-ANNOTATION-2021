public class Pet
{   
    int count;
    static String pid="d001";
    static String name="Timmy";
    static String type;
 Pet()
 {
     count++;
    }
 static void speak(String type)
 {
    if(type=="貓"){System.out.println("喵喵");}
    else if(type=="狗"){System.out.print("汪汪");}
 }
 static void display(String type)
 {
   System.out.println("寵物種類："+type);
   System.out.println("寵物編號："+pid);
   System.out.println("寵物名稱:"+name);
  }
 Pet(String pid,String name,String type)
 {
     this.pid=pid;
     this.name=name;
     this.type=type;
    }
    String getName()
 { 
    return name;
  }
 String getpid()
 {  return pid;
 }
 String gettype()
 {  return type;
    }
 public static void main(String[] args)
 {
    String a=args[0];
    display(a);
    speak(a);
 }
}
