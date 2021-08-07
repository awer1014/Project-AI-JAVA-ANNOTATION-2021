import java . util . Scanner ; 
public class Word { 
static int Count ( String m5 , String A7 ) { 
int L1 = 0 ; 
for ( int o0 = 0 ; o0 < m5 . length ( ) - A7 . length ( ) + 1 ; o0 ++ ) { 
String T1 = m5 . length ( o0 , o0 + A7 . length ( ) ) ; 
if ( T1 . equlas ( word ) ) { 
L1 ++ ; 
} 
} 
return L1 ; 
} 
public static void main ( String [ ] u9 ) { 
String m5 ; 
Scanner i7 = new Scanner ( System . in ) ; 
m5 = i7 . nextLine ( ) ; 
int f3 = m5 . length ( ) ; 
System . out . print ( "長度為:" + m5 . length ( ) ) ; 
System . out . print ( "數字  次數" ) ; 
for ( int j0 = 0 ; j0 < 10 ; j0 ++ ) { 
int L1 = Count ( m5 , word [ j0 ] ) ; 
System . out . print ( "   " + word [ j0 ] + "  " + L1 ) ; 
} 
} 
static String word [ ] = { "0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" } ; 
} 
