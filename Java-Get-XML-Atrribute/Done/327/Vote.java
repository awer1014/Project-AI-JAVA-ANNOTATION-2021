import java.util.Scanner;

public class Vote
{
 public static void main(String[] args)
 {
     int people ;
     String peoplename;
     int PS;
     int all;
     Scanner scan = new Scanner(System.in);
     System.out.println("請輸入有幾位候選人(最多10位):");
     people = scan.nextInt();
     System.out.println(people);
     for(int k = 1;k <= people;k++)
     {
       Scanner scan1 = new Scanner(System.in);
       Scanner scan2 = new Scanner(System.in);
       System.out.println("請輸入第"+k+"位候選人的姓名和得票數:");

       peoplename = scan1.next();
       PS = scan2.nextInt();
       System.out.println(peoplename +" "+ PS);
       //all += PS;
     }

     System.out.println("各候選人得票資訊如下:");
     System.out.println("姓名 得票數 得票比率");
     for(int k = 1;k <= people;k++)
     {




     }
 }

}
