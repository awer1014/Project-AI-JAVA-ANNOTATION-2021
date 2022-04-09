abstract public class Letter { 
protected String title ; 
protected String receiver ; 
protected String content ; 
protected String sender ; 
protected String date ; 
abstract public void display ( ) ; 
public Letter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public String getContent ( ) { 
return content ; 
} 
public String getSender ( ) { 
return sender ; 
} 
public String getReceiver ( ) { 
return receiver ; 
} 
public String getDate ( ) { 
return date ; 
} 
} 
