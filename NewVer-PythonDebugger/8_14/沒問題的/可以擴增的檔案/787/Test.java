import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
Scanner scan = new Scanner ( System . in ) ; 
boolean a = true ; 
while ( a ) { 
System . out . print ( "請輸入種類 (測驗),(作業),(心得報告)(0結束): " ) ; 
String as = scan . nextLine ( ) ; 
if ( as . equals ( "0" ) ) { 
a = false ; 
break ; 
} 
System . out . print ( "請輸入學生學號: " ) ; 
String id = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名: " ) ; 
String name = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績: " ) ; 
double grade = scan . nextDouble ( ) ; 
scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重: " ) ; 
double gradepp = scan . nextDouble ( ) ; 
scan . nextLine ( ) ; 
String d , t ; 
student s = new student ( id , name , grade , gradepp ) ; 
require r = null ; 
if ( as . equals ( "測驗" ) ) { 
System . out . println ( "輸入考試日期: " ) ; 
d = scan . nextLine ( ) ; 
System . out . println ( "輸入考試範圍: " ) ; 
t = scan . nextLine ( ) ; 
r = new Quiz ( s , as , d , t ) ; 
} 
else if ( as . equals ( "作業" ) ) { 
System . out . println ( "輸入截止日期: " ) ; 
d = scan . nextLine ( ) ; 
System . out . println ( "輸入作業描述: " ) ; 
t = scan . nextLine ( ) ; 
r = new Homework ( s , as , d , t ) ; 
} 
else if ( as . equals ( "心得報告" ) ) { 
System . out . println ( "輸入報告內容: " ) ; 
d = scan . nextLine ( ) ; 
System . out . println ( "輸入參考文獻: " ) ; 
t = scan . nextLine ( ) ; 
r = new Report ( s , as , d , t ) ; 
} 
System . out . print ( "--------------------" ) ; 
System . out . println ( "您輸入的是 : " ) ; 
if ( r != null ) { 
s . display ( ) ; 
r . display ( ) ; 
} 
System . out . print ( "--------------------" ) ; 
} 
} 
} 
