import java . util . * ; 
public class test { 
public static void main ( String [ ] args ) { 
Scanner sc = new Scanner ( System . in ) ; 
String type , Snum , Sname ; 
double Sgrade , Spgrade ; 
System . out . print ( "請輸入種類 1(測驗), 2(作業), 3(心得報告): " ) ; 
type = sc . nextLine ( ) ; 
System . out . print ( "請輸入學生學號: " ) ; 
Snum = sc . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名: " ) ; 
Sname = sc . nextLine ( ) ; 
System . out . print ( "請輸入成績: " ) ; 
Sgrade = sc . nextDouble ( ) ; 
System . out . print ( "請輸入成績比重: " ) ; 
Spgrade = sc . nextDouble ( ) ; 
while ( true ) { 
String tdate , trange , odate , des , rep , bok ; 
if ( type . equals ( "1" ) ) { 
System . out . println ( "請輸入考試日期: " ) ; 
tdate = sc . nextLine ( ) ; 
System . out . println ( "請輸入考試範圍: " ) ; 
trange = sc . nextLine ( ) ; 
} 
else if ( type . equals ( "2" ) ) { 
System . out . println ( "請輸入截止日期: " ) ; 
odate = sc . nextLine ( ) ; 
System . out . println ( "請輸入作業描述: " ) ; 
des = sc . nextLine ( ) ; 
} 
else if ( type . equals ( "3" ) ) { 
System . out . println ( "請輸入報告內容: " ) ; 
rep = sc . nextLine ( ) ; 
System . out . println ( "請輸入參考文獻: " ) ; 
bok = sc . nextLine ( ) ; 
} 
} 
Evaluation eval = null ; 
if ( type . equals ( "1" ) ) 
eval = new Exam ( type , Snum , Sname , Sgrade , Spgrade ) ; 
else if ( type . equals ( "2" ) ) 
eval = new HomeWork ( type , Snum , Sname , Sgrade , Spgrade ) ; 
else if ( type . equals ( "3" ) ) 
eval = new Report ( type , Snum , Sname , Sgrade , Spgrade ) ; 
eval . print ( ) ; 
} 
} 
