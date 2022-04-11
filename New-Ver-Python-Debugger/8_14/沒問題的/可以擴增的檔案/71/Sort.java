import java . util . Arrays ; 
public class Sort implements Comparable { 
String date ; 
static Letter [ ] list = { new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) , new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2018/11/01" ) , new EnglishLetter ( "Professor" , "Liu" , "I am sorry!" , "K. Ming" , "2018/07/05" ) } ; 
Sort ( String date ) { 
this . date = list [ 0 ] . date ; 
} 
String getDate ( ) { 
return list [ 1 ] . date ; 
} 
@ Override 
public int compareTo ( Object o ) { 
Sort obj = ( Sort ) o ; 
if ( this . getDate ( ) . compareTo ( obj . getDate ( ) ) > 0 ) return 1 ; 
else if ( this . getDate ( ) . compareTo ( obj . getDate ( ) ) < 0 ) return - 1 ; 
else return 0 ; 
} 
public static void main ( String [ ] args ) { 
Arrays . sort ( list ) ; 
} 
} 
