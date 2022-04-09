import java . util . Scanner ; 
public class Pet { 
String strData2 ; 
String strData3 ; 
static int count ; 
static String pid ; 
static String name ; 
static String type ; 
static int amount ; 
Pet ( String pi , String n , String t ) { 
pid = pi ; 
name = n ; 
type = t ; 
amount ++ ; 
} 
static void speak ( ) { 
if ( type == "cat" ) 
System . out . println ( "喵喵" ) ; 
if ( type == "dog" ) 
System . out . println ( "汪汪" ) ; 
} 
void display ( ) { 
System . out . println ( "寵物種類: " + type ) ; 
System . out . println ( "寵物編號：" + pid ) ; 
System . out . println ( "寵物名稱：" + name ) ; 
} 
} 
