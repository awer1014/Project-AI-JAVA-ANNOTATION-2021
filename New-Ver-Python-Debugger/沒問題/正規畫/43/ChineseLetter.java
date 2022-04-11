public class ChineseLetter extends Letter { 
private static String title , receiver , content , sender , date ; 
public static String getSender ( ) { 
return sender ; 
} 
public static String getReceiver ( ) { 
return receiver ; 
} 
public static String getDate ( ) { 
return date ; 
} 
public static String getTitle ( ) { 
return title ; 
} 
ChineseLetter ( String t , String r , String c , String s , String d ) { 
title = t ; 
receiver = r ; 
content = c ; 
sender = s ; 
date = d ; 
} 
public void display ( ) { 
System . out . println ( getReceiver ( ) + " " + getTitle ( ) + ", 您好" ) ; 
System . out . println ( this . content ) ; 
System . out . println ( getSender ( ) + " 敬上" ) ; 
System . out . println ( getDate ( ) ) ; 
} 
public static void main ( String [ ] args ) { 
Letter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
lt . display ( ) ; 
} 
} 
