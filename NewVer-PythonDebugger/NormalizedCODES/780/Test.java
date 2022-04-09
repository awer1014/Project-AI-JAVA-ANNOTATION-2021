import java . util . Scanner ; 
public class Test { 
static Scanner keyboard = new Scanner ( System . in ) ; 
private static Evaluation choose ( ) { 
System . out . println ( "請輸入種類:" ) ; 
System . out . println ( "1.測驗" ) ; 
System . out . println ( "2.作業" ) ; 
System . out . println ( "3.心得報告" ) ; 
int c = 0 ; 
do 
{ 
c = keyboard . nextInt ( ) ; 
} 
while ( c > 3 || c < 1 ) ; 
return choice ( c ) ; 
} 
static Evaluation choice ( int c ) { 
switch ( c ) { 
case 1 : 
System . out . println ( "請輸入學生學號:" ) ; 
System . out . println ( "請輸入學生姓名:" ) ; 
System . out . println ( "請輸入成績:" ) ; 
System . out . println ( "請輸入成績比重:" ) ; 
System . out . println ( "請輸入考試日期" ) ; 
System . out . println ( "請輸入考試範圍" ) ; 
break ; 
case 2 : 
System . out . println ( "請輸入學生學號:" ) ; 
System . out . println ( "請輸入學生姓名:" ) ; 
System . out . println ( "請輸入成績:" ) ; 
System . out . println ( "請輸入成績比重:" ) ; 
System . out . println ( "請輸入截止日期:" ) ; 
System . out . println ( "請輸入作業描述:" ) ; 
break ; 
case 3 : 
System . out . println ( "請輸入學生學號:" ) ; 
System . out . println ( "請輸入學生姓名:" ) ; 
System . out . println ( "請輸入成績:" ) ; 
System . out . println ( "請輸入成績比重:" ) ; 
System . out . println ( "請輸入報告內容:" ) ; 
System . out . println ( "請輸入參考文獻:" ) ; 
break ; 
} 
return ev ; 
} 
} 
