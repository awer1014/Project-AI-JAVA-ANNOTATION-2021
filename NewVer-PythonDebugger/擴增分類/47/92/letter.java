public abstract class letter { 
String title , receiver , content , sender , data ; 
public letter ( String title , String receiver , String content , String sender , String data ) { 
title = title ; 
receiver = receiver ; 
content = content ; 
sender = sender ; 
data = data ; 
} 
String getitle ( ) { 
return title ; 
} 
void setTitle ( String title ) { 
title = title ; 
} 
String getreceiver ( ) { 
return receiver ; 
} 
void setReceiver ( String receiver ) { 
receiver = receiver ; 
} 
String getcontent ( ) { 
return content ; 
} 
void setcontent ( ) { 
content = content ; 
} 
String getsender ( ) { 
return sender ; 
} 
void setsender ( ) { 
sender = sender ; 
} 
String getdata ( ) { 
return data ; 
} 
void setdata ( ) { 
data = data ; 
} 
public void display ( ) { 
System . out . println ( "title" + title ) ; 
System . out . println ( "receiver" + receiver ) ; 
System . out . println ( "content" + content ) ; 
System . out . println ( "sender" + sender ) ; 
System . out . println ( "data" + data ) ; 
} 
} 
