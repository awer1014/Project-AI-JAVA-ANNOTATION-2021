import java . util . Scanner ; 
class Pet { 
Scanner scanner = new Scanner ( System . in ) ; 
public static int count ; 
String pid ; 
String name ; 
String type ; 
void speak ( ) { 
if ( type == "貓" ) 
System . out . println ( "喵喵" ) ; 
else 
System . out . println ( "汪汪" ) ; 
} 
void display ( ) { 
System . out . println ( "請輸入寵物種類:" + pid ) ; 
} 
Pet ( String pid , String name , String type ) { 
pid = scanner . nextLine ( ) ; 
name = scanner . nextLine ( ) ; 
type = scanner . nextLine ( ) ; 
} 
public static void main ( String [ ] args ) { 
} 
} 
