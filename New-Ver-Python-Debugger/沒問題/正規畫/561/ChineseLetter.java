public class ChineseLetter { 
String title , receiver , content , sender , date ; 
public ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
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
void Display ( ) { 
System . out . println ( getReceiver ( ) + " " + title + ", 您好" ) ; 
System . out . println ( "很高興跟您連絡" ) ; 
System . out . println ( getSender ( ) + " 敬上" ) ; 
System . out . println ( getDate ( ) ) ; 
} 
public static void main ( String [ ] args ) { 
ChineseLetter lt = new ChineseLetter ( "教授" , "朱" , "很高興跟您連絡" , "豐緒" , "2019/01/05" ) ; 
lt . Display ( ) ; 
} 
} 
