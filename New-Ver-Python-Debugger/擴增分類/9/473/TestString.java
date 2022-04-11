public class TestString { 
public static void mian ( String [ ] args ) { 
int size ; 
String s = args [ 0 ] ; 
size = s . length ( ) ; 
int [ ] a = new int [ 9 ] ; 
for ( int k = 0 ; k < 9 ; k ++ ) 
a [ k ] = 0 ; 
for ( int k = 0 ; k < size ; k ++ ) { 
char c ; 
c = s . charAt ( k ) ; 
System . out . println ( c ) ; 
a [ c - '0' ] ++ ; 
} 
System . out . println ( "長度= " + size ) ; 
for ( int k = 0 ; k < 9 ; k ++ ) { 
if ( a [ k ] > 0 ) { 
System . out . println ( k + "個數= " + a [ k ] ) ; 
} 
} 
} 
} 
