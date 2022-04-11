import java . util . * ; 
public class ChineseLetter { 
public String title ; 
public String receiver ; 
public String content ; 
public String sender ; 
public String date ; 
ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public static void main ( String [ ] args ) { 
ChineseLetter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
lt . display ( ) ; 
} 
void display ( ) { 
System . out . println ( getReceiver ( ) + " " + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( getSender ( ) + " 敬上" ) ; 
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
