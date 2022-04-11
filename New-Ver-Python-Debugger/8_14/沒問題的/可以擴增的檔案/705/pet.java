import java . util . Scanner ; 
public class pet { 
static int count ; 
String pid ; 
String name ; 
String type ; 
void Pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
String Speak ( ) { 
if ( pid == "貓" ) { 
return "喵喵" ; 
} 
else if ( pid == "狗" ) { 
return "汪汪" ; 
} 
} 
void display ( ) { 
System . out . println ( "寵物種類:" + pid ) ; 
System . out . println ( "寵物編號:" + type ) ; 
System . out . println ( "寵物名稱:" + name ) ; 
} 
public static void main ( String [ ] args ) { 
String pid ; 
String name ; 
String type ; 
pet x = new pet ( ) ; 
Scanner keyboard = new Scanner ( System . in ) ; 
System . out . println ( "請輸入寵物種類:" ) ; 
pid = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入寵物編號:" ) ; 
type = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱:" ) ; 
name = keyboard . nextLine ( ) ; 
String spe = pid . Speak ( ) ; 
System . out . println ( "您的寵物叫聲是:" + spe ) ; 
} 
} 
