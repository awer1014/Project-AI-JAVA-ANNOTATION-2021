public class ChineseLetter extends Letter { 
public ChineseLetter ( String t , String r , String c , String s , String d ) { 
super ( t , r , c , s , d ) ; 
} 
public void display ( ) { 
System . out . println ( receiver + " " + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
} 
public static void main ( ) { 
ChineseLetter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
lt . display ( ) ; 
} 
} 
