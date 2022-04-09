abstract public class Letter { 
String title ; 
String receiver ; 
String content ; 
String sender ; 
String date , senderAddr , receiverAddr ; 
public Letter ( ) { 
} 
public Letter ( String a , String b , String c , String d , String e ) { 
title = a ; 
receiver = b ; 
content = c ; 
sender = d ; 
date = e ; 
} 
abstract public void display ( ) ; 
public void displayFull ( ) { 
System . out . println ( receiver + " " + title + "您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " " + "敬上" ) ; 
System . out . println ( date ) ; 
} 
abstract public void Display ( ) ; 
public void DisplayFull ( ) { 
System . out . println ( "Dear " + receiver + " " + title ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
String getreceiver ( ) { 
return receiver ; 
} 
String getsender ( ) { 
return sender ; 
} 
String getdate ( ) { 
return date ; 
} 
} 
