public class Envelop { 
String senderAddr , receiverAddr ; 
Letter letter ; 
public Envelop ( String senderAddr , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . letter = letter ; 
} 
void display ( ) { 
System . out . println ( "From: " + senderAddr + ", " + letter . getSender ( ) ) ; 
System . out . println ( "To: " + receiverAddr + ", " + letter . getReceiver ( ) + " " + letter . getTitle ( ) ) ; 
letter . display ( ) ; 
} 
} 
