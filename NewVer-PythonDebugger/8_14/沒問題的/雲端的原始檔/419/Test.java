import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
System . out . println ( "請輸入寵物種類: " ) ; 
Scanner scanner = new Scanner ( System . in ) ; 
Pet . type = scanner . nextLine ( ) ; 
System . out . println ( "請輸入寵物編號 " ) ; 
Scanner scanner1 = new Scanner ( System . in ) ; 
Pet . pid = scanner . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱：" ) ; 
Scanner scanner2 = new Scanner ( System . in ) ; 
Pet . name = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主身分證字號: " ) ; 
Scanner scanner3 = new Scanner ( System . in ) ; 
Keeper . id = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主電話: " ) ; 
Scanner scanner4 = new Scanner ( System . in ) ; 
Keeper . tel = scanner . nextLine ( ) ; 
System . out . println ( "寵物種類：" + Pet . type ) ; 
System . out . println ( "寵物編號：" + Pet . pid ) ; 
System . out . println ( "寵物名稱：" + Pet . name ) ; 
System . out . println ( "飼主身份證：" + Keeper . id ) ; 
System . out . println ( "飼主電話：" + Keeper . tel ) ; 
System . out . println ( "您的寵物叫聲:" ) ; 
if ( Pet . type == "cat" ) 
System . out . println ( "喵喵" ) ; 
if ( Pet . type == "dog" ) 
System . out . println ( "汪汪" ) ; 
System . out . println ( "總共" + Pet . amount + "隻寵物!" ) ; 
} 
} 
