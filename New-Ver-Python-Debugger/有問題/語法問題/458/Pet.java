import java . util . Scanner ; 
public class Pet { 
static int count ; 
String pid ; 
String name ; 
String type ; 
Pet ( String type , String pid , String name ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
void speak ( ) { 
if ( type == "貓" ) if ( type == "狗" ) } 
void display ( ) { 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "寵物種類:" + type ) ; 
System . out . println ( "寵物編號:" + pid ) ; 
System . out . println ( "寵物名稱:" + name ) ; 
} 
public static void main ( String [ ] args ) { 
String pid ; 
String name ; 
String type ; 
String hostname ; 
String hosttel ; 
Scanner ppid = new Scanner ( System . in ) ; 
System . out . println ( "請輸入寵物種類:" ) ; 
type = ppid . nextLine ( ) ; 
System . out . println ( "請輸入寵物編號:" ) ; 
pid = ppid . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱:" ) ; 
name = ppid . nextLine ( ) ; 
count ++ ; 
System . out . println ( "請輸入飼主身份證字號:" ) ; 
hostname = ppid . nextLine ( ) ; 
System . out . println ( "請輸入飼主電話:" ) ; 
hosttel = ppid . nextLine ( ) ; 
Pet a = new Pet ( type , pid , name ) ; 
a . display ( ) ; 
Keeper host = new Keeper ( hostname , hosttel ) ; 
host . diaplay ( ) ; 
} 
} 
