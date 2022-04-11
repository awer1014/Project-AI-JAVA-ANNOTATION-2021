public class ChineseLetter extends Letter { 
public ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
public void display ( ) { 
System . out . println ( getReceiver ( ) + " " + getTitle ( ) + ", 您好" ) ; 
System . out . println ( getContent ( ) ) ; 
System . out . println ( getSender ( ) + " 敬上" ) ; 
System . out . println ( getDate ( ) ) ; 
} 
} 
