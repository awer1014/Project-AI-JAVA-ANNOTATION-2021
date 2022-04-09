public class KKBox { 
private double length , width , height ; 
public KKBox ( double length , double width , double height ) { 
super ( ) ; 
this . length = length ; 
this . width = width ; 
this . height = height ; 
} 
public double surfaceArea ( ) { 
return surfaceArea ( length , width , height ) ; 
} 
public double Volume ( ) { 
return Volume ( length , width , height ) ; 
} 
public static double surfaceArea ( double length , double width , double height ) { 
return 2 * length * width + width * height + length * height ; 
} 
public static double Volume ( double length , double width , double height ) { 
return length * width * height ; 
} 
public static void main ( String [ ] args ) { 
KKBox cuboid = new KKBox ( 1 , 2 , 3 ) ; 
System . out . println ( cuboid . surfaceArea ( ) ) ; 
System . out . println ( cuboid . Volume ( ) ) ; 
} 
} 
