import java . util . * ; 
public class EnglishLetter implements Letter , Comparable { 
String title ; 
String receiver ; 
String sender ; 
String date ; 
String content ; 
public EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public String getSender ( ) { 
return sender ; 
} 
public String getDate ( ) { 
return date ; 
} 
public String getReceiver ( ) { 
return receiver ; 
} 
public void display ( ) { 
System . out . println ( "Dear" + title + receiver ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
public int compareTo ( Object o ) { 
Letter other = ( Letter ) o ; 
if ( this . date . compareTo ( other . getDate ( ) ) > 0 ) return 1 ; 
else return - 1 ; 
} 
} 
