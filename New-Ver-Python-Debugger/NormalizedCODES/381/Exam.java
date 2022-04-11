import java . util . Random ; 
public class KKBox { 
double length , width , height ; 
KKBox ( ) { 
} 
void surfaceArea ( ) { 
System . out . println ( 2 * length * width + width * height + height * length ) ; 
} 
public static void main ( String [ ] args ) { 
int length = 1 ; 
int width = 2 ; 
int eight = 3 ; 
KKBox t = new KKBox ( ) ; 
t . surfaceArea ( ) ; 
} 
} 
