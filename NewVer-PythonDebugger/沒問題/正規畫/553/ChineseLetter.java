import java . util . Scanner ; 
public class ChineseLetter { 
private String title , receiver , content , sender , date ; 
static Scanner cin = new Scanner ( System . in ) ; 
public static void main ( String [ ] argc ) { 
String title , receiver , content , sender , date ; 
System . out . print ( "輸入title: " ) ; 
title = cin . nextLine ( ) ; 
System . out . print ( "輸入receiver: " ) ; 
receiver = cin . nextLine ( ) ; 
System . out . print ( "輸入content: " ) ; 
content = cin . nextLine ( ) ; 
System . out . print ( "輸入sender: " ) ; 
sender = cin . nextLine ( ) ; 
System . out . print ( "輸入date: " ) ; 
date = cin . nextLine ( ) ; 
ChineseLetter lt = new ChineseLetter ( title , receiver , content , sender , date ) ; 
lt . display ( ) ; 
} 
public ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public void display ( ) { 
System . out . println ( receiver + " " + title + ", 您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
} 
public String getSender ( ) { 
return sender ; 
} 
public String getReceiver ( ) { 
return receiver ; 
} 
public String getDate ( ) { 
return date ; 
} 
} 
