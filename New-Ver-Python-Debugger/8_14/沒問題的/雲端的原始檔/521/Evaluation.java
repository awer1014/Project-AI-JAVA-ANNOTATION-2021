import java . util . Scanner ; 
public class Evaluation { 
public static void main ( String [ ] args ) { 
String type ; 
Scanner scan = new Scanner ( System . in ) ; 
boolean b = true ; 
while ( b ) { 
System . out . print ( "請輸入種類 1(測驗),2(作業),3(心得報告) : " ) ; 
String n = scan . next ( ) ; 
int n1 = Integer . parseInt ( n ) ; 
if ( n . equals ( "0" ) ) { 
b = false ; 
break ; 
} 
System . out . println ( "請輸入學生學號 : " ) ; 
String number = scan . nextLine ( ) ; 
System . out . println ( "請輸入學生姓名 : " ) ; 
String name = scan . nextLine ( ) ; 
System . out . println ( "請輸入成績 : " ) ; 
String grade = scan . nextLine ( ) ; 
System . out . println ( "請輸入成績比重 : " ) ; 
String grade1 = scan . nextLine ( ) ; 
System . out . println ( "請輸入考試日期 : " ) ; 
String date = scan . nextLine ( ) ; 
System . out . println ( "請輸入考試範圍 : " ) ; 
String range = scan . nextLine ( ) ; 
if ( n1 == 1 ) { 
type = "測驗" ; 
} 
else if ( n1 == 2 ) { 
type = "作業" ; 
} 
else if ( n1 == 3 ) { 
type = "心得報告" ; 
} 
else 
type = "錯誤" ; 
Student std = new Student ( number , name ) ; 
Grades gra = new Grades ( grade , grade1 ) ; 
Test t = new Test ( date , range ) ; 
System . out . println ( "您輸入的是 : " ) ; 
System . out . println ( "種類 : " + type ) ; 
std . display ( ) ; 
gra . display ( ) ; 
t . display ( ) ; 
System . out . println ( "======================" ) ; 
} 
} 
} 
