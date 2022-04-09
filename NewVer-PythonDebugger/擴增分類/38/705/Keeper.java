import java . util . Scanner ; 
public class Keeper { 
String id ; 
String tel ; 
void Keeper ( String id , String tel ) { 
this . id = id ; 
this . tel = tel ; 
} 
void display ( ) { 
System . out . println ( "請輸入飼主身份證字號:" + id ) ; 
System . out . println ( "請輸入飼主身份證字號:" + tel ) ; 
} 
public static void main ( String [ ] args ) { 
String id ; 
String tel ; 
Keeper x = new Keeper ( ) ; 
Scanner keyboard = new Scanner ( System . in ) ; 
System . out . println ( "飼主身份證字號:" ) ; 
id = keyboard . nextLine ( ) ; 
System . out . println ( "飼主電話:" ) ; 
tel = keyboard . nextLine ( ) ; 
} 
} 
