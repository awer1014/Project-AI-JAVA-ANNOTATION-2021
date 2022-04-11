public class EnglishLetter { 
String title = null ; 
String reciver = null ; 
String content = null ; 
String sender = null ; 
String data = null ; 
public EnglishLetter ( String title2 , String reciver2 , String content2 , String sender2 , String date2 ) { 
title = title2 ; 
reciver = reciver2 ; 
content = content2 ; 
sender = sender2 ; 
data = date2 ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + title + reciver + ",\r\n" + content + "\r\n" + "Sincerely\r\n" + sender + "\r\n" + data ) ; 
} 
public static void main ( String [ ] args ) { 
EnglishLetter lt = new EnglishLetter ( "Professor" , " Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
lt . display ( ) ; 
} 
} 
