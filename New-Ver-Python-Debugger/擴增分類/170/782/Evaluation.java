import java . util . Scanner ; 
public class Evaluation { 
public static void main ( ) { 
Scanner scan = new Scanner ( System . in ) ; 
System . out . print ( "請輸入種類(1=測驗,2=作業,3=心得報告)" ) ; 
String k = scan . nextLine ( ) ; 
if ( k . equals ( "1" ) ) { 
System . out . print ( "請輸入學生學號" ) ; 
String num = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名" ) ; 
String name = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績" ) ; 
String sc = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重" ) ; 
String s = scan . nextLine ( ) ; 
System . out . print ( "請輸入考試日期" ) ; 
String d = scan . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍" ) ; 
String r = scan . nextLine ( ) ; 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "種類:" + k ) ; 
System . out . println ( "學生學號:" + num ) ; 
System . out . println ( "學生姓名:" + name ) ; 
System . out . println ( "成績:" + sc ) ; 
System . out . println ( "成績比重:" + s ) ; 
System . out . println ( "考試日期:" + d ) ; 
System . out . println ( "考試範圍:" + r ) ; 
} 
else if ( k . equals ( "2" ) ) { 
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
System . out . println ( "種類:" + k ) ; 
System . out . println ( "學生學號:" + num ) ; 
System . out . println ( "學生姓名:" + name ) ; 
System . out . println ( "成績:" + sc ) ; 
System . out . println ( "成績比重:" + s ) ; 
System . out . println ( "截止日期:" + d ) ; 
System . out . println ( "作業描述:" + i ) ; 
} 
else { 
System . out . print ( "請輸入學生學號" ) ; 
String num = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名" ) ; 
String name = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績" ) ; 
String sc = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重" ) ; 
String s = scan . nextLine ( ) ; 
System . out . print ( "請輸入報告內容" ) ; 
String i = scan . nextLine ( ) ; 
System . out . print ( "請輸入參考文獻" ) ; 
String r = scan . nextLine ( ) ; 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "種類:" + k ) ; 
System . out . println ( "學生學號:" + num ) ; 
System . out . println ( "學生姓名:" + name ) ; 
System . out . println ( "成績:" + sc ) ; 
System . out . println ( "成績比重:" + s ) ; 
System . out . println ( "報告內容:" + i ) ; 
System . out . println ( "參考文獻:" + r ) ; 
} 
} 
} 
