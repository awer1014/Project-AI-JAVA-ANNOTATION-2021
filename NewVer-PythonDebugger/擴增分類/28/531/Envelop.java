public class Envelop extends Letter { 
protected String senderAddr , receiverAddr ; 
Letter letter ; 
protected Envelop ( String a , String b , String c , String e , String senderAddr , String receiverAddr , Letter letter ) { 
super ( a , b , c , e ) ; 
senderAddr = senderAddr ; 
receiverAddr = receiverAddr ; 
letter = letter ; 
} 
void read ( ) { 
System . out . print ( "請輸入寄件人地址:" ) ; 
senderAddr = sc . next ( ) ; 
System . out . print ( "請輸入收信人地址:" ) ; 
receiverAddr = sc . next ( ) ; 
} 
void display ( ) { 
System . out . print ( senderAddr + "," + sender ) ; 
System . out . print ( receiverAddr + "," + receiver + " " + title ) ; 
} 
} 
