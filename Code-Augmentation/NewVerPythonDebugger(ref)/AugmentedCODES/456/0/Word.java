import java . util . Scanner ; 
public class Word { 
static int Count ( String w4 , String n3 ) { 
int Z = 0 ; 
for ( int P8 = 0 ; P8 < w4 . length ( ) - n3 . length ( ) + 1 ; P8 ++ ) { 
String P1 = w4 . length ( P8 , P8 + n3 . length ( ) ) ; 
if ( P1 . equlas ( word ) ) { 
Z ++ ; 
} 
} 
return Z ; 
} 
public static void main ( String [ ] x4 ) { 
String w4 ; 
Scanner y8 = new Scanner ( System . in ) ; 
w4 = y8 . nextLine ( ) ; 
int T2 = w4 . length ( ) ; 
System . out . print ( "長度為:" + w4 . length ( ) ) ; 
System . out . print ( "數字  次數" ) ; 
for ( int L = 0 ; L < 10 ; L ++ ) { 
int Z = Count ( w4 , word [ L ] ) ; 
System . out . print ( "   " + word [ L ] + "  " + Z ) ; 
} 
} 
static String word [ ] = { "0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" } ; 
} 
