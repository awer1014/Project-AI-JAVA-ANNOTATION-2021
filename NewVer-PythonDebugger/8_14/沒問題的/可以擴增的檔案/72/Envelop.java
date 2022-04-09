public class Envelop { 
private String senderAddr , receiverAddr ; 
Envelop ( String senderAddr , String receiverAddr ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
} 
void display ( ) { 
System . out . println ( "From: " + senderAddr + ",銘哥 " ) ; 
System . out . println ( "To: " + receiverAddr + ", 王 教授" + "\r\n" ) ; 
} 
} 
