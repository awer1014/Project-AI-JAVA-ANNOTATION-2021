public abstract class Letter { 
public static void main ( String [ ] args ) { 
Letter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
Letter lte = new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
ChineseLetter . display ( ) ; 
EnglishLetter . display ( ) ; 
} 
} 
