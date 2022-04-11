import java . util . * ; 
public class EnglishLetter extends Letter { 
String title ; 
String receiver ; 
String content ; 
String sender ; 
String data ; 
public EnglishLetter ( String title , String receiver , String content , String sender , String data ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . data = data ; 
} 
public String getSender ( ) { 
return sender ; 
} 
public String getReceiver ( ) { 
return receiver ; 
} 
public String getdata ( ) { 
return data ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + receiver + title ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( data ) ; 
return ; 
} 
public static void main ( String [ ] args ) { 
EnglishLetter lt = new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
lt . display ( ) ; 
} 
} 
