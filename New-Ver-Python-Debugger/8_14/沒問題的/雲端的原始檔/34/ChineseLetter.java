public class ChineseLetter extends Letter { 
String ChineseLetter ; 
Letter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
void display ( ) { 
System . out . println ( "王 教授, 您好" ) ; 
System . out . println ( "很高興跟您連絡" ) ; 
System . out . println ( "銘哥 敬上" ) ; 
System . out . println ( "2019/01/05" ) ; 
} 
public ChineseLetter ( String ChineseLetter ) { 
this . ChineseLetter = ChineseLetter ; 
} 
public String LT ( ) { 
return ; 
} 
} 
