import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
Scanner scan = new Scanner ( System . in ) ; 
boolean b = true ; 
while ( b ) { 
System . out . print ( "請輸入種類 1(Exam),2(Homework),3(Report) : " ) ; 
String t = scan . nextLine ( ) ; 
if ( t . equals ( "0" ) ) { 
b = false ; 
break ; 
} 
System . out . print ( "請輸入學生學號 : " ) ; 
String s = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名 : " ) ; 
String n = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績 : " ) ; 
String g = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重 : " ) ; 
String p = scan . nextLine ( ) ; 
System . out . print ( "請輸入考試日期 : " ) ; 
String d = scan . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍 : " ) ; 
String r = scan . nextLine ( ) ; 
EvalutionE e ; 
System . out . print ( "請輸入學生學號 : " ) ; 
String ss = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名 : " ) ; 
String nn = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績 : " ) ; 
String gg = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重 : " ) ; 
String pp = scan . nextLine ( ) ; 
System . out . print ( "請輸入截止日期: " ) ; 
String dl = scan . nextLine ( ) ; 
System . out . print ( "請輸入作業描述 : " ) ; 
String dis = scan . nextLine ( ) ; 
EvalutionH h ; 
System . out . print ( "請輸入學生學號 : " ) ; 
String sss = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名 : " ) ; 
String nnn = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績 : " ) ; 
String ggg = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重 : " ) ; 
String ppp = scan . nextLine ( ) ; 
System . out . print ( "請輸入報告內容 : " ) ; 
String c = scan . nextLine ( ) ; 
System . out . print ( "請輸入參考文獻 : " ) ; 
String l = scan . nextLine ( ) ; 
EvalutionR x ; 
if ( t . equals ( "1" ) ) 
e = new Exam ( s , n , d , r , g , p ) ; 
else if ( t . equals ( "2" ) ) 
h = new Homework ( s , n , g , p , dl , dis ) ; 
else if ( t . equals ( "3" ) ) 
x = new Report ( s , n , g , p , c , l ) ; 
System . out . println ( "您輸入的是 : " ) ; 
e . display ( ) ; 
} 
} 
} 
