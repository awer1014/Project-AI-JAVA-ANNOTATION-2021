import java . util . Scanner ; 
public class HW extends output { 
public void kind ( ) { 
Scanner scan = new Scanner ( System . in ) ; 
System . out . print ( "請輸入學生學號" ) ; 
String num = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名" ) ; 
String name = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績" ) ; 
String sc = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重" ) ; 
String s = scan . nextLine ( ) ; 
System . out . print ( "請輸入截止日期" ) ; 
String d = scan . nextLine ( ) ; 
System . out . print ( "請輸入作業描述" ) ; 
String i = scan . nextLine ( ) ; 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "學生學號:" + num ) ; 
System . out . println ( "學生姓名:" + name ) ; 
System . out . println ( "成績:" + sc ) ; 
System . out . println ( "成績比重:" + s ) ; 
System . out . println ( "截止日期:" + d ) ; 
System . out . println ( "作業描述:" + i ) ; 
} 
} 
