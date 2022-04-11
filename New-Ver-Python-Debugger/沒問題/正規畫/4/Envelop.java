public class Envelop extends A { 
String senderAddr ; 
String receiverAddr ; 
Letter letter ; 
Envelop ( String senderAddr , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . letter = letter ; 
} 
void display ( ) { 
System . out . println ( "From: " + senderAddr + ", " + getSender ( ) ) ; 
System . out . println ( "To: " + receiverAddr + ", " + getReceiver ( ) + " " + getTitle ( ) ) ; 
} 
} 
