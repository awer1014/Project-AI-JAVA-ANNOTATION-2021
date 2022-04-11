import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
Scanner scan = new Scanner ( System . in ) ; 
boolean con = true ; 
while ( con ) { 
System . out . println ( "請輸入種類 1(測驗),2(作業),3(心得報告):" ) ; 
String kind = scan . nextLine ( ) ; 
if ( kind . equals ( "0" ) ) { 
con = false ; 
break ; 
} 
System . out . println ( "請輸入學生學號:" ) ; 
String id = scan . nextLine ( ) ; 
System . out . println ( "請輸入學生姓名:" ) ; 
String name = scan . nextLine ( ) ; 
System . out . println ( "請輸入成績:" ) ; 
String score = scan . nextLine ( ) ; 
System . out . println ( "請輸入成績比重:" ) ; 
String prop = scan . nextLine ( ) ; 
Student stu = new Student ( id , name , score , prop ) ; 
Evaluation kk = new Evaluation ( kind ) ; 
Evaluation eva = null ; 
String ask1 , ask2 ; 
if ( kind . equals ( "1" ) ) { 
System . out . println ( "請輸入考試日期:" ) ; 
ask1 = scan . nextLine ( ) ; 
System . out . println ( "請輸入考試範圍:" ) ; 
ask2 = scan . nextLine ( ) ; 
eva = new Exam ( ask1 , ask2 ) ; 
} 
else if ( kind . equals ( "2" ) ) { 
System . out . println ( "請輸入截止日期:" ) ; 
ask1 = scan . nextLine ( ) ; 
System . out . println ( "請輸入作業描述:" ) ; 
ask2 = scan . nextLine ( ) ; 
eva = new HomeWork ( ask1 , ask2 ) ; 
} 
else if ( kind . equals ( "3" ) ) { 
System . out . println ( "請輸入報告內容:" ) ; 
ask1 = scan . nextLine ( ) ; 
System . out . println ( "請輸入參考文獻:" ) ; 
ask2 = scan . nextLine ( ) ; 
eva = new Report ( ask1 , ask2 ) ; 
} 
System . out . println ( "您輸入的是: " ) ; 
if ( eva != null ) { 
kk . print ( ) ; 
stu . print ( ) ; 
eva . ask ( ) ; 
} 
System . out . print ( "--------------------" ) ; 
} 
} 
} 
