public class Pet { 
int count ; 
String pid ; 
String name ; 
String type ; 
Pet ( String p , String n , String t ) { 
this . pid = p ; 
this . name = n ; 
this . type = t ; 
} 
void speak ( ) { 
} 
void display ( ) { 
System . out . println ( "寵物種類:" + type ) ; 
System . out . println ( "寵物編號:" + pid ) ; 
System . out . println ( "寵物名稱:" + name ) ; 
} 
public static void main ( String [ ] args ) { 
String p = args [ 0 ] ; 
String n = args [ 1 ] ; 
String t = args [ 2 ] ; 
String P = args [ 3 ] ; 
String N = args [ 4 ] ; 
String T = args [ 5 ] ; 
Pet x = new Pet ( p , n , t ) ; 
Keeper a = new Keeper ( "A123456" , "02-2586777" ) ; 
System . out . println ( "請輸入寵物種類:" + t ) ; 
System . out . println ( "請輸入寵物編號:" + p ) ; 
System . out . println ( "請輸入寵物名稱:" + n ) ; 
System . out . println ( "請輸入飼主身分證字號：A123456" ) ; 
System . out . println ( "請輸入飼主電話：02-2586777" ) ; 
System . out . println ( "------------------------------------" ) ; 
System . out . println ( "請輸入寵物種類:" + T ) ; 
System . out . println ( "請輸入寵物編號:" + P ) ; 
System . out . println ( "請輸入寵物名稱:" + N ) ; 
System . out . println ( "請輸入飼主身分證字號：F123456" ) ; 
System . out . println ( "請輸入飼主電話：07-2856977" ) ; 
System . out . println ( "------------------------------------" ) ; 
System . out . println ( "您輸入的是：" ) ; 
x . display ( ) ; 
a . display ( ) ; 
System . out . println ( "您的寵物叫聲是：" ) ; 
System . out . println ( "------------------------------------" ) ; 
Pet y = new Pet ( P , N , T ) ; 
Keeper b = new Keeper ( "F123456" , "07-2856977" ) ; 
y . display ( ) ; 
b . display ( ) ; 
System . out . println ( "您的寵物叫聲是：" ) ; 
System . out . println ( "------------------------------------" ) ; 
} 
} 
