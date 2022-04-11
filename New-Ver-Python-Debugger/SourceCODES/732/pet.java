class pet { 
String pid ; 
String name ; 
String type ; 
public pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
public void speak ( ) { 
if ( type == "狗" ) { 
System . out . println ( " 汪汪 " ) ; 
} 
else { 
System . out . println ( " 喵喵 " ) ; 
} 
} 
public String gettype ( ) { 
return type ; 
} 
public String getpid ( ) { 
return pid ; 
} 
public String getname ( ) { 
return name ; 
} 
} 
