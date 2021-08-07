import java . util . Scanner ; 
public class Word { 
public static void main ( String [ ] g ) { 
String H3 ; 
Scanner C2 = new Scanner ( System . in ) ; 
H3 = C2 . nextLine ( ) ; 
int Y8 = H3 . length ( ) ; 
System . out . print ( "長度為:" + H3 . length ( ) ) ; 
System . out . print ( "數字  次數" ) ; 
for ( int l0 = 0 ; l0 < 10 ; l0 ++ ) { 
int U2 = Count ( H3 , word [ l0 ] ) ; 
System . out . print ( "   " + word [ l0 ] + "  " + U2 ) ; 
} 
} 
static int Count ( String H3 , String w8 ) { 
int U2 = 0 ; 
for ( int D8 = 0 ; D8 < H3 . length ( ) - w8 . length ( ) + 1 ; D8 ++ ) { 
String a9 = H3 . length ( D8 , D8 + w8 . length ( ) ) ; 
if ( a9 . equlas ( word ) ) { 
U2 ++ ; 
} 
} 
return U2 ; 
} 
static String word [ ] = { "0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" } ; 
} 
