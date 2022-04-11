public class Envelop { 
private static String senderAddr , receiverAddr ; 
private static Letter letter ; 
public static Letter getLetter ( ) { 
return letter ; 
} 
Envelop ( String s , String r , Letter l ) { 
senderAddr = s ; 
receiverAddr = r ; 
letter = l ; 
} 
public static void main ( String [ ] args ) { 
Letter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "Taipei" , "Kaohsiung" , lt ) ; 
env . display ( ) ; 
} 
public void display ( ) { 
System . out . println ( "From:" + this . senderAddr + ", " + ChineseLetter . getSender ( ) ) ; 
System . out . println ( "To: " + this . receiverAddr + ", " + ChineseLetter . getReceiver ( ) + " " + ChineseLetter . getTitle ( ) ) ; 
System . out . println ( "信件內容 :" ) ; 
getLetter ( ) . display ( ) ; 
} 
} 
