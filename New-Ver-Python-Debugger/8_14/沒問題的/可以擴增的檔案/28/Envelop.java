public class Envelop { 
String senderAddr ; 
String receiverAddr ; 
Letter letter ; 
Envelop ( String sA , String rA , Letter letter ) { 
senderAddr = sA ; 
receiverAddr = rA ; 
letter = letter ; 
} 
String Sender = Letter . getSender ( ) ; 
String Receiver = Letter . getReceiver ( ) ; 
String Title = Letter . getTitle ( ) ; 
public void display ( ) { 
System . out . println ( "From: " + senderAddr + ", " + Sender ) ; 
System . out . println ( "To: " + receiverAddr + ", " + Receiver + " " + Title ) ; 
System . out . println ( "信件內容:" ) ; 
} 
} 
