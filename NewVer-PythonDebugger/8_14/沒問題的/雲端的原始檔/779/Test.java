import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
Scanner scan = new Scanner ( System . in ) ; 
System . out . print ( "請輸入種類 1(測驗), 2(作業), 3(心得報告) : " ) ; 
String kind = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生學號 : " ) ; 
String num = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名 : " ) ; 
String name = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績 : " ) ; 
double score = scan . nextDouble ( ) ; 
scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重 : " ) ; 
double compete = scan . nextDouble ( ) ; 
scan . nextLine ( ) ; 
Evaluation e = null ; 
if ( kind . equals ( "1" ) ) { 
System . out . print ( "請輸入考試日期 : " ) ; 
String date = scan . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍 : " ) ; 
String range = scan . nextLine ( ) ; 
e = new Exam ( num , name , score , compete , date , range ) ; 
System . out . println ( "你輸入的是 : " ) ; 
System . out . println ( "種類: 測驗" ) ; 
e . display ( ) ; 
e . play ( ) ; 
} 
if ( kind . equals ( "2" ) ) { 
System . out . print ( "請輸入截止日期 : " ) ; 
String off = scan . nextLine ( ) ; 
System . out . print ( "請輸入作業描述 : " ) ; 
String scribe = scan . nextLine ( ) ; 
e = new HomeWork ( num , name , score , compete , off , scribe ) ; 
System . out . println ( "你輸入的是 : " ) ; 
System . out . println ( "種類: 作業" ) ; 
e . display ( ) ; 
e . play ( ) ; 
} 
if ( kind . equals ( "3" ) ) { 
System . out . print ( "請輸入報告內容 : " ) ; 
String content = scan . nextLine ( ) ; 
System . out . print ( "請輸入參考文獻 : " ) ; 
String references = scan . nextLine ( ) ; 
e = new Report ( num , name , score , compete , content , references ) ; 
System . out . println ( "你輸入的是 : " ) ; 
System . out . println ( "種類: 心得報告" ) ; 
e . display ( ) ; 
e . play ( ) ; 
} 
} 
} 
