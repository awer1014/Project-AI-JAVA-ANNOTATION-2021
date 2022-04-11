public class Exam extends Evaluation { 
String testrange ; 
String testday ; 
public Exam ( String a , String b , double c , double w , String tr , String td ) { 
super ( a , b , c , w ) ; 
this . testrange = tr ; 
this . testday = td ; 
} 
public void print ( ) { 
System . out . print ( "您輸入的是：" + "\r\n" ) ; 
System . out . print ( "種類為測驗：" + "\r\n" ) ; 
System . out . print ( "學生學號：" + number + "\r\n" ) ; 
System . out . print ( "學生姓名：" + name + "\r\n" ) ; 
System . out . print ( "成績：" + test + "\r\n" ) ; 
System . out . print ( "成績比重：" + testq + "\r\n" ) ; 
System . out . print ( "考試日期：" + data + "\r\n" ) ; 
System . out . print ( "考試範圍：" + rang + "\r\n" ) ; 
} 
} 
