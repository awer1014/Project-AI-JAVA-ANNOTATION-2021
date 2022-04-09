public class Envelop { 
public String senderAddr ; 
public String receiverAddr ; 
public Letter letter ; 
public Envelop ( String senderAddr , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . letter = letter ; 
} 
public void display ( ) { 
System . out . println ( "From " + senderAddr + " " + letter . getSender ( ) ) ; 
System . out . println ( "To " + receiverAddr + " " + letter . getReceiver ( ) + " " + letter . getTitle ( ) ) ; 
System . out . println ( "信件內容:" ) ; 
} 
} 
