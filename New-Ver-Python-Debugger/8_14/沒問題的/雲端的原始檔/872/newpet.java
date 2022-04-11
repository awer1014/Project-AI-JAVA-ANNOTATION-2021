class pet { 
static int count ; 
String pid ; 
String name ; 
String type ; 
Keeper keeper ; 
void speak ( ) { 
if ( this . type . equals ( "貓" ) ) { 
System . out . println ( "喵喵" ) ; 
} 
else if ( this . type . equals ( "狗" ) ) { 
System . out . println ( "汪汪" ) ; 
} 
} 
} 
