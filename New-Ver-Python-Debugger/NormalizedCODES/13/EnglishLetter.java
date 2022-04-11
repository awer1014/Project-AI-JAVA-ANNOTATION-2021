public class EnglishLetter extends Letter { 
String title , receiver , content , sender , date ; 
EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + title + " " + receiver ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
public String getSender ( ) { 
return this . sender ; 
} 
public String geTtitle ( ) { 
return this . title ; 
} 
public String getReceiver ( ) { 
return this . receiver ; 
} 
public String getDate ( ) { 
return this . date ; 
} 
} 
