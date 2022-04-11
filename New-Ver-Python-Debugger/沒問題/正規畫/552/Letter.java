abstract public class Letter { 
protected String title ; 
protected String receiver ; 
protected String content ; 
protected String sender ; 
protected String date ; 
Letter lt = new ChinseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
public static void main ( String [ ] args ) { 
lt . display ( ) ; 
} 
public void display ( ) { 
System . out . println ( receiver + " " + title + ", 你好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + "敬上" ) ; 
System . out . println ( date ) ; 
} 
} 
