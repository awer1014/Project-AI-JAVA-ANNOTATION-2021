import java . util . Scanner ; 
public class KKBox { 
double _length , _width , _height ; 
double total1 , total2 ; 
void getlength ( double length ) { 
_length = length ; 
} 
void getwidth ( double width ) { 
_width = width ; 
} 
void getheight ( double height ) { 
_height = height ; 
} 
double volume ( ) { 
total1 = _length * _width * _height ; 
return total1 ; 
} 
double surfaceArea ( ) { 
total2 = 2 * _length * _width + _width * _height + _height * _length ; 
return total2 ; 
} 
public static void main ( String [ ] args ) { 
double length , width , height ; 
Scanner scanner = new Scanner ( System . in ) ; 
KKBox x = new KKBox ( ) ; 
System . out . println ( "請輸入長:" ) ; 
length = scanner . nextDouble ( ) ; 
scanner . nextLine ( ) ; 
x . getlength ( length ) ; 
System . out . println ( "請輸入寬:" ) ; 
width = scanner . nextDouble ( ) ; 
scanner . nextLine ( ) ; 
x . getwidth ( width ) ; 
System . out . println ( "請輸入高:" ) ; 
height = scanner . nextDouble ( ) ; 
scanner . nextLine ( ) ; 
x . getheight ( height ) ; 
System . out . println ( "體積:" + x . volume ( ) ) ; 
System . out . println ( "表面積:" + x . surfaceArea ( ) ) ; 
} 
} 
