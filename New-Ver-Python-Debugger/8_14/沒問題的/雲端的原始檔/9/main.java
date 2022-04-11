public class main { 
public static void main ( String [ ] args ) { 
Letter itChinese , itEnglish ; 
itChinese = new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ; 
itEnglish = new EnglishLetter ( "Professor " , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) ; 
Envelop env = new Envelop ( "Taipei" , "Kaohsiung" , itChinese ) ; 
System . out . println ( "==================以下為第三題和第一題" ) ; 
env . Envelopdisplay ( ) ; 
itChinese . display ( ) ; 
System . out . println ( "==================以下為第二題" ) ; 
itChinese . display ( ) ; 
System . out . println ( "" ) ; 
itEnglish . display ( ) ; 
} 
} 
