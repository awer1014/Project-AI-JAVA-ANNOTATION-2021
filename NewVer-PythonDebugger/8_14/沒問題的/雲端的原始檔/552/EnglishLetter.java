public class EnglishLetter extends Letter { 
public EnglishLetter ( String t , String r , String c , String s , String d ) { 
title = t ; 
receiver = r ; 
content = c ; 
sender = s ; 
date = d ; 
} 
public void display ( ) { 
System . out . println ( "Dear" + receiver + " " + title + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
} 
