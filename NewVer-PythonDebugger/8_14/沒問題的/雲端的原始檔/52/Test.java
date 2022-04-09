public class Test { 
public static void main ( String [ ] args ) { 
Letter lt = new ChineseLetter ( "同學" , "黃" , "很高興跟您連絡" , "彭" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "台北" , "桃園" , lt ) ; 
env . display ( ) ; 
} 
} 
