import java . util . Scanner ; 
public class ChineseLetter extends Letter { 
static Scanner cin = new Scanner ( System . in ) ; 
protected String title , receiver , content , sender , date ; 
public ChineseLetter ( ) { 
} 
public ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
protected void display ( ) { 
System . out . println ( receiver + " " + title + ", 您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
} 
protected String getTitle ( ) { 
return title ; 
} 
protected String getSender ( ) { 
return sender ; 
} 
protected String getReceiver ( ) { 
return receiver ; 
} 
protected String getDate ( ) { 
return date ; 
} 
} 
