public class Test { 
public static void main ( String [ ] args ) { 
ChineseLetter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "Taipei" , "Kaohsiung" , lt ) ; 
env . display ( ) ; 
lt . display ( 1 ) ; 
Letter lts = new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
Envelop envs = new Envelop ( "Taipei" , "Kaohsiung" , lt ) ; 
envs . display ( ) ; 
lts . display ( 2 ) ; 
} 
} 
