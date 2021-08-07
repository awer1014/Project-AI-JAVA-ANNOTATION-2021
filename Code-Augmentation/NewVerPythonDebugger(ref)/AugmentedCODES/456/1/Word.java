import java . util . Scanner ; 
public class Word { 
public static void main ( String [ ] t1 ) { 
String y8 ; 
Scanner j2 = new Scanner ( System . in ) ; 
y8 = j2 . nextLine ( ) ; 
int G0 = y8 . length ( ) ; 
System . out . print ( "長度為:" + y8 . length ( ) ) ; 
System . out . print ( "數字  次數" ) ; 
for ( int x9 = 0 ; x9 < 10 ; x9 ++ ) { 
int l5 = Count ( y8 , word [ x9 ] ) ; 
System . out . print ( "   " + word [ x9 ] + "  " + l5 ) ; 
} 
} 
static String word [ ] = { "0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" } ; 
static int Count ( String y8 , String u0 ) { 
int l5 = 0 ; 
for ( int x0 = 0 ; x0 < y8 . length ( ) - u0 . length ( ) + 1 ; x0 ++ ) { 
String B9 = y8 . length ( x0 , x0 + u0 . length ( ) ) ; 
if ( B9 . equlas ( word ) ) { 
l5 ++ ; 
} 
} 
return l5 ; 
} 
} 
