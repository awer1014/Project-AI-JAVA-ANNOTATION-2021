import java . util . Scanner ; 
public class pet { 
static Scanner sca = new Scanner ( System . in ) ; 
static int count = 0 ; 
String pid ; 
String name ; 
String type ; 
keeper keep ; 
pet ( String pid , String name , String type , keeper k ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
this . keep = k ; 
count += 1 ; 
} 
public void speak ( String name ) { 
if ( name == "貓" ) 
System . out . println ( "喵喵" ) ; 
else if ( name == "狗" ) 
System . out . println ( "汪汪" ) ; 
else 
System . out . println ( "叫聲未收錄" ) ; 
} 
public void petdisplay ( ) { 
System . out . println ( "編號：" + pid ) ; 
System . out . println ( "名稱：" + name ) ; 
System . out . println ( "種類：" + type ) ; 
} 
public static void main ( String [ ] args ) { 
int i = 1 ; 
while ( i != 0 ) { 
System . out . print ( "請輸入寵物種類：" ) ; 
String p = sca . next ( ) ; 
if ( Integer . parseInt ( p ) == 0 ) break ; 
System . out . println ( ) ; 
System . out . print ( "請輸入寵物編號：" ) ; 
String n = sca . next ( ) ; 
System . out . println ( ) ; 
System . out . print ( "請輸入寵物名稱：" ) ; 
String t = sca . next ( ) ; 
System . out . println ( ) ; 
System . out . print ( "請輸入飼主身分證字號：" ) ; 
String id = sca . next ( ) ; 
System . out . println ( ) ; 
System . out . print ( "請輸入飼主電話：" ) ; 
String tel = sca . next ( ) ; 
System . out . println ( ) ; 
keeper A = new keeper ( id , tel ) ; 
pet pet = new pet ( p , n , t , A ) ; 
System . out . println ( "------------" ) ; 
pet . petdisplay ( ) ; 
A . keeperdisplay ( ) ; 
pet . speak ( pet . pid ) ; 
System . out . println ( "------------" ) ; 
count ++ ; 
} 
System . out . println ( "總共" + count + "隻寵物！" ) ; 
} 
} 
