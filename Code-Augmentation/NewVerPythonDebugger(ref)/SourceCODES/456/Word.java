import java.util.Scanner;
public class Word
{
    static String word[]={"0","1","2","3","4","5","6","7","8","9"};
    static int Count(String a, String b)
    {
        int count =0;
        for(int i=0;i<a.length()-b.length()+1;i++)
        {
            String tmp = a.length(i,i+b.length());
            if(tmp.equlas(word))
            {
                count++;
            }
        }
        return count;
    } 
    public static void main(String[] args)
    {
        String a;   
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextLine();
        int n=a.length();
        System.out.print("長度為:"+a.length());
        System.out.print("數字  次數");
        for(int k=0;k<10;k++)
        {
            int count=Count(a,word[k]);
            System.out.print("   "+word[k]+"  "+count);
        }
    }
}
