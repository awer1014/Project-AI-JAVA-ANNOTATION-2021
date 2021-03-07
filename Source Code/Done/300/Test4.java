import java.util.Scanner;
public class Test4
{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("請輸入訂單編號 ： ");
        String s1 = scan.next();
        System.out.print("請輸入日期 ： ");
        String s2 = scan.next();
        System.out.print("請輸入貨幣單位 ： ");
        String s3 = scan.next();
        Order o = new Order(s1,s2,s3);
        
        System.out.print("------------------------------------");
        System.out.print("請輸入客戶公司行號 ： ");
        String s4 = scan.next();
        System.out.print("請輸入客戶地址 ： ");
        String s5 = scan.next();
        System.out.print("請輸入連絡人 ： ");
        String s6 = scan.next();
        System.out.print("請輸入客戶電話 ： ");
        String s7 = scan.next();
        System.out.print("------------------------------------");
        Customer c = new Customer(s4,s5,s6,s7);
        o.setCustomer(c);
        int i;
        for(i=0;i<10;i++){
            System.out.print("請輸入訂購貨品編號 ： ");
            String s8 = scan.next();
            System.out.print("請輸入訂購貨品名稱 ： ");
            String s9 = scan.next();
            System.out.print("請輸入訂購貨品單價 ： ");
            double d1 = scan.nextDouble();
            System.out.print("請輸入訂購貨品數量 ： ");
            int i1 = scan.nextInt();
            System.out.print("請輸入訂購貨品折扣 ： ");
            double d2 = scan.nextDouble();
            Item b = new Item(s8,s9,d1,d2,i1);
            if(s8.equals("0"))break;
            else o.setItem(i,b);
        }
        System.out.println("您輸入的訂單資料如下 ： ");
        o.display(i);
    }
}
