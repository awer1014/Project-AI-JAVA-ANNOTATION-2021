abstract public class letter { 
private String title , receiver , content , sender , date ; 
private void letter ( String title , String receiver , String content , String sender , String date ) { 
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
abstract public void display ( ) ; 
public static void main ( String [ ] args ) { 
chineseletter it = new chineseletter ( "教授" , "王" , "很高興能夠上您的課程" , "絜哥" , "2019/1/05" ) ; 
it . display ( ) ; 
englishletter it1 = new englishletter ( "Professor" , "Wang" , "I am glad to join yuor class" , "K.J.Fan" , "2019/1/05" ) ; 
it1 . display ( ) ; 
} 
} 
