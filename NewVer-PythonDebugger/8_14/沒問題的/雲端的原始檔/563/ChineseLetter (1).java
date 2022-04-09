public class ChineseLetter extends Evaluation { 
public ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
public void Display ( ) { 
System . out . println ( getReceiver ( ) + " " + title + ", 您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( getSender ( ) + " 敬上" ) ; 
System . out . println ( getDate ( ) ) ; 
} 
} 
