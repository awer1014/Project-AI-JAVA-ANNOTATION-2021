import java . util . Scanner ; 
public class test { 
public static void main ( String [ ] args ) { 
Scanner input = new Scanner ( System . in ) ; 
boolean b = true ; 
while ( b ) { 
System . out . print ( "input Chinese orEnglish (輸入0為終止): " ) ; 
String ltt = input . nextLine ( ) ; 
if ( ltt . equals ( "0" ) ) { 
b = false ; 
break ; 
} 
System . out . print ( "input title:" ) ; 
String title = input . nextLine ( ) ; 
System . out . print ( "input receiver:" ) ; 
String receiver = input . nextLine ( ) ; 
System . out . print ( "input content :" ) ; 
String content = input . nextLine ( ) ; 
System . out . print ( "input sender :" ) ; 
String sender = input . nextLine ( ) ; 
System . out . print ( "input date :" ) ; 
String date = input . nextLine ( ) ; 
Letter lt ; 
if ( ltt . equals ( "Chinese" ) ) { 
lt = new ChineseLetter ( title , receiver , content , sender , date ) ; 
Envelop env = new Envelop ( senderAddr , receiverAddr , letter ) ; 
lt . display ( ) ; 
} 
else if ( ltt . equals ( "English" ) ) { 
lt = new EnglishLetter ( title , receiver , content , sender , date ) ; 
Envelop env = new Envelop ( senderAddr , receiverAddr , letter ) ; 
lt . display2 ( ) ; 
} 
else { 
lt = new non ( title , receiver , content , sender , date ) ; 
Envelop env = new Envelop ( senderAddr , receiverAddr , letter ) ; 
System . out . println ( "您可能輸入的大小寫錯誤或是拼字錯誤造成無法印出!!" ) ; 
} 
} 
} 
} 
