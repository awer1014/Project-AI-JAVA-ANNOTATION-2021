public class Envelop { 
private String senderAddr ; 
private String receiverAddr ; 
Letter letter ; 
Envelop ( ) { 
} 
Envelop ( String senderAddr , String receiverAddr , Letter letter ) { 
this . senderAddr = senderAddr ; 
this . receiverAddr = receiverAddr ; 
this . letter = letter ; 
} 
public void display ( ) { 
System . out . println ( "From:" + senderAddr + ",銘哥" ) ; 
System . out . println ( "To:" + receiverAddr + ",王 教授" ) ; 
System . out . println ( "信件內容:" ) ; 
return ; 
} 
public static void main ( String [ ] args ) { 
Letter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "Taipei" , "Kaohsiung" , lt ) ; 
env . display ( ) ; 
lt . display ( ) ; 
} 
} 
