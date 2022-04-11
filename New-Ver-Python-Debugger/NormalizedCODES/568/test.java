public class test { 
public static void display ( ChineseLetter lt ) { 
String a = lt . getReceiver ( ) + " " + lt . title + "," + "您好" ; 
String b = lt . content ; 
String c = lt . getSender ( ) + " " + "敬上" ; 
String d = lt . getDate ( ) ; 
System . out . println ( a ) ; 
System . out . println ( b ) ; 
System . out . println ( c ) ; 
System . out . println ( d ) ; 
} 
public static void main ( String [ ] args ) { 
ChineseLetter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
display ( lt ) ; 
} 
} 
