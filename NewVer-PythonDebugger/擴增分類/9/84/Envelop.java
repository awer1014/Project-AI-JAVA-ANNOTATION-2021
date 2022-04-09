public class Envelop { 
public String senderAddr ; 
public String receiverAddr ; 
Letter letter ; 
Envelop ( String a , String b , Letter c ) { 
senderAddr = a ; 
receiverAddr = b ; 
letter = c ; 
} 
public void display ( ) { 
System . out . println ( "From: " + senderAddr + "," + letter . getsender ( ) ) ; 
System . out . println ( "To: " + receiverAddr + "," + letter . getreceiver ( ) + letter . getTitle ( ) ) ; 
} 
} 
