import java . util . Scanner ; 
public class Pet { 
static int count = 0 ; 
String pid , name , type ; 
Pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
Pet ( ) { 
} 
void speak ( ) { 
if ( this . type == "貓" ) 
System . out . println ( "喵喵" ) ; 
else 
System . out . println ( "汪汪" ) ; 
} 
} 
