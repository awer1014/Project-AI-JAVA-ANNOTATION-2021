import java . util . Random ; 
public class Exam { 
int length , width , height ; 
double surfaceArea ( ) { 
return 2 * length * width + width * height + height * length ; 
} 
public static void main ( String [ ] args ) { 
Exam t = new Exam ( ) ; 
t . length = 1 ; 
t . width = 2 ; 
t . height = 3 ; 
} 
} 
