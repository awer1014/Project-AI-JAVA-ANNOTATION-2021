import java.util.Scanner;

public class Keeper{
    String id,tel;
    void display(){
        System.out.println("飼主身分證 :"+id);
        System.out.println("飼主電話 :"+tel);
    }
    public Keeper(String idd,String tell){
        id = idd;
        tel = tell;;
    }
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        String type,pid,name,kid,ktel;
        System.out.println("請輸入寵物種類 : ");
        type = cin.nextLine();
        if(type.equals("0"))System.exit(0);
        System.out.println("請輸入寵物編號 : ");
        pid = cin.nextLine();
        System.out.println("請輸入寵物名稱 : ");
        name = cin.nextLine();
            
        Pet a = new Pet(type,pid,name);
            
        System.out.println("請輸入飼主身份證字號 : ");
        kid = cin.nextLine();
        System.out.println("請輸入飼主電話 : ");
        ktel = cin.nextLine();
        Keeper ak = new Keeper(kid,ktel);
        a.display();
        ak.display();
        a.speak();
    }
    
}