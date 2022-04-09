import java . util . * ; 
public class main { 
public static void main ( String [ ] args ) { 
ChineseLetter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "Taipei" , "銘哥" , "王 教授" , "Kaohsiung" , lt ) ; 
env . display ( ) ; 
lt . display ( ) ; 
EnglishLetter Elt = new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
Elt . display ( ) ; 
} 
} 
