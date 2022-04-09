import java . util . Scanner ; 
public class KKbox { 
double length , width , height ; 
KKbox ( double l , double w , double h ) { 
length = l ; 
width = w ; 
height = h ; 
} 
double volume ( double L , double W , double H ) { 
return L * W * H ; 
} 
double surfaceArea ( double L , double W , double H ) { 
return L * W + L * H + W * H * 2 ; 
} 
void println ( ) { 
System . out . println ( "體積:" + volume ( length , width , height ) ) ; 
System . out . println ( "表面積:" + surfaceArea ( length , width , height ) ) ; 
} 
public static void main ( String [ ] args ) { 
Scanner length = new Scanner ( System . in ) ; 
Scanner width = new Scanner ( System . in ) ; 
Scanner height = new Scanner ( System . in ) ; 
System . out . println ( "請輸入長:" ) ; 
double l = length . nextDouble ( ) ; 
System . out . println ( "請輸入寬:" ) ; 
double w = width . nextDouble ( ) ; 
System . out . println ( "請輸入高:" ) ; 
double h = height . nextDouble ( ) ; 
println ( ) ; 
} 
} 
