import java . util . Scanner ; 
public class EnglishLetter extends Letter { 
protected String title ; 
protected String receiver ; 
protected String content ; 
protected String sender ; 
protected String date ; 
protected Scanner sc = new Scanner ( System . in ) ; 
public EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public void display ( ) { 
System . out . println ( "Dear" + title + " " + receiver + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
} 
