import java.util.Scanner;
public class test
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            System.out.println("請輸入寵物種類:");
            String s=sc.nextLine();
            if(s.equals("0"))
                break;
            System.out.println("請輸入寵物編號:");
            String sp=sc.nextLine();
            System.out.println("請輸入寵物名稱:");
            String sn=sc.nextLine();
            System.out.println("請輸入飼主身分證字號:");
            String se=sc.nextLine();
            System.out.println("請輸入飼主電話:");
            String st=sc.nextLine();
            System.out.println("-------------------------------------");
        }
        System.out.println("您輸入的是:");
    }
}
