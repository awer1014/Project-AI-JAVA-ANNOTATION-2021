public class Envelop { 
String senderAddr , receiverAddr , sender , receiver , title ; 
Letter letter ; 
public Envelop ( String sAddr , String rAddr , Letter lt ) { 
senderAddr = sAddr ; 
receiverAddr = rAddr ; 
letter = lt ; 
} 
public void Envelopdisplay ( ) { 
System . out . println ( "From: " + senderAddr + sender ) ; 
System . out . println ( "To: " + receiverAddr + receiver ) ; 
System . out . println ( "信件內容:" ) ; 
} 
} 
