public class EnglishLetter { 
String title ; 
String receiver ; 
String content ; 
String sender ; 
String date ; 
EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
void display ( ) { 
System . out . print ( "Dear " + title + receiver + "," ) ; 
System . out . print ( content ) ; 
System . out . print ( "Sincerely" ) ; 
System . out . print ( sender ) ; 
System . out . print ( date ) ; 
} 
} 
