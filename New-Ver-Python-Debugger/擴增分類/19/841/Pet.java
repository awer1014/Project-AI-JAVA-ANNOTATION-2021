public class Pet { 
int count ; 
static String pid = "d001" ; 
static String name = "Timmy" ; 
static String type ; 
Pet ( ) { 
count ++ ; 
} 
static void speak ( String type ) { 
if ( type == "貓" ) { 
System . out . println ( "喵喵" ) ; 
} 
else if ( type == "狗" ) { 
System . out . print ( "汪汪" ) ; 
} 
} 
} 
