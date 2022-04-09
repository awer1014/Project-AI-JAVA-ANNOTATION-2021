import java . util . Scanner ; 
public class Pet { 
static int count ; 
private String pid ; 
private String name ; 
private String type ; 
private int i ; 
private String sound [ ] = { "旺旺" , "喵喵" } ; 
Pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
public void display ( ) { 
System . out . println ( "寵物種類； " + pid ) ; 
System . out . println ( "寵物編號： " + type ) ; 
System . out . println ( "寵物名稱： " + name ) ; 
if ( pid == "貓" ) 
i = 1 ; 
else 
i = 0 ; 
System . out . println ( "您的寵物叫聲是：" + sound [ i ] ) ; 
} 
public static void main ( String [ ] args ) { 
Scanner keyboard = new Scanner ( System . in ) ; 
System . out . println ( "請輸入寵物種類 :" ) ; 
String m = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入寵物編號 ： " ) ; 
String n = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱 ： " ) ; 
String name = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入飼主身分證字號 ： " ) ; 
String id = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入飼主電話 ： " ) ; 
String tel = keyboard . nextLine ( ) ; 
Pet x = new Pet ( m , name , n ) ; 
Keeper y = new Keeper ( id , tel ) ; 
x . display ( ) ; 
y . getid ( ) ; 
y . gettel ( ) ; 
y . display1 ( ) ; 
} 
} 
