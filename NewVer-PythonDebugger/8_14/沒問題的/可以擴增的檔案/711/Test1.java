import java . util . * ; 
public class Test1 { 
static int countWord ( String aStr , String word ) { 
int count = 0 ; 
for ( int i = 0 ; i < aStr . length ( ) - word . length ( ) + 1 ; i ++ ) { 
String tmp = aStr . substring ( i , i + word . length ( ) ) ; 
if ( tmp . equals ( word ) ) 
count ++ ; 
} 
return count ; 
} 
public static void main ( String [ ] args ) { 
Scanner scanner = new Scanner ( System . in ) ; 
String string = scanner . next ( ) ; 
String string0 = "0" ; 
String string1 = "1" ; 
String string2 = "2" ; 
String string3 = "3" ; 
String string4 = "4" ; 
String string5 = "5" ; 
String string6 = "6" ; 
String string7 = "7" ; 
String string8 = "8" ; 
String string9 = "9" ; 
System . out . println ( "length is  " + string . length ( ) ) ; 
System . out . println ( "0 is  " + countWord ( string , string0 ) + " times " ) ; 
System . out . println ( "1 is  " + countWord ( string , string1 ) + " times " ) ; 
System . out . println ( "2 is  " + countWord ( string , string2 ) + " times " ) ; 
System . out . println ( "3 is  " + countWord ( string , string3 ) + " times " ) ; 
System . out . println ( "4 is  " + countWord ( string , string4 ) + " times " ) ; 
System . out . println ( "5 is  " + countWord ( string , string5 ) + " times " ) ; 
System . out . println ( "6 is  " + countWord ( string , string6 ) + " times " ) ; 
System . out . println ( "7 is  " + countWord ( string , string7 ) + " times " ) ; 
System . out . println ( "8 is  " + countWord ( string , string8 ) + " times " ) ; 
System . out . println ( "9 is  " + countWord ( string , string9 ) + " times " ) ; 
} 
} 
