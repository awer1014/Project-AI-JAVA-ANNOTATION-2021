import java . util . Scanner ; 
class TestScanner2 { 
public static void main ( ) { 
String pid ; 
String name ; 
String type ; 
String id ; 
String tell ; 
int num ; 
Scanner scanner = new Scanner ( System . in ) ; 
System . out . println ( "請輸入寵物種類：" ) ; 
type = scanner . nextLine ( ) ; 
System . out . println ( "請輸入請輸入寵物編號：" ) ; 
pid = scanner . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱:" ) ; 
name = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主身分證：" ) ; 
id = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主電話：" ) ; 
tell = scanner . nextLine ( ) ; 
System . out . println ( "-----------------------------------------------------------" ) ; 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "寵物種類:" + type ) ; 
System . out . println ( "寵物編號:" + pid ) ; 
System . out . println ( "寵物名稱:" + name ) ; 
System . out . println ( "飼主身分證:" + id ) ; 
System . out . println ( "飼主電話:" + tell ) ; 
if ( type == "狗" ) 
System . out . println ( "您寵物的叫聲:" + "旺旺" ) ; 
if ( type == "貓" ) 
System . out . println ( "您寵物的叫聲:" + "喵喵" ) ; 
} 
} 
