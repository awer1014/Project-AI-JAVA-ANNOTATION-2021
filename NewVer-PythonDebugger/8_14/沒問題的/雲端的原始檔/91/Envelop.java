public class Envelop extends letter { 
private String senderAddr ; 
private String receiverAddr ; 
public Envelop ( String title , String receiver , String content , String sender , String data ) { 
super ( title , receiver , content , sender , data ) ; 
} 
String getsenderAddr ( ) { 
return senderAddr ; 
} 
void setsenderAddr ( ) { 
senderAddr = senderAddr ; 
} 
String getreceiverAddr ( ) { 
return receiverAddr ; 
} 
void setreceiverAddr ( ) { 
receiverAddr = receiverAddr ; 
} 
public void display ( ) { 
System . out . println ( "Envelop" + senderAddr + receiverAddr ) ; 
} 
} 
