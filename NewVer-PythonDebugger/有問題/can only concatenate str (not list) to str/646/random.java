import java . util . Random ; 
import java . util . List ; 
import java . util . ArrayList ; 
public class random { 
public static void main ( String [ ] args ) { 
String x = "春夏秋冬" ; 
ArrayList < Integer > myList1 = new ArrayList < Integer > ( ) ; 
ArrayList < Integer > myList2 = new ArrayList < Integer > ( ) ; 
for ( int i = 0 ; i < 4 ; ++ i ) { 
myList1 . add ( i + 1 ) ; 
} 
while ( myList2 . size ( ) < 4 ) { 
Integer ran = new Random ( ) . nextInt ( 4 ) ; 
if ( myList2 . indextOf ( myList1 . get ( ran ) ) < 0 ) { 
myList2 . add ( myList1 . get ( ran ) ) ; 
} 
} 
} 
} 
