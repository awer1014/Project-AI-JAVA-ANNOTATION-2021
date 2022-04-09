public class ChineseLetter extends Letter { 
String title , receiver , content , sender , date ; 
ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public void display ( ) { 
System . out . println ( receiver + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
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
