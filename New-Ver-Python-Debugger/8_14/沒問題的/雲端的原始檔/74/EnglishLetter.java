public class EnglishLetter { 
String title ; 
String receiver ; 
String content ; 
String sender ; 
String date ; 
public EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
String gettitle ( ) { 
return title ; 
} 
void settitle ( String a ) { 
title = a ; 
} 
String getreceiver ( ) { 
return receiver ; 
} 
void setreceiver ( String b ) { 
receiver = b ; 
} 
String getcontent ( ) { 
return content ; 
} 
void setcontent ( String c ) { 
content = c ; 
} 
String getsender ( ) { 
return sender ; 
} 
void setsender ( String d ) { 
sender = d ; 
} 
String getdata ( ) { 
return date ; 
} 
void setdate ( String e ) { 
date = e ; 
} 
void display ( ) { 
System . out . println ( "Dear " + title + " " + receiver ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
public static void main ( String [ ] args ) { 
EnglishLetter lt = new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
lt . display ( ) ; 
} 
} 
