public class ChineseLetter { 
private String title , receiver , content , sender , date ; 
ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
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
void display ( ) { 
System . out . println ( receiver + title + ", " + "您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + "敬上" ) ; 
System . out . println ( date ) ; 
} 
} 
