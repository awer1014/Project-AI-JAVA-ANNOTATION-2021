import java.util.Scanner;
public class Test
{
    
    public static void main(String[] args){
    
    Scanner scan = new Scanner (System.in);
    Items[] item=new Items[10];
    System.out.println("請輸入訂單編號：");
    String code = scan.nextLine();
    System.out.println("請輸入日期：");
    String date = scan.nextLine();
    System.out.println("請輸入貨幣單位：");
    String x = scan.nextLine();
    Order od = new Order( code, date, x);
    System.out.println("請輸入客戶公司行號：");
    String cname = scan.nextLine();
    System.out.println("請輸入客戶地址：");
    String caddress = scan.nextLine();
    System.out.println("請輸入連絡人：");
    String crelatives = scan.nextLine();
    System.out.println("請輸入客戶電話：");
    String cphone= scan.nextLine();
    company cp = new company( cname,caddress,crelatives,cphone);
    while(true){
    System.out.println("請輸入訂購貨品編號：");
    String code= scan.nextLine();
    if( code.equals('0'))break;
    
    System.out.println("請輸入訂購貨品名稱：");
    String iname= scan.nextLine();
    System.out.println("請輸入訂購貨品單價：");
    String iprice= scan.nextdouble();
    System.out.println("請輸入訂購貨品數量：");
    String inum= scan.nextInt();
    System.out.println("請輸入訂購貨品折扣：");
    String idiscount= scan.nextdouble();
    
    }
    
    }
    }

