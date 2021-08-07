public class ChineseLetter { 
String date ; 
public String getReceiver ( ) { 
return this . receiver ; 
} 
String sender ; 
void display ( ) { 
System . out . println ( receiver + " " + title + ", " + "您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + "敬上" ) ; 
System . out . println ( date ) ; 
} 
public String getTitle ( ) { 
return this . title ; 
} 
public String getDate ( ) { 
return this . date ; 
} 
String content ; 
ChineseLetter ( ) { 
} 
String title ; 
public String getSender ( ) { 
return this . sender ; 
} 
String receiver ; 
ChineseLetter ( String f3 , String K9 , String Z1 , String p5 , String q6 ) { 
this . title = f3 ; 
this . receiver = K9 ; 
this . content = Z1 ; 
this . sender = p5 ; 
this . date = q6 ; 
} 
} 
