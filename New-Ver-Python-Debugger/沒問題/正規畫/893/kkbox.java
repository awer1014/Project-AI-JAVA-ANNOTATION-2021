public class kkbox { 
int n ; 
int b ; 
int length ; 
int width ; 
int height ; 
double volume ( ) { 
n = length * width * height ; 
return n ; 
} 
double surfaceArea ( ) { 
b = 2 * length * width + width * height + height * length ; 
return b ; 
} 
public static void main ( String [ ] x ) { 
System . out . println ( "請輸入長:" + length ) ; 
System . out . println ( "請輸入寬:" + width ) ; 
System . out . println ( "請輸入高:" + height ) ; 
System . out . println ( "體積:" + n ) ; 
System . out . println ( "表面積:" + b ) ; 
} 
} 
