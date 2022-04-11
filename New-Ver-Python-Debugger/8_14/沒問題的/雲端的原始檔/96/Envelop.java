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
System . out . println ( "From:" + senderAddr + ",銘哥" ) ; 
System . out . println ( "To: " + receiverAddr + ",王教授" ) ; 
System . out . println ( "信息內容:" ) ; 
} 
String getSenderAddr ( ) { 
return senderAddr ; 
} 
String getReceiverAddr ( ) { 
return receiverAddr ; 
} 
Letter getLetter ( ) { 
return letter ; 
} 
public static void main ( String [ ] args ) { 
Letter it = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "Taipei" , "Kaohsiung" , it ) ; 
env . display ( ) ; 
it . display ( ) ; 
} 
} 
