abstract public class Letter { 
protected String title ; 
protected String receiver ; 
protected String content ; 
protected String sender ; 
protected String date ; 
public Letter ( ) { 
} 
public Letter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
abstract void display ( ) ; 
String getSender ( ) { 
return sender ; 
} 
String getReceiver ( ) { 
return receiver ; 
} 
String getDate ( ) { 
return date ; 
} 
public static void main ( String [ ] args ) { 
String receiver = args [ 0 ] ; 
lt . display ( ) ; 
} 
abstract protected String LT ( ) ; 
} 
