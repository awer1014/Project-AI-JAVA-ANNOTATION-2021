import java . util . Scanner ; 
public class Evaluation { 
public static void main ( ) { 
String name ; 
double number , grade , weight ; 
int n ; 
Scanner scan = new Scanner ( System . in ) ; 
System . out . println ( "請輸入種類 1.測驗 2.作業 3.心得報告 :" ) ; 
n = scan . nextInt ( ) ; 
System . out . println ( "請輸入學生學號 :" ) ; 
number = scan . nextDouble ( ) ; 
System . out . println ( "請輸入學生姓名 :" ) ; 
name = scan . next ( ) ; 
System . out . println ( "請輸入成績 :" ) ; 
grade = scan . nextDouble ( ) ; 
System . out . println ( "請輸入成績比重 :" ) ; 
weight = scan . nextDouble ( ) ; 
if ( n == 1 ) { 
Evaluation eva = new Exam ( ) ; 
} 
else if ( n == 2 ) { 
Evaluation eva = new HomeWork ( ) ; 
} 
else { 
Evaluation eva = new Report ( ) ; 
} 
} 
} 
