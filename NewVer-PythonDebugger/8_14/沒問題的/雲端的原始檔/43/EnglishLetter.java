public class EnglishLetter extends Letter { 
private static String title , receiver , content , sender , date ; 
public static String getSender ( ) { 
return sender ; 
} 
public static String getReceiver ( ) { 
return receiver ; 
} 
public static String getDate ( ) { 
return date ; 
} 
public static String getTitle ( ) { 
return title ; 
} 
EnglishLetter ( String t , String r , String c , String s , String d ) { 
title = t ; 
receiver = r ; 
content = c ; 
sender = s ; 
date = d ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + getTitle ( ) + " " + getReceiver ( ) + "," ) ; 
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
