public class Pet { 
static int count ; 
String pid = "d001" ; 
String name = "Timmy" ; 
String type ; 
Keeper keeper ; 
Pet ( ) { 
count ++ ; 
} 
static void speak ( ) { 
if ( type . equals ( "貓" ) ) { 
System . out . println ( "喵喵" ) ; 
} 
else if ( type . equals ( "狗" ) ) { 
System . out . print ( "汪汪" ) ; 
} 
} 
} 
