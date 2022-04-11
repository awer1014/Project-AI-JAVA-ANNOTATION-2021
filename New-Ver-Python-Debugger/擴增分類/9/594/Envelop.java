import java . util . * ; 
public class Envelop { 
public String senderAddr ; 
public String receiverAddr ; 
public String sender ; 
public String receiver ; 
Letter letter ; 
Envelop ( String senderAddr , String sender , String receiver , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . sender = sender ; 
this . receiver = receiver ; 
this . letter = letter ; 
} 
public void display ( ) { 
System . out . println ( "From: " + getSenderAddr ( ) ) ; 
System . out . println ( "To: " + getReceiverAddr ( ) ) ; 
} 
String getSenderAddr ( ) { 
return senderAddr + "," + sender ; 
} 
String getReceiverAddr ( ) { 
return receiverAddr + "," + receiver ; 
} 
} 
