public class Envelop { 
String senderAddr , receiverAddr ; 
Evaluation letter ; 
public Envelop ( String senderAddr , String receiverAddr , Evaluation letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . letter = letter ; 
} 
void display ( ) { 
System . out . println ( "From: " + senderAddr ) ; 
System . out . println ( "To: " + receiverAddr ) ; 
letter . Display ( ) ; 
} 
public static void main ( String [ ] args ) { 
Evaluation C = new ChineseLetter ( "教授" , "朱" , "很高興跟您連絡" , "豐緒" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "Taipei" , "Kaohsiung" , C ) ; 
env . display ( ) ; 
} 
} 
