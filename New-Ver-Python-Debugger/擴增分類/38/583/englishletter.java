public class englishletter { 
public String title , receiver , content , sender , date ; 
public englishletter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
String getsender ( ) { 
return sender ; 
} 
String getreceiver ( ) { 
return receiver ; 
} 
String getdate ( ) { 
return date ; 
} 
public void display ( ) { 
System . out . println ( "Dear " + title + " " + receiver + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely!" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
public static void main ( String [ ] args ) { 
englishletter it = new englishletter ( "Professor" , "Wang" , "I am glad to join yuor class" , "K.J.Fan" , "2019/1/05" ) ; 
it . display ( ) ; 
} 
} 
