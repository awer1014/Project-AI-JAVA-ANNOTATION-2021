import java . util . Random ; 
import java . util . ArrayList ; 
class season { 
public static void main ( String [ ] args ) { 
String data = { "春秋冬夏" } ; 
for ( int i = 0 ; i < 4 ; i ++ ) { 
int ran = new Random ( ) . nextInt ( 4 ) ; 
if ( ran == 0 ) 
System . out . print ( data . charAt ( 1 ) ) ; 
else if ( ran == 1 ) 
System . out . print ( data . charAt ( 1 ) ) ; 
else if ( ran == 2 ) 
System . out . print ( data . charAt ( 2 ) ) ; 
else if ( ran == 3 ) 
System . out . print ( data . charAt ( 3 ) ) ; 
} 
} 
} 
