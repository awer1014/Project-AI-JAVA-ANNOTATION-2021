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
public static void main ( ) { 
Letter lt = new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
lt . display ( ) ; 
} 
} 
