import java . util . Scanner ; 
class Keeper { 
String id ; 
String tel ; 
Keeper ( String id , String tel ) { 
this . id = id ; 
this . tel = tel ; 
} 
void display ( ) { 
System . out . println ( "飼主身分證字號:" + this . id ) ; 
System . out . println ( "飼主電話：" + tel ) ; 
} 
public static void main ( String [ ] args ) { 
String a1 , a2 , a3 , a4 , a5 ; 
for ( int i = 0 ; i < 100 ; i ++ ) { 
Scanner scanner = new Scanner ( System . in ) ; 
System . out . println ( "請輸入寵物種類:" ) ; 
a1 = scanner . nextLine ( ) ; 
System . out . println ( "請輸入寵物編號:" ) ; 
a2 = scanner . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱:" ) ; 
a3 = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主身分證字號:" ) ; 
a4 = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主電話:" ) ; 
a5 = scanner . nextLine ( ) ; 
pet K = new pet ( a2 , a3 , a1 ) ; 
Keeper S = new Keeper ( a4 , a5 ) ; 
K . display ( ) ; 
S . display ( ) ; 
K . speak ( ) ; 
int r = K . A ( ) ; 
System . out . println ( "總共" + r + "+隻寵物！" ) ; 
} 
} 
} 
