public class Letter { 
public static void main ( String [ ] args ) { 
Evaluation C = null ; 
Evaluation E = null ; 
C = new ChineseLetter ( "教授" , "朱" , "很高興跟您連絡" , "豐緒" , "2019/01/05" ) ; 
E = new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
C . Display ( ) ; 
E . Display ( ) ; 
} 
} 
