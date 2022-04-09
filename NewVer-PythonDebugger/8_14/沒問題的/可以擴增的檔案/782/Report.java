import java . util . Scanner ; 
public class Report extends output { 
public void Report ( ) { 
Scanner scan = new Scanner ( System . in ) ; 
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
System . out . println ( "學生學號:" + num ) ; 
System . out . println ( "學生姓名:" + name ) ; 
System . out . println ( "成績:" + sc ) ; 
System . out . println ( "成績比重:" + s ) ; 
System . out . println ( "報告內容:" + i ) ; 
System . out . println ( "參考文獻:" + r ) ; 
} 
} 
