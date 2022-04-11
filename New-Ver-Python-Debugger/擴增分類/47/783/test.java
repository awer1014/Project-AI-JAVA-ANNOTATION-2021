import java . util . Scanner ; 
public class test { 
static String evaluation , stid , stname , stnumber , stnumberc , day , cover ; 
static Student st ; 
static Evaluation setEvaluation ( String evaluation ) { 
Evaluation ev = null ; 
switch ( evaluation ) { 
case "1" : 
ev = new Exam ( day , cover ) ; 
case "2" : 
ev = new Homework ( day , cover ) ; 
case "3" : 
ev = new Report ( day , cover ) ; 
} 
return ev ; 
} 
public static void main ( String [ ] args ) { 
Scanner sc = new Scanner ( System . in ) ; 
System . out . print ( "請輸入種類1(測驗),2(作業),3(心得報告)" ) ; 
evaluation = sc . nextLine ( ) ; 
System . out . print ( "請輸入學生學號：" ) ; 
stid = sc . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名：" ) ; 
stname = sc . nextLine ( ) ; 
System . out . print ( "請輸入成績：" ) ; 
stnumber = sc . nextLine ( ) ; 
System . out . print ( "請輸入成績比重" ) ; 
stnumberc = sc . nextLine ( ) ; 
st = new Student ( evaluation , stid , stname , stnumber , stnumberc ) ; 
while ( true ) { 
String day , cover ; 
if ( evaluation . equals ( "1" ) ) { 
System . out . print ( "請輸入考試日期" ) ; 
day = sc . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍" ) ; 
cover = sc . nextLine ( ) ; 
} 
if ( evaluation . equals ( "2" ) ) { 
System . out . print ( "請輸入截止日期" ) ; 
day = sc . nextLine ( ) ; 
System . out . print ( "請輸入作業描述" ) ; 
cover = sc . nextLine ( ) ; 
} 
if ( evaluation . equals ( "3" ) ) { 
System . out . print ( "請輸入報告內容" ) ; 
day = sc . nextLine ( ) ; 
System . out . print ( "請輸入參考文獻" ) ; 
cover = sc . nextLine ( ) ; 
} 
} 
Evaluation eva = setEvaluation ( evaluation ) ; 
eva . print ( ) ; 
} 
} 
