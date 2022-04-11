import java . util . Scanner ; 
public class Text { 
public static void main ( String [ ] args ) { 
int n ; 
String s ; 
Double d ; 
Scanner cin = new Scanner ( System . in ) ; 
System . out . println ( "請輸入種類1:測驗 2:作業 3:心得報告" ) ; 
n = cin . nextInt ( ) ; 
Evaluation std = new Evaluation ( ) ; 
System . out . println ( "請輸入學生學號:" ) ; 
s = cin . next ( ) ; 
std . setSID ( s ) ; 
System . out . println ( "請輸入學生姓名:" ) ; 
s = cin . next ( ) ; 
std . setName ( s ) ; 
System . out . println ( "請輸入成績:" ) ; 
s = cin . next ( ) ; 
std . setScore ( s ) ; 
System . out . println ( "請輸入成績比重:" ) ; 
d = cin . nextDouble ( ) ; 
std . setSP ( d ) ; 
System . out . println ( "請輸入考試日期:" ) ; 
s = cin . next ( ) ; 
std . setData ( s ) ; 
System . out . println ( "請輸入考試範圍:" ) ; 
s = cin . next ( ) ; 
std . setRange ( s ) ; 
std . print ( ) ; 
} 
} 
