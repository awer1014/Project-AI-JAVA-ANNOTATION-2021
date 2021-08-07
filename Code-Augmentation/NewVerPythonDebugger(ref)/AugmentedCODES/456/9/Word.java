import java . util . Scanner ; 
public class Word { 
public static void main ( String [ ] M8 ) { 
String l5 ; 
Scanner b5 = new Scanner ( System . in ) ; 
l5 = b5 . nextLine ( ) ; 
int d3 = l5 . length ( ) ; 
System . out . print ( "長度為:" + l5 . length ( ) ) ; 
System . out . print ( "數字  次數" ) ; 
for ( int G7 = 0 ; G7 < 10 ; G7 ++ ) { 
int H4 = Count ( l5 , word [ G7 ] ) ; 
System . out . print ( "   " + word [ G7 ] + "  " + H4 ) ; 
} 
} 
static String word [ ] = { "0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" } ; 
static int Count ( String l5 , String D7 ) { 
int H4 = 0 ; 
for ( int O5 = 0 ; O5 < l5 . length ( ) - D7 . length ( ) + 1 ; O5 ++ ) { 
String Z3 = l5 . length ( O5 , O5 + D7 . length ( ) ) ; 
if ( Z3 . equlas ( word ) ) { 
H4 ++ ; 
} 
} 
return H4 ; 
} 
} 
