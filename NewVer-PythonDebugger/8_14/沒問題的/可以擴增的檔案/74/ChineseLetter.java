public class ChineseLetter { 
String title ; 
String receiver ; 
String content ; 
String sender ; 
String date ; 
public ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
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
System . out . println ( receiver + " " + title + ", 您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
} 
public static void main ( String [ ] args ) { 
ChineseLetter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
lt . display ( ) ; 
} 
} 
