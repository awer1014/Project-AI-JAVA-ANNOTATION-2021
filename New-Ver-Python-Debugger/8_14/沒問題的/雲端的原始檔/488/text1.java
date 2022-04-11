import java . util . Scanner ; 
public class text1 { 
public static void main ( String [ ] args ) { 
String a ; 
int count ; 
Scanner scanner = new Scanner ( System . in ) ; 
a = scanner . next ( ) ; 
count = a . length ( ) ; 
System . out . println ( "輸入的數字長度為" + count ) ; 
System . out . println ( "數字    次數" ) ; 
int x = 0 ; 
int i = 0 ; 
for ( ; i < 10 ; i ++ ) { 
char word = a . charAt ( i ) ; 
String tmp = a . substring ( i , word ) ; 
if ( tmp . equals ( i ) ) 
x ++ ; 
if ( x >= 10 ) 
System . out . println ( " " + i + "     " + x ) ; 
else 
System . out . println ( " " + i + "      " + x ) ; 
} 
} 
} 
