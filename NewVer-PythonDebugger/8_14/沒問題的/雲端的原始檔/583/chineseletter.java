public class chineseletter { 
public String title , receiver , content , sender , date ; 
public chineseletter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
String getsender ( ) { 
return sender ; 
} 
String getreceiver ( ) { 
return receiver ; 
} 
String getdate ( ) { 
return date ; 
} 
public void display ( ) { 
System . out . println ( receiver + " " + title + ",您好!" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + "敬上" ) ; 
System . out . println ( date ) ; 
} 
} 
