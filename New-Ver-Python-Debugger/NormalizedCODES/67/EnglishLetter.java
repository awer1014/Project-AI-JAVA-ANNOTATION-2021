public class EnglishLetter extends Letter { 
public EnglishLetter ( String t , String r , String c , String s , String d ) { 
super ( t , r , c , s , d ) ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + title + " " + receiver + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely\n" + sender ) ; 
System . out . println ( date ) ; 
} 
} 
