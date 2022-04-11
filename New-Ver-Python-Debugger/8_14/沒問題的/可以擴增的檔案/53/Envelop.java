public class Envelop { 
private String senderAddr ; 
private String receiverAddr ; 
private Letter letter ; 
Envelop ( String senderAddr , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . letter = letter ; 
} 
void display ( ) { 
System . out . println ( "From: " + senderAddr + ", " + letter . getSender ( ) + "\nTo: " + receiverAddr + ", " + letter . getReceiver ( ) + " " + letter . getTitle ( ) + "\n信件內容:" ) ; 
letter . display ( ) ; 
} 
} 
