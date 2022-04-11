public class Pet { 
String pid ; 
int count ; 
String name ; 
String type ; 
Keeper kp ; 
public Pet ( String pid , String name , String type , Keeper kp ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
this . kp = kp ; 
count ++ ; 
} 
void speak ( ) { 
if ( type . equals ( "貓" ) ) 
System . out . println ( "喵喵" ) ; 
else 
System . out . println ( "汪汪" ) ; 
} 
public void display ( ) { 
System . out . println ( "寵物品種：" + type ) ; 
System . out . println ( "寵物編號：" + pid ) ; 
System . out . println ( "寵物名稱：" + name ) ; 
kp . display ( ) ; 
this . speak ( ) ; 
} 
} 
