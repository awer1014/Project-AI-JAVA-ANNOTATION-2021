import java . util . Scanner ; 
public class pet { 
static int count ; 
String pid ; 
String name ; 
String type ; 
Keeper keeper ; 
void Pet ( String pid , String name , String type , Keeper keeper ) { 
count ++ ; 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
this . keeper = keeper ; 
} 
String Speak ( ) { 
if ( pid . equals ( "貓" ) ) { 
return "喵喵" ; 
} 
else if ( pid . equals ( "狗" ) ) { 
return "汪汪" ; 
} 
} 
void display ( ) { 
System . out . println ( "寵物種類:" + pid ) ; 
System . out . println ( "寵物編號:" + type ) ; 
System . out . println ( "寵物名稱:" + name ) ; 
keeper . display ( ) ; 
} 
public static void main ( String [ ] args ) { 
String pid ; 
String name ; 
String type ; 
String id ; 
String tel ; 
Scanner keyboard = new Scanner ( System . in ) ; 
System . out . println ( "飼主身份證字號:" ) ; 
id = keyboard . nextLine ( ) ; 
System . out . println ( "飼主電話:" ) ; 
tel = keyboard . nextLine ( ) ; 
Keeper k = new Keeper ( id , tel ) ; 
Scanner keyboard = new Scanner ( System . in ) ; 
System . out . println ( "請輸入寵物種類:" ) ; 
pid = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入寵物編號:" ) ; 
type = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱:" ) ; 
name = keyboard . nextLine ( ) ; 
pet x = new pet ( pid , name , type , k ) ; 
String spe = x . Speak ( ) ; 
System . out . println ( "您的寵物叫聲是:" + spe ) ; 
} 
} 
