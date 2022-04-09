public class EnglishLetter extends Letter { 
private String content ; 
public EnglishLetter ( ) { 
} 
public EnglishLetter ( String a , String b , String c , String d , String e ) { 
super ( a , b , c , e ) ; 
content = d ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + receiver ) ; 
System . out . print ( title + ",\n" ) ; 
System . out . println ( content + "\n" + "Scincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
} 
