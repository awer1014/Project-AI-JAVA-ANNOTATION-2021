import java.util.Scanner;
public class Keeper
{
    String id;
    String tel;
    

    void display(){ //自我介紹
        System.out.println("飼主身份證："+ id);
        System.out.println("飼主電話："+ tel);
    }
    
    
    public Keeper(String i,  String tl){
        id=i;
        tel=tl;
    }
}
