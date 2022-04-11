import java . util . Scanner ; 
public class test { 
public static void main ( String [ ] args ) { 
Letter [ ] list = { new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) , new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2018/11/01" ) , new EnglishLetter ( "Professor" , "Liu" , "I am sorry!" , "K. Ming" , "2018/07/05" ) } ; 
Letter tmp ; 
for ( int i = 0 ; i < 3 ; i ++ ) { 
for ( int j = 0 ; j < 3 ; j ++ ) { 
if ( list [ i ] . compareTo ( list [ j ] ) == - 1 ) { 
tmp = list [ i ] ; 
list [ i ] = list [ j ] ; 
list [ j ] = tmp ; 
} 
} 
} 
for ( int i = 0 ; i < 3 ; i ++ ) { 
list [ i ] . display ( ) ; 
System . out . println ( "===========" ) ; 
} 
} 
} 
