public class ChineseLetter extends Letter { 
public void display ( ) { 
System . out . println ( this . getReceiver ( ) + this . getTitle ( ) + ",您好" ) ; 
System . out . println ( this . getContent ( ) ) ; 
System . out . println ( this . getSender ( ) + " 敬上" ) ; 
System . out . println ( this . getDate ( ) ) ; 
} 
public String getTitle ( ) { 
return title ; 
} 
public String getSender ( ) { 
return sender ; 
} 
public String getReceiver ( ) { 
return receiver ; 
} 
public String getContent ( ) { 
return content ; 
} 
public String getDate ( ) { 
return date ; 
} 
public ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
} 
