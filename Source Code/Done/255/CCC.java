public class CCC
{
   String le;
   String day;
   String moeny;
   CCC(String le,String day,String moeny)
   {
       this.le=le;
       this.day=day;
       this.moeny=moeny;
   }
   void display()
   {
       System.out.println("您輸入的訂單資料如下：");
       System.out.println("訂單編號："+le);
       System.out.println("日期:"+day);
       System.out.println("貨幣單位:"+moeny);
   }
}
