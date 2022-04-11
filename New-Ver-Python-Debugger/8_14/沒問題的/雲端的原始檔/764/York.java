import java . util . StringTokenizer ; 
import java . util . Scanner ; 
public class York { 
static Scanner scanner = new Scanner ( System . in ) ; 
static String input = scanner . nextLine ( ) ; 
static int word [ ] = { } ; 
public static int math ( String a ) { 
int c = a . length ( ) ; 
return c ; 
} 
public static String word ( ) { 
String space = " " ; 
int count = 0 ; 
word [ 0 ] = 0 ; 
for ( int i = 0 ; i < input . length ( ) - word [ 0 ] . length ( ) + 1 ; i ++ ) { 
String check = input . substring ( i , i + word . length ( ) ) ; 
if ( check . equals ( word ) ) 
count ++ ; 
} 
System . out . println ( word + " " + count ) ; 
return space ; 
} 
public static void main ( String [ ] args ) { 
System . out . println ( "輸入的數字長度：" + math ( input ) ) ; 
System . out . println ( word ( ) ) ; 
} 
} 
