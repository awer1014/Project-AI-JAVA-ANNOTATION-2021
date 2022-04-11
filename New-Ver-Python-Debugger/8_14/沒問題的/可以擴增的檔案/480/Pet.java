public class Pet { 
static int count ; 
String pid ; 
String name ; 
String type ; 
Pet ( String t , String p , String n ) { 
type = t ; 
name = n ; 
pid = p ; 
} 
void speak ( ) { 
if ( type == "狗" ) 
System . out . println ( "您的寵物叫聲是：汪汪\r\n" ) ; 
else 
System . out . println ( "您的寵物叫聲是：喵喵\r\n" ) ; 
} 
public void display ( ) { 
System . out . println ( "寵物種類：" + type ) ; 
System . out . println ( "寵物編號：" + pid ) ; 
System . out . println ( "寵物名稱：" + name ) ; 
} 
} 
