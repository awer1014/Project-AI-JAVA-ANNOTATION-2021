import java . util . * ; 
public class ChineseLetter { 
String title ; 
String receiver ; 
String content ; 
String sender ; 
String data ; 
public ChineseLetter ( String title , String receiver , String content , String sender , String data ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . data = data ; 
} 
public String getSender ( ) { 
return sender ; 
} 
public String getReceiver ( ) { 
return receiver ; 
} 
public String getdata ( ) { 
return data ; 
} 
void display ( ) { 
System . out . println ( receiver + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + "敬上" ) ; 
System . out . println ( data ) ; 
return ; 
} 
public static void main ( String [ ] args ) { 
ChineseLetter it = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
it . display ( ) ; 
} 
} 
