public class Cuboid { 
double length , width , height ; 
public Cuboid ( double length , double width , double height ) { 
System . out . println ( "輸入長" ) ; 
double length = scanner . nextLine ( ) ; 
System . out . println ( "輸入寬" ) ; 
double width = scanner . nextLine ( ) ; 
System . out . println ( "輸入高" ) ; 
double height = scanner . nextLine ( ) ; 
} 
public static double Surface ( double length , double width , double height ) { 
return 2 * length * width + width * height + length * height ; 
} 
public static double Volume ( double length , double width , double height ) { 
return length * width * height ; 
} 
public static void main ( String [ ] args ) { 
System . out . println ( cuboid . Surface ( ) ) ; 
System . out . println ( cuboid . Volume ( ) ) ; 
} 
} 
