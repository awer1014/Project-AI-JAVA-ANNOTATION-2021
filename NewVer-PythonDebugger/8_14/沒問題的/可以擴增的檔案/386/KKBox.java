import java . util . Scanner ; 
public class KKBox { 
double length , width , height ; 
KKBox ( double l , double w , double h ) { 
this . length = l ; 
this . width = w ; 
this . height = h ; 
} 
double volume ( ) { 
return length * width * height ; 
} 
double surfaceArea ( ) { 
return 2 * length * width + width * height + height * length ; 
} 
public static void main ( String [ ] args ) { 
Scanner scan = new Scanner ( System . in ) ; 
System . out . print ( "請輸入長: " ) ; 
double l = scan . nextDouble ( ) ; 
System . out . print ( "請輸入寬: " ) ; 
double w = scan . nextDouble ( ) ; 
System . out . print ( "請輸入高: " ) ; 
double h = scan . nextDouble ( ) ; 
KKBox ex = new KKBox ( l , w , h ) ; 
System . out . println ( "體積: " + ( int ) ( ex . volume ( ) ) ) ; 
System . out . print ( "表面積: " + ( int ) ( ex . surfaceArea ( ) ) ) ; 
} 
} 
