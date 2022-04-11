public class ChineseLetter extends Letter { 
ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
public String getDate ( ) { 
return date ; 
} 
public String getReceiver ( ) { 
return receiver ; 
} 
public String getSender ( ) { 
return sender ; 
} 
public void display ( ) { 
System . out . println ( receiver + " " + title + ", 您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
} 
} 
