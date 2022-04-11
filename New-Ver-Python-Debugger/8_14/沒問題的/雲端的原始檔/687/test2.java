import java . util . Scanner ; 
public class test2 { 
static Scanner keyboard = new Scanner ( System . in ) ; 
public static String type , pid , name , id , tel ; 
public static void input ( ) { 
System . out . println ( "請輸入寵物種類" ) ; 
type = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入寵物編號" ) ; 
pid = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱" ) ; 
name = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入飼主身分證字號" ) ; 
id = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入飼主電話" ) ; 
tel = keyboard . nextLine ( ) ; 
} 
public static void main ( String [ ] args ) { 
input ( ) ; 
Pet P1 = new Pet ( type , pid , name ) ; 
P1 . keep ( id ) ; 
Keeper K1 = new Keeper ( id , tel ) ; 
System . out . println ( "-------------------------------------" ) ; 
input ( ) ; 
Pet P2 = new Pet ( type , pid , name ) ; 
P2 . keep ( id ) ; 
Keeper K2 = new Keeper ( id , tel ) ; 
System . out . println ( "-------------------------------------" ) ; 
P1 . display ( ) ; 
K1 . display ( ) ; 
P1 . speak ( ) ; 
System . out . println ( "-------------------------------------" ) ; 
P2 . display ( ) ; 
K2 . display ( ) ; 
P2 . speak ( ) ; 
} 
} 
