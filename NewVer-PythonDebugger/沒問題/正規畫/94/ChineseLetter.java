import java . util . * ; 
public class ChineseLetter extends Letter { 
ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
public void display ( ) { 
System . out . println ( receiver + " " + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
} 
public static void main ( String [ ] args ) { 
ChineseLetter it = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
} 
} 
