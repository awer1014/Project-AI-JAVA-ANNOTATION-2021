public class Envelop extends ChineseLetter { 
private String senderAddr , receiverAddr ; 
Letter letter ; 
private String title , receiver , content , sender , date ; 
public Envelop ( String senderAddr , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
super ( title , receiver , content , sender , date ) = letter ; 
} 
public void display ( ) { 
System . out . println ( "From:" + senderAddr + ", " + letter . getSender ( ) ) ; 
System . out . println ( "To:" + receiverAddr + ", " + letter . getReceiver ( ) + letter . getTitle ( ) ) ; 
System . out . println ( "信件內容：" ) ; 
} 
} 
