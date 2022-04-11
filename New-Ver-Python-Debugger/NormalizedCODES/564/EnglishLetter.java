public class EnglishLetter extends Evaluation { 
public EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
public void Display ( ) { 
System . out . println ( "Dear " + title + " " + getReceiver ( ) + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( getSender ( ) ) ; 
System . out . println ( getDate ( ) ) ; 
} 
} 
