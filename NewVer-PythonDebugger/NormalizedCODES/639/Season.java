import java . util . Random ; 
public class Season { 
static String print ( String [ ] season ) { 
Random x = new Random ( ) ; 
String ans = " " ; 
for ( int i = 4 ; i > 0 ; i -- ) { 
int y = x . nextInt ( i ) ; 
ans += season [ y ] ; 
} 
} 
public static void main ( ) { 
String [ ] season = { "春" , "夏" , "秋" , "冬" } ; 
System . out . println ( print ( season ) ) ; 
} 
} 
