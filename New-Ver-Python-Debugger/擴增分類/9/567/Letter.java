import java . util . * ; 
abstract public class Letter implements Comparable { 
String title , receiver , content , sender , date ; 
boolean same ; 
Letter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
abstract protected void display ( ) ; 
String getSender ( ) { 
return sender ; 
} 
String getReceiver ( ) { 
return receiver ; 
} 
String getDate ( ) { 
return date ; 
} 
int valueOf ( String aStr ) { 
int val = 0 ; 
for ( int i = 0 ; i < aStr . length ( ) ; i ++ ) { 
char n = aStr . charAt ( i ) ; 
val = val * 10 + n - '0' ; 
} 
return val ; 
} 
public int compareTo ( Object x ) { 
Letter o = ( Letter ) x ; 
same = false ; 
if ( valueOf ( this . date . substring ( 0 , 10 ) ) > valueOf ( o . date . substring ( 0 , 10 ) ) ) return 1 ; 
else if ( valueOf ( this . date . substring ( 0 , 10 ) ) == valueOf ( o . date . substring ( 0 , 10 ) ) ) { 
return 0 ; 
} 
else return - 1 ; 
} 
} 
