abstract class Letter { 
String title ; 
String receiver ; 
String content ; 
String sender ; 
String date ; 
public String getSender ( ) { 
return this . sender ; 
} 
public String getTitle ( ) { 
return this . title ; 
} 
public String getReceiver ( ) { 
return this . receiver ; 
} 
public String getDate ( ) { 
return this . date ; 
} 
abstract void display ( ) ; 
} 
