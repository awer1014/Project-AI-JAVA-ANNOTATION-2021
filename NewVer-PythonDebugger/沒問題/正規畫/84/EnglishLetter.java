public class EnglishLetter extends Letter { 
public EnglishLetter ( String a , String b , String c , String d , String e ) { 
super ( a , b , c , d , e ) ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + title + " " + receiver + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
} 
