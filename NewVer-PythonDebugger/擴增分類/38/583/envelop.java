public class envelop { 
public String senderAddr , receiverAddr , receiver , sender , title , content , date ; 
public envelop ( String senderAddr , String receiverAddr , chineseletter chineseletter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public void display ( ) { 
System . out . println ( "From: " + senderAddr + ", " + sender ) ; 
System . out . println ( "To: " + receiverAddr + ", " + receiver + " " + title ) ; 
System . out . println ( "信件內容:" ) ; 
System . out . println ( receiver + " " + title + ",您好!" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + "敬上" ) ; 
System . out . println ( date ) ; 
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
public static void main ( String [ ] args ) { 
chineseletter it = new chineseletter ( "教授" , "王" , "很高興能夠上您的課程" , "絜哥" , "2019/1/05" ) ; 
envelop env = new envelop ( "Taipei" , "Tainan" , it ) ; 
env . display ( ) ; 
} 
} 
