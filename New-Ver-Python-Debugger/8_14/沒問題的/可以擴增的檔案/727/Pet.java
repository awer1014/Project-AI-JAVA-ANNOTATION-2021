import java . util . Scanner ; 
public class Pet { 
static int count ; 
String pid ; 
String name ; 
String type ; 
Pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
void speak ( ) { 
if ( this . type == "dog" ) 
System . out . println ( "汪汪" ) ; 
else 
System . out . println ( "喵喵" ) ; 
} 
void display ( ) { 
System . out . println ( "寵物種類:" + this . type ) ; 
System . out . println ( "寵物編號:" + this . pid ) ; 
System . out . println ( "寵物名稱:" + this . name ) ; 
System . out . println ( ) ; 
} 
public static void main ( String [ ] args ) { 
String Data ; 
Scanner keyboard = new Scanner ( System . in ) ; 
int T = 1 ; 
while ( T == 1 ) { 
System . out . print ( "請輸入寵物種類:" ) ; 
Data = keyboard . nextLine ( ) ; 
if ( Data == "0" ) { 
T = 0 ; 
break ; 
} 
System . out . print ( Data ) ; 
System . out . println ( ) ; 
System . out . print ( "請輸入寵物編號:" ) ; 
Data = keyboard . nextLine ( ) ; 
System . out . print ( Data ) ; 
System . out . println ( ) ; 
System . out . print ( "請輸入寵物編號:" ) ; 
Data = keyboard . nextLine ( ) ; 
System . out . print ( Data ) ; 
System . out . println ( ) ; 
System . out . print ( "請輸入飼主身份證字號:" ) ; 
} 
} 
} 
