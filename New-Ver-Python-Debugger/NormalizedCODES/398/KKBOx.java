import java . util . * ; 
class KKBOx { 
static double volume ( double length , double width , double height ) { 
double sum = 0 ; 
sum = length * width * height ; 
return sum ; 
} 
static double surfaceArea ( double length , double width , double height ) { 
double sum = 0 ; 
sum = 2 * length * width + length * height + width * height ; 
return sum ; 
} 
public static void main ( String [ ] x ) { 
double ans = volume ( 1 , 2 , 3 ) ; 
double ans1 = surfaceArea ( 1 , 2 , 3 ) ; 
System . out . println ( ans ) ; 
System . out . println ( ans1 ) ; 
} 
} 
