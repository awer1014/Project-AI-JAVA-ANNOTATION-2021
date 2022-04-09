public class ChineseLetter { 
private String title , receiver , content , sender , date ; 
ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
String getSender ( String sender ) { 
return sender ; 
} 
String getReceiver ( String receiver ) { 
return receiver ; 
} 
String getDate ( String date ) { 
return date ; 
} 
void display ( ) { 
System . out . println ( receiver + " " + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date + "\r\n" ) ; 
} 
public static void main ( String [ ] args ) { 
Envelop env = new Envelop ( "Taipei" , "Kaohsiung" ) ; 
ChineseLetter It = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
EnglishLetter lt = new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
EnglishLetter2 lt2 = new EnglishLetter2 ( "Professor" , "Liu" , "I am sorry!" , "K. Ming" , "2018/07/05" ) ; 
System . out . println ( "第一題:" + "\r\n" ) ; 
It . display ( ) ; 
System . out . println ( "==========以上=========" ) ; 
System . out . println ( "第二題:" + "\r\n" ) ; 
lt . display ( ) ; 
System . out . println ( "==========以上=========" ) ; 
System . out . println ( "第三題:" + "\r\n" ) ; 
env . display ( ) ; 
System . out . println ( "信件內容:" + "\r\n" ) ; 
It . display ( ) ; 
lt . display ( ) ; 
System . out . println ( "==========以上=========" ) ; 
System . out . println ( "第四題:" + "\r\n" ) ; 
lt2 . display ( ) ; 
System . out . println ( "============" ) ; 
lt . display ( ) ; 
System . out . println ( "============" ) ; 
It . display ( ) ; 
System . out . println ( "==========以上=========" ) ; 
} 
} 
