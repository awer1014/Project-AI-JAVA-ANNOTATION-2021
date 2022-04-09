import java . util . * ; 
class EnglishLetter extends Letter { 
protected String title ; 
protected String receiver ; 
protected String content ; 
protected String sender ; 
protected String date ; 
public EnglishLetter ( String titl , String receive , String conten , String sende , String dat ) { 
title = titl ; 
receiver = receive ; 
content = conten ; 
sender = sende ; 
date = dat ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + title + " " + getReceiver ( ) + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( getSender ( ) ) ; 
System . out . print ( getDate ( ) ) ; 
} 
String getSender ( ) { 
return sender ; 
} 
String getReceiver ( ) { 
return receiver ; 
} 
String getDate ( ) { 
return date ; 
} 
} 
