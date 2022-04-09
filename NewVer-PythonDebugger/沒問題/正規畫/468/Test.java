import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
Scanner scan = new Scanner ( System . in ) ; 
boolean b = true ; 
while ( b ) { 
System . out . print ( "請輸入寵物種類(貓或狗) : " ) ; 
String pt = scan . nextLine ( ) ; 
if ( pt . equals ( "0" ) ) { 
b = false ; 
break ; 
} 
System . out . print ( "請輸入寵物編號 : " ) ; 
String pp = scan . nextLine ( ) ; 
System . out . print ( "請輸入寵物名稱 : " ) ; 
String pn = scan . nextLine ( ) ; 
System . out . print ( "請輸入飼主名稱 : " ) ; 
String kn = scan . nextLine ( ) ; 
System . out . print ( "請輸入飼主身分證字號 : " ) ; 
String ki = scan . nextLine ( ) ; 
System . out . print ( "請輸入飼主電話 : " ) ; 
String kt = scan . nextLine ( ) ; 
System . out . print ( "--------------------" ) ; 
Keeper k = new Keeper ( kn , ki , kt ) ; 
Pet p = new Pet ( pp , pn , pt , k ) ; 
System . out . println ( "您輸入的是 : " ) ; 
p . display ( ) ; 
k . display ( ) ; 
p . speak ( ) ; 
System . out . print ( "--------------------" ) ; 
} 
System . out . println ( "總共" + Pet . getcount ( ) + "隻寵物!" ) ; 
} 
} 
