public class KKBox { 
double length ; 
double width ; 
double height ; 
public KKBox ( double length , double width , double height ) { 
this . length = length ; 
this . width = width ; 
this . height = height ; 
} 
double volume ( ) { 
return length * width * height ; 
} 
double surfaceArea ( ) { 
return 2 * length * width + width * height + height * length ; 
} 
public static void main ( String [ ] args ) { 
double length = Double . parseDouble ( args [ 0 ] ) ; 
double width = Double . parseDouble ( args [ 1 ] ) ; 
double height = Double . parseDouble ( args [ 2 ] ) ; 
KKBox KK = new KKBox ( length , width , height ) ; 
System . out . println ( "體積:" + KK . volume ( ) ) ; 
System . out . println ( "表面積:" + KK . surfaceArea ( ) ) ; 
} 
} 
