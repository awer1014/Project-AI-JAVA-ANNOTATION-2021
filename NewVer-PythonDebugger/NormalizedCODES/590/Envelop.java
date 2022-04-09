public class Envelop extends Letter { 
String senderAddr ; 
String receiverAddr ; 
Letter letter ; 
Envelop ( String senderAddr , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . letter = letter ; 
} 
String getsenderAddr ( ) { 
return senderAddr ; 
} 
String getreceiverAddr ( ) ; 
{ 
return receiverAddr ; 
} 
String getletter ( ) { 
return letter ; 
} 
} 
