public class Envelop { 
String senderAddr = null ; 
String receiverAddr = null ; 
String letter = null ; 
public Envelop ( String senderAddr1 , String receiverAddr1 ) { 
senderAddr = senderAddr1 ; 
receiverAddr = receiverAddr1 ; 
} 
public void display1 ( ) { 
System . out . println ( "Form :" + senderAddr ) ; 
System . out . println ( "To :" + receiverAddr ) ; 
System . out . println ( "信件內容:" ) ; 
} 
public static void main ( String [ ] args ) { 
Envelop b4 = new Envelop ( "Taipei" , "Kaohsiung" ) ; 
b4 . display1 ( ) ; 
ChineseLetter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥 " , "2019/01/05" ) ; 
lt . display ( ) ; 
EnglishLetter b2 = new EnglishLetter ( "Professor" , " Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
b2 . display ( ) ; 
} 
} 
