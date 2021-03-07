import java.util.Scanner;
public class Word
{
    public static void main(String[] args) {
           String s = "This is a test. The test is not easy.";
           String[] tokens = s.split(" " || ".");
           int a=tokens.lenght();
           int b=0;
           for(int k=0;k<a;k++){  
               for(int i=k+1;i<a;i++){
                   for(int j=0;j<k;j++){
                   if(tokens[k].equals(tokens[j]) continue;
                   else if(tokens[k].equals(tokens[i]) b++;
            }
        }
    }   
           System.out.println("會輸出：");
           System.out.println("Word   Frequency:");
           System.out.println("-----------------------");
           
  
        
    }   
}
