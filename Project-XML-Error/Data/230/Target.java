import java.util.Random;
import java.util.Scanner;
 class Target
{
    double x;
    double y;
    int in;
    int out;
    public double rate1;
    public int n;
    int  InOrOut(double x, double y)
    {
     if((x*x)+(y*y)<=1)
         return in++;
     if((x*x)+(y*y)>1)
         return out++;
     return 0;
    }
    
    public void toss(int n){
    for(int i=0; i< n; i++){
        x = Math.random()*1.414;
        y = Math.random()*1.414;
        InOrOut(x,y);
        rate1 = in/(in+out);
        }
    }
   
    
}
