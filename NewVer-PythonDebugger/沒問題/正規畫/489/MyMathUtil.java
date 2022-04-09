import java . util . StringTokenizer ; 
public class sMyMathUtil { 
public static void main ( String [ ] args ) { 
String str = "4589751245954621" ; 
int a = length ( str ) ; 
System . out . println ( a ) ; 
for ( int k = 0 ; k <= 9 ; k ++ ) { 
String str1 = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 } ; 
while ( str . indexOf ( str1 [ k ] , start ) >= 0 && start < s . length ( ) ) { 
count ++ ; 
start = str . indexOf ( str1 , start ) + str1 . length ( ) ; 
} 
} 
System . out . println ( str1 + count ) ; 
} 
} 
