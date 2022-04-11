import java . util . Scanner ; 
public class Keeper { 
String id ; 
String tel ; 
void display ( ) { 
System . out . println ( "Keeper's id: " + id ) ; 
System . out . println ( "Keeper's tel: " + tel ) ; 
} 
Keeper ( String i , String t ) { 
id = i ; 
tel = t ; 
} 
public static void main ( ) { 
Scanner scanner = new Scanner ( System . in ) ; 
System . out . println ( "Please enter the pet's type: " ) ; 
String Pt = scanner . nextLine ( ) ; 
while ( Pt != 0 ) { 
System . out . println ( "Please enter the pet's pid: " ) ; 
String p = scanner . nextLine ( ) ; 
System . out . println ( "Please enter the pet's name: " ) ; 
String n = scanner . nextLine ( ) ; 
System . out . println ( "Please enter the keeper's id: " ) ; 
String i = scanner . nextLine ( ) ; 
System . out . println ( "Please enter the keeper's tel: " ) ; 
String t = scanner . nextLine ( ) ; 
} 
Pet p = new Pet ( p , n , Pt ) ; 
Keeper k = new Keeper ( i , t ) ; 
} 
} 
