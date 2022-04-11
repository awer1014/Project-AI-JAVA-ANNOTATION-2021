public class Envelop { 
private String senderAddr , receiverAddr ; 
private Letter letter ; 
public Envelop ( String senderAddr , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . letter = letter ; 
} 
public Letter getLetterr ( ) { 
return letter ; 
} 
public void display ( ) { 
System . out . println ( "From: " + senderAddr + "," + letter . getSender ( ) ) ; 
System . out . println ( "To: " + receiverAddr + letter . getReceiver ( ) ) ; 
System . out . println ( "信件內容:" ) ; 
System . out . println ( letter . display ( ) ) ; 
} 
} 
