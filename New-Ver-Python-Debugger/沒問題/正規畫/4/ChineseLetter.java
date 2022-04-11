public class ChineseLetter { 
String title ; 
String receiver ; 
String content ; 
String sender ; 
String date ; 
ChineseLetter ( ) { 
} 
ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
void display ( ) { 
System . out . println ( receiver + " " + title + ", " + "您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + "敬上" ) ; 
System . out . println ( date ) ; 
} 
public String getSender ( ) { 
return this . sender ; 
} 
public String getTitle ( ) { 
return this . title ; 
} 
public String getReceiver ( ) { 
return this . receiver ; 
} 
public String getDate ( ) { 
return this . date ; 
} 
} 
