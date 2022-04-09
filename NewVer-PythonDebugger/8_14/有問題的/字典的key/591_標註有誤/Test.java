import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
Scanner input = new Scanner ( System . in ) ; 
while ( true ) { 
String month = input . nextLine ( ) ; 
String day = intput . nextLine ( ) ; 
if ( month == 12 || day == 0 ) break ; 
Letter l1 = new Letter ( month , day ) ; 
month = input . nextLine ( ) ; 
day = input . nextLine ( ) ; 
Letter l2 = new Letter ( month , day ) ; 
if ( l1 . compareTo ( l2 ) == 1 ) 
System . out . println ( l1 . display ( ) + ">" + l2 . display ( ) ) ; 
else if ( l1 . compareTo ( l2 ) == - 1 ) 
System . out . println ( l1 . display ( ) + "<" + l2 . display ( ) ) ; 
else 
System . out . println ( l1 . display ( ) + "=" + l2 . display ( ) ) ; 
System . out . println ( "如果輸入0就結束" ) ; 
} 
} 
} 
