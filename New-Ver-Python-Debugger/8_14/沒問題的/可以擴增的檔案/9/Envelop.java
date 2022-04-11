public class Envelop { 
String senderAddr , receiverAddr , sender , receiver , title ; 
Letter letter ; 
public Envelop ( String sAddr , String rAddr , Letter lt ) { 
senderAddr = sAddr ; 
receiverAddr = rAddr ; 
letter = lt ; 
} 
public void Envelopdisplay ( ) { 
System . out . println ( "From: " + senderAddr + lt . getSender ( ) ) ; 
System . out . println ( "To: " + receiverAddr + lt . getReceiver ( ) ) ; 
System . out . println ( "信件內容:" + lt . getContent ( ) ) ; 
} 
} 
