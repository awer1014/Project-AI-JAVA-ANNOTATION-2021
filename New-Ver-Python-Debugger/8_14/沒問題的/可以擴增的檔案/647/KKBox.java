import java . util . Scanner ; 
class KKBox { 
double length ; 
double width ; 
double height ; 
double x , y ; 
KKBox ( double length , double width , double height ) { 
this . length = length ; 
this . width = width ; 
this . height = height ; 
} 
double volume ( double x ) { 
this . length = length ; 
this . width = width ; 
this . height = height ; 
x = length * width * height ; 
return x ; 
} 
double surfaceArea ( double y ) { 
this . length = length ; 
this . width = width ; 
this . height = height ; 
y = 2 * length * width + width * height + height * length ; 
return y ; 
} 
void list ( ) { 
System . out . println ( "請輸入長:" + length ) ; 
System . out . println ( "請輸入寬:" + width ) ; 
System . out . println ( "請輸入高:" + height ) ; 
System . out . println ( "體積:" + x ) ; 
System . out . println ( "表面積:" + y ) ; 
} 
public static void main ( String [ ] args ) { 
double length ; 
double width ; 
double height ; 
double x , y ; 
Scanner x = new Scanner ( System . in ) ; 
System . out . println ( "Input Name:" ) ; 
x = length * width * height ; 
y = 2 * length * width + width * height + height * length ; 
length = Double . parseDouble ( args [ 0 ] ) ; 
width = Double . parseDouble ( args [ 1 ] ) ; 
height = Double . parseDouble ( args [ 2 ] ) ; 
System . out . println ( "請輸入長:" + length ) ; 
System . out . println ( "請輸入寬:" + width ) ; 
System . out . println ( "請輸入高:" + height ) ; 
KKBox z = new KKBox ( length , width , height ) ; 
z . volume ( x ) ; 
z . surfaceArea ( y ) ; 
System . out . println ( "體積:" + x ) ; 
System . out . println ( "表面積:" + y ) ; 
} 
} 
