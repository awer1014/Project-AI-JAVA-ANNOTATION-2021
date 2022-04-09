import java . util . Scanner ; 
abstract public class Evaluation { 
static Scanner keyboard = new Scanner ( System . in ) ; 
String number ; 
String name ; 
double grade ; 
double percent ; 
String Eva [ ] = { "" , "測驗" , "作業" , "心得報告" } ; 
Evaluation ( String number , String name , double grade , double percent ) { 
this . number = number ; 
this . name = name ; 
this . grade = grade ; 
this . percent = percent ; 
} 
abstract void print ( int i ) ; 
public static void main ( String [ ] args ) { 
Scanner sc = new Scanner ( System . in ) ; 
while ( true ) { 
int Eva ; 
String num , name , day , range , nei , wen , meow ; 
double grade , per ; 
Evaluation eval = null ; 
System . out . print ( "請輸入成績種類(1.測驗 2.作業 3.心得報告)：" ) ; 
Eva = sc . nextInt ( ) ; 
sc . nextLine ( ) ; 
if ( Eva == 0 ) { 
System . out . println ( "-----------------------------------" ) ; 
break ; 
} 
System . out . print ( "請輸入學生學號：" ) ; 
num = sc . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名：" ) ; 
name = sc . nextLine ( ) ; 
System . out . print ( "請輸入成績：" ) ; 
grade = sc . nextDouble ( ) ; 
System . out . print ( "請輸入成績比重：" ) ; 
per = sc . nextDouble ( ) ; 
sc . nextLine ( ) ; 
if ( Eva == 1 ) { 
System . out . print ( "請輸入考試日期：" ) ; 
day = sc . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍：" ) ; 
range = sc . nextLine ( ) ; 
eval = new Exam ( num , name , grade , per , day , range ) ; 
} 
else if ( Eva == 2 ) { 
System . out . print ( "請輸入截止日期：" ) ; 
day = sc . nextLine ( ) ; 
System . out . print ( "請輸入作業描述：" ) ; 
meow = sc . nextLine ( ) ; 
eval = new Homework ( num , name , grade , per , day , meow ) ; 
} 
else { 
System . out . print ( "請輸入報告內容：" ) ; 
nei = sc . nextLine ( ) ; 
System . out . print ( "請輸入參考文獻：" ) ; 
wen = sc . nextLine ( ) ; 
eval = new Report ( num , name , grade , per , nei , wen ) ; 
} 
System . out . println ( "-----------------------------------" ) ; 
eval . print ( Eva ) ; 
System . out . println ( "-----------------------------------" ) ; 
} 
} 
} 
