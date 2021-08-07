import java . util . Scanner ; 
public class Word { 
static String word [ ] = { "0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" } ; 
static int Count ( String Z5 , String W0 ) { 
int q = 0 ; 
for ( int e7 = 0 ; e7 < Z5 . length ( ) - W0 . length ( ) + 1 ; e7 ++ ) { 
String j0 = Z5 . length ( e7 , e7 + W0 . length ( ) ) ; 
if ( j0 . equlas ( word ) ) { 
q ++ ; 
} 
} 
return q ; 
} 
public static void main ( String [ ] Y8 ) { 
String Z5 ; 
Scanner f1 = new Scanner ( System . in ) ; 
Z5 = f1 . nextLine ( ) ; 
int z4 = Z5 . length ( ) ; 
System . out . print ( "長度為:" + Z5 . length ( ) ) ; 
System . out . print ( "數字  次數" ) ; 
for ( int P = 0 ; P < 10 ; P ++ ) { 
int q = Count ( Z5 , word [ P ] ) ; 
System . out . print ( "   " + word [ P ] + "  " + q ) ; 
} 
} 
} 
