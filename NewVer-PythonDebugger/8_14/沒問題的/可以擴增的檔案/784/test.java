import java . util . Scanner ; 
public class test { 
static Scanner cin = new Scanner ( System . in ) ; 
static String num , name , eat ; 
static double grade , level ; 
public static void main ( String [ ] args ) { 
int n ; 
System . out . print ( "請輸入種類 1(測驗), 2(作業), 3(心得報告):" ) ; 
n = cin . nextInt ( ) ; 
eat = cin . nextLine ( ) ; 
System . out . print ( "請輸入學生學號:" ) ; 
num = cin . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名:" ) ; 
name = cin . nextLine ( ) ; 
System . out . print ( "請輸入成績:" ) ; 
grade = cin . nextFloat ( ) ; 
System . out . print ( "請輸入成績比重:" ) ; 
level = cin . nextFloat ( ) ; 
eat = cin . nextLine ( ) ; 
switch ( n ) { 
case 1 : 
String data , b ; 
System . out . print ( "請輸入考試日期:" ) ; 
data = cin . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍:" ) ; 
b = cin . nextLine ( ) ; 
Exam student = new Exam ( num , name , grade , level , data , b ) ; 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "總類: 測驗" ) ; 
System . out . print ( "學生學號: " ) ; 
student . numPrint ( ) ; 
System . out . print ( "學生姓名: " ) ; 
student . namePrint ( ) ; 
System . out . print ( "成績: " ) ; 
student . gradePrint ( ) ; 
System . out . print ( "成績比重: " ) ; 
student . levelPrint ( ) ; 
System . out . println ( "考試日期: " + student . data ) ; 
System . out . println ( "考試範圍: " + student . b ) ; 
break ; 
case 2 : 
String a ; 
System . out . print ( "請輸入截止日期:" ) ; 
data = cin . nextLine ( ) ; 
System . out . print ( "請輸入作業描述:" ) ; 
a = cin . nextLine ( ) ; 
HomeWork student2 = new HomeWork ( num , name , grade , level , data , a ) ; 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "總類: 作業" ) ; 
System . out . print ( "學生學號: " ) ; 
student2 . numPrint ( ) ; 
System . out . print ( "學生姓名: " ) ; 
student2 . namePrint ( ) ; 
System . out . print ( "成績: " ) ; 
student2 . gradePrint ( ) ; 
System . out . print ( "成績比重: " ) ; 
student2 . levelPrint ( ) ; 
System . out . println ( "截止日期: " + student2 . data ) ; 
System . out . println ( "作業描述: " + student2 . a ) ; 
break ; 
case 3 : 
String file , f ; 
System . out . print ( "請輸入報告內容:" ) ; 
file = cin . nextLine ( ) ; 
System . out . print ( "請輸入參考文獻:" ) ; 
f = cin . nextLine ( ) ; 
Report student3 = new Report ( num , name , grade , level , file , f ) ; 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "總類: 心得報告" ) ; 
System . out . print ( "學生學號: " ) ; 
student3 . numPrint ( ) ; 
System . out . print ( "學生姓名: " ) ; 
student3 . namePrint ( ) ; 
System . out . print ( "成績: " ) ; 
student3 . gradePrint ( ) ; 
System . out . print ( "成績比重: " ) ; 
student3 . levelPrint ( ) ; 
System . out . println ( "報告內容: " + student3 . file ) ; 
System . out . println ( "參考文獻: " + student3 . f ) ; 
break ; 
} 
} 
} 
