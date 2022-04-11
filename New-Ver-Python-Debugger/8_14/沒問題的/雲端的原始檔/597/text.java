public class text { 
public static void main ( ) { 
Letter it = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "Taipei" , "Kaohsiung" , it ) ; 
env . display ( ) ; 
it . displayFull ( ) ; 
} 
} 
