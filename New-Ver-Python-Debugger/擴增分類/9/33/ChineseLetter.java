public class ChineseLetter { 
String title ; 
String receiver ; 
String content ; 
String sender ; 
String date ; 
ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public void display ( ) { 
System . out . println ( receiver + ' ' + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
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
public static void main ( String [ ] args ) { 
String receiver = args [ 0 ] ; 
String content = args [ 1 ] ; 
String sender = args [ 2 ] ; 
String date = args [ 3 ] ; 
ChineseLetter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
lt . display ( ) ; 
} 
} 
