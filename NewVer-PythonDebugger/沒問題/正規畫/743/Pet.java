import java . util . Scanner ; 
public class Pet { 
private static int count ; 
private static String pid ; 
private static String name ; 
private static String type ; 
Pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
public static void speak ( ) { 
if ( type == "狗" ) { 
System . out . println ( "汪汪" ) ; 
} 
else { 
System . out . println ( "喵喵" ) ; 
} 
} 
public static String getName ( ) { 
return name ; 
} 
public static String getPid ( ) { 
return pid ; 
} 
public static String getType ( ) { 
return type ; 
} 
public static void sc ( String c ) { 
Scanner sc = new Scanner ( System . in ) ; 
c = sc . nextLine ( ) ; 
} 
public static void display ( ) { 
Scanner sc = new Scanner ( System . in ) ; 
int x = 0 ; 
do 
{ 
x = sc . nextInt ( ) ; 
} 
while ( x = 0 ) ; 
Scanner t = new Scanner ( System . in ) ; 
System . out . println ( "請輸入寵物類別:" + t . nextLine ( ) ) ; 
Scanner p = new Scanner ( System . in ) ; 
System . out . println ( "寵物編號:" + p . nextLine ( ) ) ; 
Scanner n = new Scanner ( System . in ) ; 
System . out . println ( "寵物名稱:" + n . nextLine ( ) ) ; 
Scanner i = new Scanner ( System . in ) ; 
System . out . println ( "飼主身分證字號:" + i . nextLine ( ) ) ; 
Scanner te = new Scanner ( System . in ) ; 
System . out . println ( "飼主電話：" + te . nextLine ( ) ) ; 
System . out . println ( "-------------------------------------" ) ; 
} 
public static void main ( String [ ] args ) { 
Pet d = new Pet ( "d001" , "Timmy" , "狗" ) ; 
Pet c = new Pet ( "c001" , "Catty" , "貓" ) ; 
display ( ) ; 
} 
} 
