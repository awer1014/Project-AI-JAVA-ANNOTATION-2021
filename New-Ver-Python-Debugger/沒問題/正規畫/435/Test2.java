public class Test2 { 
int wordCount ( String aString , String word ) { 
int temp = 0 ; 
for ( int i = 0 ; i < aString . length ( ) - word . length ( ) + 1 ; i ++ ) { 
String tmp = aString . substring ( i , i + word . length ( ) ) ; 
if ( tmp . equals ( word ) ) 
temp ++ ; 
} 
return temp ; 
} 
public static void main ( String [ ] args ) { 
String aString = args [ 0 ] ; 
String word = args [ 1 ] ; 
System . out . println ( "數字0出現次數:" + temp + "數字1出現次數:" + temp + "數字2出現次數:" + temp + "數字3出現次數:" + temp + "數字4出現次數:" + temp + "數字5出現次數:" + temp + "數字6出現次數:" + temp + "數字7出現次數:" + temp + "數字8出現次數:" + temp + "數字9出現次數:" + temp ) ; 
} 
} 
