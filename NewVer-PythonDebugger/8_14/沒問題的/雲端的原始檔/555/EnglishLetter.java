import java . util . Scanner ; 
public class EnglishLetter extends Letter { 
static Scanner cin = new Scanner ( System . in ) ; 
protected String title , receiver , content , sender , date ; 
public EnglishLetter ( ) { 
} 
public EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
protected void display ( ) { 
System . out . println ( "Dear " + title + " " + receiver + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
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
