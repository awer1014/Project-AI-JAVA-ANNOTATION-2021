import java . util . Date ; 
import java . text . * ; 
abstract public class Letter implements Comparable { 
abstract void display ( ) ; 
abstract String getSender ( ) ; 
abstract String getReceiver ( ) ; 
abstract String getTitle ( ) ; 
abstract String getDate ( ) ; 
private Date thisd , objd ; 
public void inputDateTime ( Letter obj ) { 
try { 
SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy/MM/dd" ) ; 
thisd = formatter . parse ( this . getDate ( ) ) ; 
objd = formatter . parse ( obj . getDate ( ) ) ; 
} 
catch ( ParseException e ) { 
} 
} 
public int compareTo ( Object o ) { 
Letter obj = ( Letter ) o ; 
inputDateTime ( obj ) ; 
if ( thisd . getTime ( ) < objd . getTime ( ) ) { 
return 1 ; 
} 
else if ( thisd . getTime ( ) > objd . getTime ( ) ) { 
return - 1 ; 
} 
else return 0 ; 
} 
} 
