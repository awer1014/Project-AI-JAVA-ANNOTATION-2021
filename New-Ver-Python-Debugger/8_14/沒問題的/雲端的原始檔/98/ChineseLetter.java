import java . util . * ; 
public class ChineseLetter { 
String title ; 
String receiver ; 
String content ; 
String sender ; 
String date ; 
private ChineseLetter ( ) { 
} 
public ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
static String getSender ( ) { 
Scanner sc = new Scanner ( System . in ) ; 
String s = sc . nextLine ( ) ; 
return s ; 
} 
static String getReceiver ( ) { 
Scanner sc = new Scanner ( System . in ) ; 
String s = sc . nextLine ( ) ; 
return s ; 
} 
static String getDate ( ) { 
Scanner sc = new Scanner ( System . in ) ; 
String s = sc . nextLine ( ) ; 
return s ; 
} 
static String getTitle ( ) { 
Scanner sc = new Scanner ( System . in ) ; 
String s = sc . nextLine ( ) ; 
return s ; 
} 
static String getContent ( ) { 
Scanner sc = new Scanner ( System . in ) ; 
String s = sc . nextLine ( ) ; 
return s ; 
} 
public void display ( ) { 
System . out . println ( title + receiver + " " + "您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + "敬上" ) ; 
System . out . println ( date ) ; 
} 
public static void main ( String [ ] args ) { 
System . out . println ( "分別輸入:別稱，姓名，內容，寄件者，日期" ) ; 
ChineseLetter letter = new ChineseLetter ( ) ; 
letter . display ( ) ; 
} 
} 
