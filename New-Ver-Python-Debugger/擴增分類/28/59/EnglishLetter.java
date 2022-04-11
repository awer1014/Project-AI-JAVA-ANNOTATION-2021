public class EnglishLetter extends Letter { 
protected String title ; 
protected String receiver ; 
protected String content ; 
protected String sender ; 
protected String date ; 
public void getSender ( ) { 
this . sender = sender ; 
} 
public void getReceiver ( ) { 
this . receiver = receiver ; 
} 
public void getDate ( ) { 
this . date = date ; 
} 
EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . sender = sender ; 
this . content = content ; 
this . date = date ; 
} 
} 
