import java . util . Scanner ; 
class KKBox { 
double length , width , height ; 
KKBox ( double length , double width , double height ) { 
this . length = length ; 
this . width = width ; 
this . height = height ; 
} 
double surfaceArea ( ) { 
return 2 * length * width + width * height + height * length ; 
} 
double volume ( ) { 
return length * width * height ; 
} 
public static void main ( String [ ] args ) { 
Scanner scanner = new Scanner ( System . in ) ; 
double length , width , height ; 
length = scanner . nextInt ( ) ; 
scanner . nextLine ( ) ; 
width = scanner . nextInt ( ) ; 
scanner . nextLine ( ) ; 
height = scanner . nextInt ( ) ; 
scanner . nextLine ( ) ; 
KKBox data = new KKBox ( length , width , height ) ; 
System . out . println ( "The volume is " + data . volume ( ) ) ; 
System . out . println ( "The volume is " + data . surfaceArea ( ) ) ; 
} 
} 
