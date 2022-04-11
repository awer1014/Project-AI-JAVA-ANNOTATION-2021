import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
Scanner scan = new Scanner ( System . in ) ; 
boolean b = true ; 
while ( b ) { 
System . out . print ( "請輸入種類 1(測驗) 2(作業) 3(心得報告): " ) ; 
String t = scan . nextLine ( ) ; 
if ( t . equals ( "0" ) ) { 
b = false ; 
break ; 
} 
System . out . print ( "請輸入學生學號 : " ) ; 
String sid = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名 : " ) ; 
String sn = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績 : " ) ; 
double sg = scan . nextDouble ( ) ; 
String enter = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重 : " ) ; 
double sw = scan . nextDouble ( ) ; 
enter = scan . nextLine ( ) ; 
Evaluation e ; 
if ( t . equals ( "1" ) ) 
e = new Exam ( sid , sn , sg , sw , t ) ; 
else if ( t . equals ( "2" ) ) 
e = new HomeWork ( sid , sn , sg , sw , t ) ; 
else 
e = new Report ( sid , sn , sg , sw , t ) ; 
e . A ( ) ; 
String aa = scan . nextLine ( ) ; 
e . B ( ) ; 
String bb = scan . nextLine ( ) ; 
System . out . println ( "您輸入的是 : " ) ; 
e . display ( ) ; 
e . Dis ( aa , bb ) ; 
System . out . print ( "-------------------- please cilck enter" ) ; 
enter = scan . nextLine ( ) ; 
} 
} 
} 
