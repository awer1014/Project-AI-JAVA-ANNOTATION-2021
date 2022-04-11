public class EnglishLetter extends Letter { 
EnglishLetter ( String t , String r , String c , String s , String d ) { 
title = t ; 
receiver = r ; 
content = c ; 
sender = s ; 
date = d ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + this . title + " " + getReceiver ( ) + "," ) ; 
System . out . println ( this . content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( getSender ( ) ) ; 
System . out . println ( getDate ( ) ) ; 
} 
public static void main ( String [ ] args ) { 
Letter lt = new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
lt . display ( ) ; 
} 
} 
