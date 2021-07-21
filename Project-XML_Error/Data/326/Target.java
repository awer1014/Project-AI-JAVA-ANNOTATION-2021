import java.util.Random;
import java.util.Scanner;
public class Target
{
 int I = 0;
  void toss(int n)
  {
     for(int K = 0;K <= n;k++)
     {
       int RANDOMX = (int)(Math.random()*5)+(-2);
       int RANDOMY = (int)(Math.random()*5)+(-2);
       if(RANDOMX*RANDOMY >= 1)
         {
            I++;
         }

     }
       return I;
  }

 public static void main(String[] args) 
 {

    Scanner scan = new Scanner(System.in);
    int n,RANDOMX,RANDOMY;
    System.out.println("請輸入模擬次數：");
    if (scan.hasNextInt())
    {
      n = scan.nextInt();
    }
    System.out.println("圓內次數 ="+ n-I);
    System.out.println("出現比率 :"+ (n-I)/n);
    System.out.println("圓外次數 ="+ I);
    System.out.println("出現比率 :"+ I/n);
 }



}
