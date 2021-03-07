import java.util.Scanner;
public class Keeper
{   String id;
    String tel;
    public Keeper(String i,String t){
        this.id=i;
        this.tel=t;
    }
    public void display(String i,String t){
        System.out.println("飼主名稱: "+i);
        System.out.println("飼主電話: "+t);
    }
}
