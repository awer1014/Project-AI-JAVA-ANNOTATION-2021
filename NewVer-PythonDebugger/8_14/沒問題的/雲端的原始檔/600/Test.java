public class Test { 
public void main ( ) { 
Letter lt = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "Taipei" , "Kaohsiung" , lt ) ; 
env . display ( ) ; 
} 
} 
