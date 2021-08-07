import java . util . Scanner ; 
public class Word { 
public static void main ( String [ ] K3 ) { 
String D9 ; 
Scanner g2 = new Scanner ( System . in ) ; 
D9 = g2 . nextLine ( ) ; 
int e8 = D9 . length ( ) ; 
System . out . print ( "長度為:" + D9 . length ( ) ) ; 
System . out . print ( "數字  次數" ) ; 
for ( int n9 = 0 ; n9 < 10 ; n9 ++ ) { 
int B = Count ( D9 , word [ n9 ] ) ; 
System . out . print ( "   " + word [ n9 ] + "  " + B ) ; 
} 
} 
static int Count ( String D9 , String P4 ) { 
int B = 0 ; 
for ( int u = 0 ; u < D9 . length ( ) - P4 . length ( ) + 1 ; u ++ ) { 
String f6 = D9 . length ( u , u + P4 . length ( ) ) ; 
if ( f6 . equlas ( word ) ) { 
B ++ ; 
} 
} 
return B ; 
} 
static String word [ ] = { "0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" } ; 
} 
