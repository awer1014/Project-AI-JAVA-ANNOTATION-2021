import java . util . * ; 
public class ChineseLetter { 
String title ; 
String receiver ; 
String sender ; 
String date ; 
String content ; 
public ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public String getSender ( ) { 
return sender ; 
} 
public String getDate ( ) { 
return date ; 
} 
public String getReceiver ( ) { 
return receiver ; 
} 
public void display ( ) { 
System . out . println ( receiver + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + "敬上" ) ; 
System . out . println ( date + " " ) ; 
} 
} 
