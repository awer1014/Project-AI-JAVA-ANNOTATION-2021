public class EnglishLetter extends Letter { 
EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
void display ( ) { 
System . out . println ( "Dear " + receiver + " " + title + "," + "你好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
} 
} 
