import java . util . Scanner ; 
public class test { 
public static void main ( String [ ] args ) { 
Scanner kb = new Scanner ( System . in ) ; 
while ( true ) { 
System . out . println ( "請輸入種類 1(測驗) 2(作業) 3(心得報告)" ) ; 
int flag = kb . nextInt ( ) ; 
if ( flag == 0 ) break ; 
kb . nextLine ( ) ; 
System . out . println ( "請輸入學生學號 :" ) ; 
String id = kb . nextLine ( ) ; 
System . out . println ( "請輸入學生姓名 :" ) ; 
String n = kb . nextLine ( ) ; 
System . out . println ( "請輸入成績 :" ) ; 
Double sc = kb . nextDouble ( ) ; 
System . out . println ( "請輸入成績比重:" ) ; 
Double p = kb . nextDouble ( ) ; 
kb . nextLine ( ) ; 
Evaluation eva = null ; 
if ( flag == 1 ) { 
System . out . println ( "請輸入考試日期:" ) ; 
String date = kb . nextLine ( ) ; 
System . out . println ( "請輸入考試範圍:" ) ; 
String range = kb . nextLine ( ) ; 
eva = new Exam ( flag , id , n , sc , p , date , range ) ; 
eva . print ( ) ; 
} 
else if ( flag == 2 ) { 
System . out . println ( "請輸入截止日期:" ) ; 
String date = kb . nextLine ( ) ; 
System . out . println ( "請輸入作業描述:" ) ; 
String describe = kb . nextLine ( ) ; 
eva = new HomeWork ( flag , id , n , sc , p , date , describe ) ; 
eva . print ( ) ; 
} 
else { 
System . out . println ( "請輸入報告內容:" ) ; 
String text = kb . nextLine ( ) ; 
System . out . println ( "請輸入參考文獻:" ) ; 
String ref = kb . nextLine ( ) ; 
eva = new Report ( flag , id , n , sc , p , text , ref ) ; 
eva . print ( ) ; 
} 
} 
} 
} 
