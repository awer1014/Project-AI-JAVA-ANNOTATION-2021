import java . util . * ; 
class Evaluation { 
public static void main ( String [ ] args ) { 
Scanner kb = new Scanner ( System . in ) ; 
int kind ; 
System . out . println ( "請輸入種類 1(測驗), 2(作業), 3(心得報告)" ) ; 
kind = kb . nextInt ( ) ; 
kb . nextLine ( ) ; 
if ( kind == 1 ) { 
String studentid ; 
String name ; 
double score ; 
double proportion ; 
int date ; 
String range ; 
System . out . print ( "請輸入學號：" ) ; 
studentid = kb . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名：" ) ; 
name = kb . nextLine ( ) ; 
System . out . print ( "請輸入成績：" ) ; 
score = kb . nextDouble ( ) ; 
kb . nextLine ( ) ; 
System . out . print ( "請輸入成績比重：" ) ; 
proportion = kb . nextDouble ( ) ; 
kb . nextLine ( ) ; 
System . out . print ( "請輸入考試日期：" ) ; 
date = kb . nextInt ( ) ; 
kb . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍：" ) ; 
range = kb . nextLine ( ) ; 
System . out . print ( "============================" ) ; 
Exam Ex = new Exam ( studentid , name , score , proportion , date , range ) ; 
Ex . print ( ) ; 
} 
else if ( kind == 2 ) { 
String studentid ; 
String name ; 
double score ; 
int date ; 
System . out . print ( "請輸入學號：" ) ; 
studentid = kb . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名：" ) ; 
name = kb . nextLine ( ) ; 
System . out . print ( "請輸入成績：" ) ; 
score = kb . nextDouble ( ) ; 
kb . nextLine ( ) ; 
System . out . print ( "請輸入繳交日期：" ) ; 
date = kb . nextInt ( ) ; 
kb . nextLine ( ) ; 
System . out . print ( "============================" ) ; 
HomeWork HW = new HomeWork ( studentid , name , score , date ) ; 
HW . print ( ) ; 
} 
else { 
String studentid ; 
String name ; 
String literature ; 
double score ; 
int date ; 
String range ; 
System . out . print ( "請輸入學號：" ) ; 
studentid = kb . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名：" ) ; 
name = kb . nextLine ( ) ; 
System . out . print ( "請輸入文獻：" ) ; 
literature = kb . nextLine ( ) ; 
System . out . print ( "請輸入成績：" ) ; 
score = kb . nextDouble ( ) ; 
kb . nextLine ( ) ; 
System . out . print ( "請輸入繳交日期：" ) ; 
date = kb . nextInt ( ) ; 
kb . nextLine ( ) ; 
System . out . print ( "============================" ) ; 
Report Re = new Report ( studentid , name , literature , score , date ) ; 
Re . print ( ) ; 
} 
} 
} 
