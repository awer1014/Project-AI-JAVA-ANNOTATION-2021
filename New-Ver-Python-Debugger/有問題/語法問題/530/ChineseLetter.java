import java . util . Scanner ; 
public class ChineseLetter extends Letter { 
public ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
public void display ( ) { 
System . out . println ( receiver + " " + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
} 
} 
