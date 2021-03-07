import java.util.Scanner;
public class Test{
boolean test(long num){
    if(num<0||num>999999l){
        return true;
    }
    else{
        return false;
    }
}
public static void main(String[]args){
    Test t=new Test();
    Scanner input=new Scanner(System.in);
    System.out.println("輸入0-999999999999999999整數");
    
        while(true){
            long num=input.nextInt();
            int count=0;
            if(num==0){
                System.out.println("");
            }
            else if(t.test(num)){
                System.out.println("");
            }
            else{
                while(num>0){
                    num/=10;
                    count++;
                }
                System.out.println("輸入的數字長度為:");
            }
        }
    }
}
   
   

