public class Test { 
public static void main ( String [ ] args ) { 
Letter it = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "Taipei" , "Kaohsiung" , it ) ; 
env . display ( ) ; 
it . display ( ) ; 
} 
} 
