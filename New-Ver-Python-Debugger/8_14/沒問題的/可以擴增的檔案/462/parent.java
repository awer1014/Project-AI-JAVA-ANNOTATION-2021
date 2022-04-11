public class parent { 
static int count ; 
String pay ; 
String name ; 
String type ; 
void speak ( ) { 
{ 
if ( type == "貓" ) 
System . out . println ( "喵喵" ) ; 
if ( type == "狗" ) 
System . out . println ( "汪汪" ) ; 
} 
} 
void display ( ) { 
System . out . println ( this . pay ) ; 
System . out . println ( this . name ) ; 
System . out . println ( this . type ) ; 
} 
parent ( String pay , String name , String type ) { 
this . pid = pay ; 
this . name = name ; 
this . type = type ; 
} 
} 
public class Keeper { 
String id ; 
String tel ; 
void display ( ) { 
System . out . println ( this . id ) ; 
System . out . println ( this . tel ) ; 
} 
Keeper ( String id , String tel ) { 
this . id = id ; 
this . tel = tel ; 
} 
} 
