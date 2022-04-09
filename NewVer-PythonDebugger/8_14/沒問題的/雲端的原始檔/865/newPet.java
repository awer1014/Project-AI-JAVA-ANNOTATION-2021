import java . util . Scanner ; 
public class Pet { 
static int count ; 
String pid ; 
String name ; 
String type ; 
Keeper keeper ; 
public void speak ( ) { 
if ( type . equals ( "貓" ) ) 
System . out . println ( "喵喵!" ) ; 
else if ( type . equals ( "狗" ) ) 
System . out . println ( "汪汪!" ) ; 
} 
} 
