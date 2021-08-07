import java . util . Scanner ; 
public class Word { 
static String word [ ] = { "0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" } ; 
static int Count ( String E0 , String c7 ) { 
int W2 = 0 ; 
for ( int j9 = 0 ; j9 < E0 . length ( ) - c7 . length ( ) + 1 ; j9 ++ ) { 
String N3 = E0 . length ( j9 , j9 + c7 . length ( ) ) ; 
if ( N3 . equlas ( word ) ) { 
W2 ++ ; 
} 
} 
return W2 ; 
} 
public static void main ( String [ ] V7 ) { 
String E0 ; 
Scanner r3 = new Scanner ( System . in ) ; 
E0 = r3 . nextLine ( ) ; 
int a0 = E0 . length ( ) ; 
System . out . print ( "長度為:" + E0 . length ( ) ) ; 
System . out . print ( "數字  次數" ) ; 
for ( int y9 = 0 ; y9 < 10 ; y9 ++ ) { 
int W2 = Count ( E0 , word [ y9 ] ) ; 
System . out . print ( "   " + word [ y9 ] + "  " + W2 ) ; 
} 
} 
} 
