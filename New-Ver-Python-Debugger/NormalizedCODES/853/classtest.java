import java . util . Scanner ; 
public class classtest { 
public static void main ( String [ ] args ) { 
Scanner a = new Scanner ( System . in ) ; 
System . out . println ( "請輸入寵物種類:" ) ; 
String input1 = a . nextLine ( ) ; 
System . out . println ( "請輸入寵物編號：" ) ; 
String input2 = a . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱：" ) ; 
String input3 = a . nextLine ( ) ; 
System . out . println ( "請輸入飼主身分證字號：" ) ; 
String input4 = a . nextLine ( ) ; 
System . out . println ( "請輸入飼主電話：" ) ; 
String input5 = a . nextLine ( ) ; 
Keeper keeper = new Keeper ( input4 , input5 ) ; 
Pet pet = new Pet ( input1 , input1 , input1 ) ; 
pet . display ( ) ; 
keeper . display ( ) ; 
if ( input1 == "狗" ) { 
System . out . println ( "您的寵物叫聲是：汪汪" ) ; 
} 
} 
} 
