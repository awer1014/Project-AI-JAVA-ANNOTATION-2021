import java.util.Scanner;

public class test
{
    public static void main(String[] args){
        Vote[] list = new Vote[10];
        int total = 0;
        int win;
        Scanner scan = new Scanner(System.in);
        System.out.println("請輸入有幾位候選人(最多10位):");
        int p = scan.nextInt();
        for(int i=0; i<p; p++){
        String name = scan.nextLine();
        int ticket = scan.nextInt();
        Vote v = new Vote(name, ticket);
        list[p++] = v;    
        }
        System.out.println("各候選人得票資訊如下:");
        for(int i=0; i<p; i++){
        Vote v = list[i];
        v.display();
        } 
        System.out.println("-------------------------------------");
        for(int i=0; i<p-1; i++){
        Vote v =list[i];
        Vote v2 =list[i++];
        if(v.rate>v2.rate)
             win = i;
        else 
             win =i++;
        }
        Vote v=list [win];
        System.out.println("當選人得票資訊如下:");

        v.display();
    }    
    }

