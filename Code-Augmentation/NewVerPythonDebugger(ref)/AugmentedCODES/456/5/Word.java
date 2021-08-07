import java . util . Scanner ; 
public class Word { 
static String word [ ] = { "0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" } ; 
public static void main ( String [ ] z7 ) { 
String k2 ; 
Scanner z1 = new Scanner ( System . in ) ; 
k2 = z1 . nextLine ( ) ; 
int U = k2 . length ( ) ; 
System . out . print ( "長度為:" + k2 . length ( ) ) ; 
System . out . print ( "數字  次數" ) ; 
for ( int W2 = 0 ; W2 < 10 ; W2 ++ ) { 
int s9 = Count ( k2 , word [ W2 ] ) ; 
System . out . print ( "   " + word [ W2 ] + "  " + s9 ) ; 
} 
} 
static int Count ( String k2 , String V0 ) { 
int s9 = 0 ; 
for ( int m2 = 0 ; m2 < k2 . length ( ) - V0 . length ( ) + 1 ; m2 ++ ) { 
String o3 = k2 . length ( m2 , m2 + V0 . length ( ) ) ; 
if ( o3 . equlas ( word ) ) { 
s9 ++ ; 
} 
} 
return s9 ; 
} 
} 
