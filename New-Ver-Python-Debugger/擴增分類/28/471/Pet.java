import java . util . Scanner ; 
class Pet { 
static int count ; 
String pid ; 
String name ; 
String type ; 
static String pet ; 
Pet ( String type , String pid , String name ) { 
count ++ ; 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
void speak ( ) { 
if ( type == "狗" ) 
System . out . println ( "您的寵物叫聲 : 汪汪" ) ; 
else 
System . out . println ( "您的寵物叫聲 : 喵喵" ) ; 
} 
void display ( ) { 
} 
public static void main ( String [ ] args ) { 
String a , b , c , d , e ; 
Scanner scanner = new Scanner ( System . in ) ; 
System . out . println ( "請輸入寵物種類 : " ) ; 
a = scanner . nextLine ( ) ; 
System . out . println ( "請輸入寵物編號 : " ) ; 
b = scanner . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱 : " ) ; 
c = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主身分證字號 : " ) ; 
d = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主電話 : " ) ; 
e = scanner . nextLine ( ) ; 
Pet p = new Pet ( a , b , c ) ; 
System . out . println ( "寵物總類 : " + p . type ) ; 
System . out . println ( "寵物編號 : " + p . type ) ; 
System . out . println ( "寵物名稱 : " + p . type ) ; 
System . out . println ( "飼主身份證字號 : " + p . type ) ; 
System . out . println ( "飼主電話 : " + p . type ) ; 
} 
} 
