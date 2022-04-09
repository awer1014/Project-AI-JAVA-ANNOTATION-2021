import java . util . Scanner ; 
public class Pet { 
static int count ; 
String pid ; 
String name ; 
String type ; 
Pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
void speak ( ) { 
if ( this . type == "dog" ) 
System . out . println ( "汪汪" ) ; 
else 
System . out . println ( "喵喵" ) ; 
} 
} 
