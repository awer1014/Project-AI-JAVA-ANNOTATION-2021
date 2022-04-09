import java . util . * ; 
public abstract class Letter { 
String title , receiver , content , sender , date ; 
int c ; 
public Letter ( String title , String receiver , String content , String sender , String date ) { 
c = 0 ; 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
for ( int k = 0 ; k < this . date . length ( ) ; k ++ ) { 
String i = "" ; 
i += date . charAt ( k ) ; 
if ( i . equals ( "/" ) ) continue ; 
c += i . charAt ( 0 ) - '0' ; 
c *= 10 ; 
} 
} 
void display ( ) { 
System . out . println ( receiver + " " + title + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
System . out . println ( c ) ; 
} 
String getSender ( ) { 
return sender ; 
} 
String getReceiver ( ) { 
return receiver ; 
} 
String getDate ( ) { 
return date ; 
} 
String getTitle ( ) { 
return title ; 
} 
} 
