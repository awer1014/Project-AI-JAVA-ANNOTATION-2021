import java . util . * ; 
public class EnglishLetter extends Letter { 
EnglishLetter ( String a , String b , String c , String d , String e ) { 
super ( a , b , c , d , e ) ; 
} 
public void display ( ) { 
System . out . print ( "Dear" + " " + title + " " + receiver + "\r\n" ) ; 
System . out . print ( content + "\r\n" ) ; 
System . out . print ( "Sincerely" + "\r\n" ) ; 
System . out . print ( sender + "\r\n" ) ; 
System . out . print ( date + "\r\n" ) ; 
} 
} 
