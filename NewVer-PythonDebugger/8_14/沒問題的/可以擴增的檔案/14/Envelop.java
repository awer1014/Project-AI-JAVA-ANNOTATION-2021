public class Envelop { 
String senderAddr , receiverAddr ; 
Letter letter ; 
Envelop ( String senderAddr , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . letter = letter ; 
} 
void display ( ) { 
System . out . println ( "From: " + senderAddr + "," + letter . getSender ( ) ) ; 
System . out . println ( "To: " + receiverAddr + "," + letter . getReceiver ( ) + " " + letter . geTtitle ( ) ) ; 
System . out . println ( "信件內容:" ) ; 
letter . display ( ) ; 
} 
public static void main ( String [ ] args ) { 
Letter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "Taipei" , "Kaohsiung" , lt ) ; 
env . display ( ) ; 
} 
} 
