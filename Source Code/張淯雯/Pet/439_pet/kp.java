import java.io.*;
import java.util.*;
import java.math.*;
public class Test
{
    static Scanner cin = new Scanner(System.in);
    
    public static void main(String[] args){
     String p[]=new String[100];
     String n[]=new String[100];
     String t[]=new String[100];
     String i[]=new String[100];
     String tl[]=new String[100];
    //編號 名稱 類別 身份證 電話
     int f=0;
     do{
         System.out.println("請輸入寵物種類：");
         t[f] = cin.nextLine();
         System.out.println("請輸入寵物編號：");
         p[f] = cin.nextLine();
         System.out.println("請輸入寵物名稱：");
         n[f] = cin.nextLine();
         System.out.println("請輸入飼主身份證字號：");
         i[f] = cin.nextLine();
         System.out.println("請輸入飼主電話：");
         tl[f] = cin.nextLine();
         f++;
         System.out.println("----------------------------------------");
        }while(t!="0");
     for(int j=0;j<f;j++){
         Pet pet=new Pet(p[j],n[j],t[j]);
         Keeper keeper=new Keeper(i[j],tl[i]);
         pet.display();
         keeper.display();
         pet.speak(n[j]);
        }
}
}