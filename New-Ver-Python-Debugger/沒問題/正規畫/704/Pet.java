import java . util . Scanner ; 
public class Pet { 
public static int count ; 
public String pid ; 
public String name ; 
public String type ; 
Keeper keeper ; 
public Pet ( String pid , String name , String type , Keeper keeper ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
this . keeper = keeper ; 
count ++ ; 
} 
public void speak ( ) { 
System . out . println ( "您的寵物叫聲是：" ) ; 
if ( type . equals ( "狗" ) ) 
System . out . println ( "汪汪" ) ; 
else if ( type . equals ( "貓" ) ) 
System . out . println ( "喵喵" ) ; 
} 
public void display ( ) { 
System . out . println ( "寵物種類:" + type ) ; 
System . out . println ( "寵物編號：" + pid ) ; 
System . out . println ( "寵物名稱：" + name ) ; 
keeper . display ( ) ; 
} 
public static void main ( ) { 
String ty , p , n , i , te ; 
Scanner scanner = new Scanner ( System . in ) ; 
System . out . println ( "請輸入寵物種類:" ) ; 
ty = scanner . nextLine ( ) ; 
System . out . println ( "請輸入寵物編號：" ) ; 
p = scanner . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱：" ) ; 
n = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主身分證字號：" ) ; 
i = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主電話：" ) ; 
te = scanner . nextLine ( ) ; 
Keeper k = new Keeper ( i , te ) ; 
Pet a = new Pet ( p , n , ty , k ) ; 
a . display ( ) ; 
k . display ( ) ; 
a . speak ( ) ; 
} 
} 
