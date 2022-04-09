abstract public class Letter { 
protected String title , receiver , content , sender , date ; 
abstract protected void display ( ) ; 
public String getSender ( ) { 
return this . sender ; 
} 
public String getReceiver ( ) { 
return this . receiver ; 
} 
public String getDate ( ) { 
return this . date ; 
} 
} 
