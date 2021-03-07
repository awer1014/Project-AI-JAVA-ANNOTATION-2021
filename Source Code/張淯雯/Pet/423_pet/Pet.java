import java.util.Scanner;
public class Pet
{   static int count; 
    String pid;
    String name;
    String type;
    public void speak(String t){
        if(t=="貓")System.out.println("喵喵!");
        else if(t=="狗")System.out.println("汪汪!");
    }
    public void display(String p,String n,String t){
        System.out.println("寵物編號: "+p);
        System.out.println("寵物名稱: "+n);
        System.out.println("寵物種類: "+t);
    }
    public Pet(String p,String n,String t){
        this.type=t;
        this.pid=p;
        this.name=n;
        
    }
    public static void main(String[] args){
        
        System.out.println("請輸入寵物種類: ");
        Scanner q1=new Scanner(System.in);
        String q=q1.nextLine();
        System.out.println("請輸入寵物編號: ");
        Scanner w1=new Scanner(System.in);
        String w=w1.nextLine();
        System.out.println("請輸入寵物名稱: ");
        Scanner e1=new Scanner(System.in);
        String e=e1.nextLine();
        System.out.println("請輸入飼主身分證字號: ");
        Scanner r1=new Scanner(System.in);
        String r=r1.nextLine();
        System.out.println("請輸入飼主電話: ");
        Scanner t1=new Scanner(System.in);
        String t=t1.nextLine();
        
        //以上輸入
        System.out.println("您的輸入是: ");
        //以下設定+介紹
        Pet p1=new Pet(w,e,q);
        Keeper k1=new Keeper(r,t);
        p1.display(w,e,q);
        k1.display(r,t);
        
    }    
}
