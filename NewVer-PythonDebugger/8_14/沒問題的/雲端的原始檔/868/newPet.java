public class Pet { 
String pid ; 
String name ; 
String type ; 
Keeper keeper ; 
public Pet ( String p , String n , String t , Keeper keeper ) { 
this . pid = p ; 
this . name = n ; 
this . type = t ; 
this . keeper = keeper ; 
} 
void speak ( ) { 
if ( type . equals ( "狗" ) ) { 
System . out . println ( "你的寵物叫聲是:汪汪" ) ; 
} 
else if ( type . equals ( "貓" ) ) { 
System . out . println ( "你的寵物叫聲是:喵喵" ) ; 
} 
} 
} 
