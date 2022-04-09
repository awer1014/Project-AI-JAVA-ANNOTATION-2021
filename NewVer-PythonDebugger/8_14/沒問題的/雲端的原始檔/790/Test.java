import java . util . * ; 
public class Test { 
static Scanner scanner = new Scanner ( System . in ) ; 
public static void main ( String [ ] args ) { 
int c = 0 ; 
String sname , sstuno ; 
double sscore , srate ; 
System . out . print ( "請輸入種類 1(測驗),2(作業),3(心得報告):" ) ; 
c = scanner . nextInt ( ) ; 
System . out . print ( "請輸入學生學號:" ) ; 
sstuno = scanner . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名:" ) ; 
sname = scanner . nextLine ( ) ; 
System . out . print ( "請輸入成績:" ) ; 
sscore = scanner . nextDouble ( ) ; 
scanner . nextLine ( ) ; 
System . out . print ( "請輸入成績比重:" ) ; 
srate = scanner . nextDouble ( ) ; 
scanner . nextLine ( ) ; 
if ( c == 1 ) { 
String ddate , rrange ; 
System . out . print ( "請輸入考試日期:" ) ; 
ddate = scanner . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍:" ) ; 
rrange = scanner . nextLine ( ) ; 
Evaluation ev = new Exam ( sstuno , sname , sscore , srate , ddate , rrange ) ; 
} 
if ( c == 2 ) { 
String endtime , work ; 
System . out . print ( "請輸入截止日期:" ) ; 
endtime = scanner . nextLine ( ) ; 
System . out . print ( "請輸入作業描述:" ) ; 
work = scanner . nextLine ( ) ; 
Evaluation ev = new Homework ( sstuno , sname , sscore , srate , endtime , work ) ; 
} 
if ( c == 3 ) { 
String content , book ; 
System . out . print ( "請輸入報告內容:" ) ; 
content = scanner . nextLine ( ) ; 
System . out . print ( "請輸入參考文獻:" ) ; 
book = scanner . nextLine ( ) ; 
Evaluation ev = new Report ( sstuno , sname , sscore , srate , content , book ) ; 
} 
} 
} 
