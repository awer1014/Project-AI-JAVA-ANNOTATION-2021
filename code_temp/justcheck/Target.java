import java.util.Random;
public class Target{
    static int n;
    static void toss(){
        int out = 0;
        int in = 0;
        Random run = new Random();
        for(int i=1;i<=n;i++){

            double x=run.nextDouble()*2;
            double y=run.nextDouble()*2;
            double d=Math.sqrt((x-1)*(x-1)+(y-1)*(y-1));
            if(d<=1)
                in++;
            else 
                out++;
        }
        // double pout=1.0*(out/n);
        // double pin=1.0*(in/n);
        System.out.println("圈外次數: "+out+"出現比率: "+1.0*(out/n));
        System.out.println("圈內次數: "+in+"出現比率: "+1.0*(in/n));

    }

    public static void main(String[] args){
        //System.out.println("請輸入模擬次數:");
         n = Integer.parseInt(args[0]);
        toss();

    }
}