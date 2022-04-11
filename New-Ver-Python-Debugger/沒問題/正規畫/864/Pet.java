public class Pet { 
static int count ; 
String pid ; 
String name ; 
String type ; 
public Pet ( String p , String n , String t ) { 
this . pid = p ; 
this . name = n ; 
this . type = t ; 
} 
void speak ( ) { 
if ( type == "貓" ) 
System . out . println ( "喵喵" ) ; 
else if ( type == "狗" ) 
System . out . println ( "汪汪" ) ; 
} 
} 
