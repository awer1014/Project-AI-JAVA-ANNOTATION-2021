public class EnglishLetter extends Letter { 
public void display ( ) { 
System . out . println ( "Dear " + receiver + " " + title ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
public EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
} 
