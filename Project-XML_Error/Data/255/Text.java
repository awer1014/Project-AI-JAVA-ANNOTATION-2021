import java.util.Scanner;
public class Text
{
  public static void main(String[] args)
  {
      CCC[] list = new CCC[10];
      man[] bad = new man[10];
      buy[] door = new buy[10];
      Scanner key=new Scanner(System.in);
      int ans=0;
      while(true)
      {
          System.out.println("請輸入訂單編號：");
          String a=key.nextLine();
          if(a.equals("0"))break;
          System.out.println("請輸入日期：");
          String c=key.nextLine();
          System.out.println("請輸入貨幣單位：");
          String d=key.nextLine();
          System.out.println("------------------------");
          System.out.println("請輸入客戶公司行號：");
          String e=key.nextLine();
          System.out.println("請輸入客戶地址：");
          String f=key.nextLine();
          System.out.println("請輸入連絡人：");
          String g=key.nextLine();
          System.out.println("請輸入客戶電話：");
          String b=key.nextLine();
          System.out.println("------------------------");
          System.out.println("請輸入訂購貨品編號：");
          String h=key.nextLine();
          System.out.println("請輸入訂購貨品名稱：");
          String i=key.nextLine();
          System.out.println("請輸入訂購貨品單價：");
          double j=key.nextDouble();
          System.out.println("請輸入訂購貨品數量：");
          int k=key.nextInt();
          System.out.println("請輸入訂購貨品折扣：");
          double l=key.nextDouble();
          System.out.println("------------------------");
          CCC op=new CCC(a,c,d);
          buy ep=new buy(h,i,j,k,l);
          man cp=new man(e,f,g,b);
          list [ans++];
          bad [ans++];
          door [ans++];
          System.out.println("您輸入的訂單資料如下：");
          for (int i=0; i<ans; i++) {
              Pet p = list[i];
              p.display();
              System.out.println("-------------------------------------");
            }
      }
  }
}
