import java . util . Arrays ; 
public class Card implements Comparable { 
static Letter [ ] list = { new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "20190105" ) , new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "20181101" ) , new EnglishLetter ( "Professor" , "Liu" , "I am sorry!" , "K. Ming" , "20180705" ) } ; 
private short lists ; 
Letter letter ; 
public static String listsAsString ( short r ) { 
return list [ r ] ; 
} 
Card ( Letter letter ) { 
this . letter = letter ; 
} 
public String getDate ( ) { 
return letter . date ; 
} 
public int compareTo ( Object o ) { 
Card obj = ( Card ) o ; 
int int1 = Integer . parseInt ( this . getDate ) ; 
int int2 = Integer . parseInt ( obj . getDate ) ; 
int result = int1 - int2 ; 
} 
} 
