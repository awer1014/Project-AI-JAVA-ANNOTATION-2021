import java . util . * ; 
class ChineseLetter extends Letter { 
protected String title ; 
protected String receiver ; 
protected String content ; 
public String sender ; 
protected String date ; 
public ChineseLetter ( String titl , String receive , String conten , String sende , String dat ) { 
title = titl ; 
receiver = receive ; 
content = conten ; 
sender = sende ; 
date = dat ; 
} 
public void display ( ) { 
System . out . println ( getReceiver ( ) + " " + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( getSender ( ) + " 敬上" ) ; 
System . out . println ( getDate ( ) ) ; 
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
