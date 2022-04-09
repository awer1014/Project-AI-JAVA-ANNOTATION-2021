abstract public class Letter { 
private String title ; 
private String receiver ; 
private String content ; 
private String sender ; 
private String date ; 
public Letter ( ) { 
} 
public Letter ( String a , String b , String c , String d , String e ) { 
title = a ; 
receiver = b ; 
content = c ; 
sender = d ; 
date = e ; 
} 
void display ( ) { 
System . out . println ( receiver + " " + title + "您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " " + "敬上" ) ; 
System . out . println ( date ) ; 
} 
void Display ( ) { 
System . out . println ( "Dear " + receiver + " " + title ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
} 
