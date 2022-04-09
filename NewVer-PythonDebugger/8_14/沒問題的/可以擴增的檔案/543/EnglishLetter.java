public class EnglishLetter extends Letter { 
public EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + getTitle ( ) + " " + getReceiver ( ) + "," ) ; 
System . out . println ( getContent ( ) ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( getSender ( ) ) ; 
System . out . println ( getDate ( ) ) ; 
} 
} 
