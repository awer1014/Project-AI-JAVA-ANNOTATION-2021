class Pet { 
static int count ; 
String pid ; 
String name ; 
String type ; 
Pet ( ) { 
count ++ ; 
} 
Pet ( String name , String type , String pid ) { 
count ++ ; 
this . name = name ; 
this . type = type ; 
this . pid = pid ; 
} 
void speak ( Pet x ) { 
} 
void display ( ) { 
System . out . println ( "Name:" + name ) ; 
System . out . println ( "pid:" + pid ) ; 
System . out . println ( "type:" + type ) ; 
} 
} 
