import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
Scanner scan = new Scanner ( System . in ) ; 
boolean b = true ; 
while ( b ) { 
System . out . print ( "請輸入種類: (1.測驗 2.作業 3.心得)" ) ; 
String test1 = scan . nextLine ( ) ; 
if ( test1 . equals ( "0" ) ) { 
b = false ; 
break ; 
} 
System . out . print ( "請輸入學生學號: " ) ; 
String ss = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名: " ) ; 
String sn = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績: " ) ; 
String sc = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重 : " ) ; 
String cc = scan . nextLine ( ) ; 
if ( test1 . equals ( "1" ) ) { 
System . out . print ( "請輸入考試日期: " ) ; 
String td = scan . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍: " ) ; 
String tr = scan . nextLine ( ) ; 
System . out . print ( "--------------------------" ) ; 
Teacher k = new Teacher ( sn , ss , sc , cc ) ; 
Kind p = new Kind ( td , tr ) ; 
System . out . println ( "您輸入的是: " ) ; 
k . display ( ) ; 
p . display ( 1 ) ; 
System . out . print ( "---------------------------" ) ; 
} 
else if ( test1 . equals ( "2" ) ) { 
System . out . print ( "請輸入截止日期: " ) ; 
String hd = scan . nextLine ( ) ; 
System . out . print ( "請輸入作業描述: " ) ; 
String hw = scan . nextLine ( ) ; 
System . out . print ( "--------------------------" ) ; 
Teacher k = new Teacher ( sn , ss , sc , cc ) ; 
Kind p = new Kind ( hd , hw ) ; 
System . out . println ( "您輸入的是: " ) ; 
k . display ( ) ; 
p . display ( 2 ) ; 
System . out . print ( "---------------------------" ) ; 
} 
else if ( test1 . equals ( "3" ) ) { 
System . out . print ( "請輸入報告內容 : " ) ; 
String aa = scan . nextLine ( ) ; 
System . out . print ( "請輸入參考文獻 : " ) ; 
String ab = scan . nextLine ( ) ; 
System . out . print ( "--------------------------" ) ; 
Teacher k = new Teacher ( sn , ss , sc , cc ) ; 
Kind p = new Kind ( aa , ab ) ; 
System . out . println ( "您輸入的是: " ) ; 
k . display ( ) ; 
p . display ( 3 ) ; 
System . out . print ( "---------------------------" ) ; 
} 
} 
} 
} 
