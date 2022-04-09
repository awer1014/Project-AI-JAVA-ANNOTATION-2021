import java . util . Scanner ; 
public class Pet { 
static int count = 0 ; 
String pid , name , type ; 
Keeper z ; 
static Scanner copypid = new Scanner ( System . in ) ; 
static Scanner copyname = new Scanner ( System . in ) ; 
static Scanner copytype = new Scanner ( System . in ) ; 
static String entertype ( ) { 
System . out . println ( "請輸入寵物種類:" ) ; 
return copytype . nextLine ( ) ; 
} 
static String enterpid ( ) { 
System . out . println ( "請輸入寵物編號:" ) ; 
return copypid . nextLine ( ) ; 
} 
static String entername ( ) { 
System . out . println ( "請輸入寵物名稱:" ) ; 
return copyname . nextLine ( ) ; 
} 
Pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
Pet ( ) { 
} 
void speak ( ) { 
if ( this . type == "貓" ) 
System . out . println ( "喵喵" ) ; 
else 
System . out . println ( "汪汪" ) ; 
} 
void display ( ) { 
System . out . println ( "稱號= " + this . pid ) ; 
System . out . println ( "名稱= " + this . name ) ; 
System . out . println ( "種類= " + this . type ) ; 
} 
void settype ( String s ) { 
this . type = s ; 
} 
void setpid ( String s ) { 
this . pid = s ; 
} 
void setname ( String s ) { 
this . name = s ; 
} 
public static void mian ( String [ ] args ) { 
Pet a = new Pet ( ) ; 
Pet b = new Pet ( ) ; 
a . settype ( entertype ( ) ) ; 
a . setpid ( enterpid ( ) ) ; 
a . setname ( entername ( ) ) ; 
a . Keeper . setid ( enterid ( ) ) ; 
a . display ( ) ; 
} 
} 
