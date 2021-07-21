import java.util.*;
public class Target
{
    static int in;
    static int out;
    static int randomhit(){
       int c= (int) (Math.random()+1);
       if(c<0.5){in++; return 0;}
       else{out++;return 1;}
           
    }
    void toss(int n)
    {
       System.out.println("圓內次數 ="+in+"出現比率:");
       System.out.println("圓外次數 ="+out+"出現比率:");
    }
    
    public static void main(String[]args)
    {
       randomhit();
       int in; 
       int out;
       for(int i=0;i<100000;i++)
       {
           randomhit();
           toss(n);
           
        
       }
    }
}












