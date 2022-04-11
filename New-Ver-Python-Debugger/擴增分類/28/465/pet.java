class pet { 
private int count ; 
String pid ; 
String name ; 
String type ; 
pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
count ++ ; 
} 
int A ( ) { 
return count ; 
} 
void speak ( ) { 
if ( this . type == "貓" ) { 
System . out . println ( "喵喵" ) ; 
} 
else if ( this . type == "狗" ) { 
System . out . println ( "汪汪" ) ; 
} 
} 
void display ( ) { 
System . out . println ( "編號" + this . pid + "名稱" + this . name + "種類" + this . type ) ; 
} 
public static void main ( String [ ] args ) { 
} 
} 
