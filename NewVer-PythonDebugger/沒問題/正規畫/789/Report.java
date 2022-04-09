public class Report extends Evaluation { 
String nei ; 
String wen ; 
Report ( String number , String name , double grade , double percent , String nei , String wen ) { 
super ( number , name , grade , percent ) ; 
this . nei = nei ; 
this . wen = wen ; 
} 
public void print ( int i ) { 
System . out . println ( "種類：" + Eva [ i ] ) ; 
System . out . println ( "學生學號：" + number ) ; 
System . out . println ( "學生姓名：" + name ) ; 
System . out . println ( "成績：" + grade ) ; 
System . out . println ( "成績比重：" + percent ) ; 
System . out . println ( "報告內容：" + nei ) ; 
System . out . println ( "參考文獻：" + wen ) ; 
} 
} 
