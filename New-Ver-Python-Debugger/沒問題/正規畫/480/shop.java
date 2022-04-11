import java . util . Scanner ; 
public class shop { 
public static void main ( String [ ] args ) { 
System . out . println ( "請輸入寵物種類：" ) ; 
System . out . println ( "請輸入寵物編號：" ) ; 
System . out . println ( "請輸入寵物名稱：" ) ; 
System . out . println ( "請輸入飼主身分證字號：" ) ; 
System . out . println ( "請輸入飼主電話：" ) ; 
Pet p = new Pet ( args [ 0 ] , args [ 1 ] , args [ 2 ] ) ; 
Keeper user = new Keeper ( args [ 3 ] , args [ 4 ] ) ; 
p . display ( ) ; 
user . display ( ) ; 
p . speak ( ) ; 
} 
} 
