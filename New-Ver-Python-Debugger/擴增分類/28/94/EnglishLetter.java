import java . util . * ; 
public class EnglishLetter extends Letter { 
EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + title + " " + receiver ) ; 
System . out . println ( content ) ; 
System . out . println ( sender ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( date ) ; 
} 
public static void main ( String [ ] args ) { 
EnglishLetter id = new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
} 
} 
