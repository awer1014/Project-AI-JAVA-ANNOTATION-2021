public class ChineseLetter extends Letter { 
public ChineseLetter ( String t , String r , String c , String s , String d ) { 
super ( t , r , c , s , d ) ; 
} 
public void display ( ) { 
System . out . println ( receiver + title + ", 您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
} 
