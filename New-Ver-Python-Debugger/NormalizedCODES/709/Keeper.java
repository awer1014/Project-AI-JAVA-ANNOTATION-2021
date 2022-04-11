public class Keeper { 
String id ; 
String tel ; 
public Keeper ( String id , String tel ) { 
this . id = id ; 
this . tel = tel ; 
} 
public void display ( ) { 
System . out . println ( "飼主身分證字號：" + id ) ; 
System . out . println ( "飼主電話：" + tel ) ; 
} 
public static void main ( String [ ] args ) { 
String t , p , n , i , te ; 
Keeper keeper = new Keeper ( ) ; 
Pet p1 = new Pet ( "狗" , "d001" , "Timmy" , keeper ) ; 
Pet p2 = new Pet ( "貓" , "c001" , "Catty" , keeper ) ; 
p1 . display ( ) ; 
p2 . display ( ) ; 
} 
} 
