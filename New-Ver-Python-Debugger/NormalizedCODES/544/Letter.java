abstract public class Letter { 
protected String title , receiver , content , sender , date ; 
public Letter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
abstract public void display ( ) ; 
public String getSender ( ) { 
return sender ; 
} 
public String getReceiver ( ) { 
return receiver ; 
} 
public String getDate ( ) { 
return date ; 
} 
public String getTitle ( ) { 
return title ; 
} 
public String getContent ( ) { 
return content ; 
} 
} 
