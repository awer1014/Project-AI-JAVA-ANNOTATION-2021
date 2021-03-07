import java.util.Scanner;
public class Test
{
    static Scanner cin = new Scanner(System.in);
    public static void main(String[] args)
    {
        int count=0;
        String p[]=new String[100];
        String n[]=new String[100];
        String t[]=new String[100];
        String i[]=new String[100];
        String tl[]=new String[100];
        do
        {
            System.out.println("請輸入寵物種類: ");
            t[count] = cin.nextLine();
            if(t[count].equals("0")) break;
            System.out.println("請輸入寵物編號 ");
            p[count] = cin.nextLine();
            System.out.println("請輸入寵物名稱：");
            n[count] = cin.nextLine();
            System.out.println("請輸入飼主身分證字號: ");
            i[count] = cin.nextLine();
            System.out.println("請輸入飼主電話: ");
            tl[count] = cin.nextLine();
            
            count++;
            System.out.println("-------------------------------------------");
        }
        while(true);
        for(int j =0;j<count;j++)
        {
            Keeper keeper = new Keeper(i[j],tl[j]);
            Pet pet = new Pet(p[j],n[j],t[j],keeper);
            pet.display();
        }
        System.out.println("總共"+count+"隻寵物");
        System.out.println("------------------------------------------------");
    }
}
