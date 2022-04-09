import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
Item [ ] list = new Item [ 10 ] ; 
Scanner keyboard = new Scanner ( System . in ) ; 
int total = 0 ; 
while ( true ) { 
System . out . println ( "請輸入訂購貨品名稱:" ) ; 
String num = keyboard . nextLine ( ) ; 
if ( name . equals ( "0" ) ) break ; 
System . out . println ( "請輸入訂單編號：" ) ; 
String number = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入日期：" ) ; 
String date = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入貨幣單位：" ) ; 
String dollar = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入客戶公司行號：" ) ; 
String cp = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入客戶地址：" ) ; 
String adress = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入客戶聯絡人：" ) ; 
String br = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入客戶電話：" ) ; 
String tel = keyboard . nextLine ( ) ; 
String s = keyboard . nextLine ( ) ; 
System . out . println ( "訂購貨品名稱：" ) ; 
String name = keyboard . nextLine ( ) ; 
System . out . println ( "訂購貨品單價：" ) ; 
double money = keyboard . nextDouble ( ) ; 
System . out . println ( "訂購貨品折扣：" ) ; 
int nums = keyboard . nextInt ( ) ; 
double discount = keyboard . nextDouble ( ) ; 
double ans = keyboard . nextDouble ( ) ; 
Order o = new Order ( name , date , dollar ) ; 
Item i = new Item ( num , name , money , nums , discount , ans ) ; 
Customer c = new Customer ( cp , adress , br , tel , s ) ; 
System . out . println ( "此筆訂單總共:" + Order . dollar + ans ) ; 
} 
} 
} 
