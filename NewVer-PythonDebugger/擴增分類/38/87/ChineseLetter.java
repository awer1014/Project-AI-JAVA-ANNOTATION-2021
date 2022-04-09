public class ChineseLetter { 
String title = null ; 
String reciver = null ; 
String content = null ; 
String sender = null ; 
String data = null ; 
public ChineseLetter ( String title1 , String reciver1 , String content1 , String sender1 , String date ) { 
title = title1 ; 
reciver = reciver1 ; 
content = content1 ; 
sender = sender1 ; 
data = date ; 
} 
public void display ( ) { 
System . out . println ( reciver + " " + title + ", 您好\r\n" + content + "\r\n" + sender + "敬上\r\n" + data ) ; 
} 
public static void main ( String [ ] args ) { 
ChineseLetter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥 " , "2019/01/05" ) ; 
lt . display ( ) ; 
} 
} 
