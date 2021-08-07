import java . util . Scanner ; 
public class Word { 
static int Count ( String E8 , String r1 ) { 
int g7 = 0 ; 
for ( int R0 = 0 ; R0 < E8 . length ( ) - r1 . length ( ) + 1 ; R0 ++ ) { 
String H1 = E8 . length ( R0 , R0 + r1 . length ( ) ) ; 
if ( H1 . equlas ( word ) ) { 
g7 ++ ; 
} 
} 
return g7 ; 
} 
public static void main ( String [ ] U5 ) { 
String E8 ; 
Scanner z5 = new Scanner ( System . in ) ; 
E8 = z5 . nextLine ( ) ; 
int Z = E8 . length ( ) ; 
System . out . print ( "長度為:" + E8 . length ( ) ) ; 
System . out . print ( "數字  次數" ) ; 
for ( int L1 = 0 ; L1 < 10 ; L1 ++ ) { 
int g7 = Count ( E8 , word [ L1 ] ) ; 
System . out . print ( "   " + word [ L1 ] + "  " + g7 ) ; 
} 
} 
static String word [ ] = { "0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" } ; 
} 
