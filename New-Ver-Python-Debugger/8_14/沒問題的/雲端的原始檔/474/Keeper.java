import java . util . Scanner ; 
public class Keeper { 
String id , tel ; 
static Scanner copyid = new Scanner ( System . in ) ; 
static Scanner copytel = new Scanner ( System . in ) ; 
Keeper ( String id , String tel ) { 
this . id = id ; 
this . tel = tel ; 
} 
Keeper ( ) { 
} 
static String enterid ( ) { 
System . out . println ( "請輸入飼主身分證字號：" ) ; 
return copyid . nextLine ( ) ; 
} 
static String entertel ( ) { 
System . out . println ( "請輸入飼主電話：" ) ; 
return copytel . nextLine ( ) ; 
} 
void setid ( String id ) { 
this . id = id ; 
} 
void settel ( String tel ) { 
this . tel = tel ; 
} 
void display ( ) { 
System . out . println ( "飼主身分證字號= " + this . id ) ; 
System . out . println ( "飼主電話= " + this . tel ) ; 
} 
} 
