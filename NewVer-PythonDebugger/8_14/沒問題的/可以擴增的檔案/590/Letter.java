abstract public class Letter { 
protected String title ; 
protected String receiver ; 
protected String content ; 
protected String sender ; 
protected String date ; 
Letter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public void display2 ( ) { 
System . out . println ( "Dear" + receiver + " " + title + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . print ( date ) ; 
} 
public void display ( ) { 
System . out . println ( receiver + " " + title + ", 您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + "   敬上" ) ; 
System . out . print ( date ) ; 
} 
} 
