import java . util . Scanner ; 
public class Evaluation { 
public static void main ( String [ ] args ) { 
int num ; 
String id ; 
String name ; 
double grade ; 
double gp ; 
Scanner scanner = new Scanner ( System . in ) ; 
System . out . println ( "請輸入種類  1(測驗), 2(作業), 3(心得報告): " ) ; 
num = scanner . nextInt ( ) ; 
System . out . println ( "請輸入學生學號: " ) ; 
id = scanner . next ( ) ; 
System . out . println ( "請輸入學生姓名: " ) ; 
name = scanner . next ( ) ; 
System . out . println ( "請輸入成績: " ) ; 
grade = scanner . nextInt ( ) ; 
System . out . println ( "請輸入成績比重: " ) ; 
gp = scanner . nextDouble ( ) ; 
System . out . println ( "您輸入的是: " ) ; 
if ( num == 1 ) 
System . out . println ( "種類:測驗" ) ; 
else if ( num == 2 ) 
System . out . println ( "種類:作業" ) ; 
else if ( num == 3 ) 
System . out . println ( "種類:心得報告" ) ; 
else 
System . out . println ( "無" ) ; 
System . out . println ( "學生學號:" + id ) ; 
System . out . println ( "學生姓名:" + name ) ; 
System . out . println ( "成績: " + grade ) ; 
System . out . println ( "成績比重: " + gp ) ; 
} 
} 
