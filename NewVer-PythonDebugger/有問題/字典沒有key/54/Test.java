import java . util . Arrays ; 
public class Test { 
public static void main ( ) { 
Letter [ ] list = { new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) , new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2018/11/01" ) , new EnglishLetter ( "Professor" , "Liu" , "I am sorry!" , "K. Ming" , "2018/07/05" ) , new EnglishLetter ( "Professor" , "Liu" , "I am sorry!" , "K. Ming" , "2018/07/01" ) } ; 
int min = 10000 ; 
for ( int i = list . length - 1 ; i >= 0 ; i -- ) { 
for ( int j = 0 ; j < i ; j ++ ) { 
if ( list [ j ] . compareTo ( list [ j + 1 ] ) == - 1 ) { 
Letter temp ; 
temp = list [ j ] ; 
list [ j ] = list [ j + 1 ] ; 
list [ j + 1 ] = temp ; 
} 
} 
} 
for ( int i = 0 ; i < list . length ; i ++ ) { 
list [ i ] . display ( ) ; 
System . out . println ( "\n" ) ; 
} 
} 
} 
