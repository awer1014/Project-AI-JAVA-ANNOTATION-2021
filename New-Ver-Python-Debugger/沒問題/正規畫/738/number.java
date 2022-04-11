import java . util . Scanner ; 
public class number { 
int countWord ( String aStr , String word ) { 
int count = 0 ; 
for ( int i = 0 ; i < aStr . length ( ) - word . length ( ) + 1 ; i ++ ) { 
String tmp = aStr . substring ( i , i + word . length ( ) ) ; 
if ( tmp . equals ( word ) ) 
count ++ ; 
} 
return count ; 
} 
public static void main ( String [ ] args ) { 
String x ; 
Scanner type = new Scanner ( System . in ) ; 
x = type . nextLine ( ) ; 
System . out . println ( "輸入的數字長度為:" + x . length ( ) ) ; 
System . out . println ( "數字: 0 1 2 3 4 5 6 7 8 9" ) ; 
System . out . println ( "次數: " + contWord ) ; 
} 
} 
