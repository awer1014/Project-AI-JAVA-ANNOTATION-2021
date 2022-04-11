public class EnglishLetter extends Letter { 
private String title ; 
private String receiver ; 
private String content ; 
private String sender ; 
private String date ; 
EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + title + " " + receiver + ",\n" + content + "\nSincerely\n" + sender + "\n" + date ) ; 
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
String getTitle ( ) { 
return title ; 
} 
} 
