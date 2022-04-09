import java . util . Scanner ; 
public class Pet { 
public static int count = 0 ; 
String pid ; 
String name ; 
String type ; 
Keeper keeper ; 
public Pet ( String pid , String name , String type , Keeper keeper ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
this . keeper = keeper ; 
count ++ ; 
} 
public void speak ( String name ) { 
if ( name . equals ( "貓" ) ) 
System . out . println ( "您的寵物叫聲是：喵喵" ) ; 
else if ( name . equals ( "狗" ) ) 
System . out . println ( "您的寵物叫聲是：汪汪" ) ; 
} 
public void display ( ) { 
System . out . println ( "寵物種類:" + type ) ; 
System . out . println ( "寵物編號:" + pid ) ; 
System . out . println ( "寵物名稱:" + name ) ; 
System . out . println ( "飼主:" + keeper . display ( ) ) ; 
} 
} 
