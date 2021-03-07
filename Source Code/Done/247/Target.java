import java.util.Random;
public class Target
{
   static int sum1=0,sum2=0,n;
   static double a,b;
   static void toss(double n)
   {
       n=100000;
       for(int i=0;i<n;i++)
       {
           
           Random rand=new Random();
           double x=rand.nextDouble();double y=rand.nextDouble();
           if((x*x+y*y)>1)sum1++;
           a=sum1/n;
           System.out.println("圓外次數="+sum1+"出現比率"+a);
           if((x*x+y*y)<1)sum2++;
           b=sum2/n;
           System.out.println("圓內次數="+sum2+"出現比率"+b);
       }
   }
    public static int  main(String [] args)
   {
       Random rand=new Random();
       double x=rand.nextDouble();double y=rand.nextDouble();
       n=Integer.parseInt(args[0]);
       System.out.println(Target.toss(n));
   }
}