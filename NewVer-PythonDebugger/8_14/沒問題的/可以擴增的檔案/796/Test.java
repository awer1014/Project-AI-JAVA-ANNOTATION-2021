import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
Scanner scan = new Scanner ( System . in ) ; 
boolean b = true ; 
while ( b ) { 
System . out . print ( "請輸入種類 : " ) ; 
String pt = scan . nextLine ( ) ; 
if ( pt . equals ( "0" ) ) { 
b = false ; 
break ; 
} 
System . out . print ( "請輸入學生學號: " ) ; 
String pp = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名 : " ) ; 
String pn = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績 : " ) ; 
String kn = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重 : " ) ; 
String ki = scan . nextLine ( ) ; 
System . out . print ( "請輸入考試日期 : " ) ; 
String kt = scan . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍 : " ) ; 
String kd = scan . nextLine ( ) ; 
System . out . print ( "--------------------" ) ; 
Keeper k = new Keeper ( kn , ki , kt ) ; 
Evaluation p ; 
if ( pt . equals ( "1" ) ) 
p = new Exam ( pp , pn , pt , k ) ; 
else if ( pt . equals ( "2" ) ) 
p = new HomeWork ( pp , pn , pt , k ) ; 
else 
p = new Report ( pp , pn , pt , k ) ; 
System . out . println ( "您輸入的是 : " ) ; 
} 
} 
} 
