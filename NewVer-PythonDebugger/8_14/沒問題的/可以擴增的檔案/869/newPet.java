import java . util . Scanner ; 
class Pet { 
Scanner scanner = new Scanner ( System . in ) ; 
public static int count ; 
String pid ; 
String name ; 
String type ; 
void speak ( ) { 
if ( type . equals ( "貓" ) ) 
System . out . println ( "喵喵" ) ; 
else 
System . out . println ( "汪汪" ) ; 
} 
} 
