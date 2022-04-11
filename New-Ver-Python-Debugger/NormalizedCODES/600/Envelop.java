import java . util . Scanner ; 
public class Envelop { 
protected String senderAddr ; 
protected String receiverAddr ; 
Letter letter ; 
public Envelop ( String senderAddr , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . letter = letter ; 
} 
public void display ( ) { 
System . out . println ( "From: " + senderAddr + ", " ) ; 
System . out . println ( "To: " + receiverAddr + ", " ) ; 
System . out . println ( "信件內容：" ) ; 
letter . display ( ) ; 
} 
} 
