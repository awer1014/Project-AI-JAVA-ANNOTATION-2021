import java . util . * ; 
public class Envelop { 
String senderAddr ; 
String receiverAddr ; 
Letter letter ; 
Envelop ( String senderAddr , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . letter = letter ; 
} 
void display ( ) { 
System . out . print ( "From:" + senderAddr + "," + " " + letter . sender + "\r\n" ) ; 
System . out . print ( "To:" + " " + receiverAddr + " " + letter . receiver + " " + letter . title + " " + "\r\n" ) ; 
System . out . print ( "信件內容:" + "\r\n" ) ; 
letter . display ( ) ; 
} 
} 
