abstract public class Letter implements Comparable { 
String title , receiver , content , sender , date ; 
Letter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
String getSender ( ) { 
return sender ; 
} 
String getReceiver ( ) { 
return receiver ; 
} 
String getDate ( ) { 
return date ; 
} 
abstract void display ( ) ; 
public int compareTo ( Object o ) { 
Letter obj = ( Letter ) o ; 
if ( this . getDate ( ) . compareTo ( obj . getDate ( ) ) < 0 ) { 
return 1 ; 
} 
else if ( this . getDate ( ) . compareTo ( obj . getDate ( ) ) > 0 ) { 
return - 1 ; 
} 
else { 
return 0 ; 
} 
} 
} 
