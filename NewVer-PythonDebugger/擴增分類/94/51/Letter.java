abstract public class Letter { 
protected String title , receiver , content , sender , date ; 
public String getSender ( ) { 
return sender ; 
} 
public String getReceiver ( ) { 
return receiver ; 
} 
public String getDate ( ) { 
return date ; 
} 
abstract public void display ( ) ; 
} 
