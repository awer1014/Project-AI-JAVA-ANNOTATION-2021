public class EnglishLetter extends Letter { 
public EnglishLetter ( String r , String t , String c , String s , String d ) { 
super ( r , t , c , s , d ) ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + title + receiver + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
} 
