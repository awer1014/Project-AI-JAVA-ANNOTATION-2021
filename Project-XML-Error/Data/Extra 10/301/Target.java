import java.util.*;
class Target{
    static double in = 0;
    static double out = 0;
    public static void main(String[] args){
        for(int k = 0 ;k < 100000;k++){
            double ranx = Math.random()*2-1;
            double rany = Math.random()*2-1;
            if(ranx * ranx + rany * rany <= 1)in++;
            else out++;
        }
        System.out.println("園內次數　= " + in + "出現比率　= " + in / 100000);
        System.out.println("園外次數　= " + out + "出現比率　= " + out / 100000);
    }
}