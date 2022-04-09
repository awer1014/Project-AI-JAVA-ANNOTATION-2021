import java . util . * ; 
import java . util . Arrays ; 
public class Test { 
public static void main ( String [ ] args ) { 
Letter [ ] list = { new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) , new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2018/11/01" ) , new EnglishLetter ( "Professor" , "Liu" , "I am sorry!" , "K. Ming" , "2018/07/05" ) } ; 
Arrays . sort ( list ) ; 
for ( int i = 0 ; i < list . length ; i ++ ) { 
if ( i != 0 ) 
System . out . println ( "===========" ) ; 
list [ i ] . display ( ) ; 
} 
} 
} 
