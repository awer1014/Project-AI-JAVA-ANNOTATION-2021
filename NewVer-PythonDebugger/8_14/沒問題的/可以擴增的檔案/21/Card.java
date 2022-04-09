import java . util . Arrays ; 
public class Card { 
static Letter [ ] list = { new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "20190105" ) , new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "20181101" ) , new EnglishLetter ( "Professor" , "Liu" , "I am sorry!" , "K. Ming" , "20180705" ) } ; 
public static void main ( String args ) { 
Arrays . sort ( list ) ; 
for ( Letter l : list ) 
l . display ( ) ; 
} 
} 
