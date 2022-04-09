public class HomeWork extends Evaluation { 
String htestrange ; 
String htestday ; 
public homework ( String a , String b , double c , double w , String tr , String td ) { 
super ( a , b , c , w ) ; 
this . htestrange = tr ; 
this . htestday = td ; 
} 
public void print ( ) { 
System . out . print ( "您輸入的是：" + "\r\n" ) ; 
System . out . print ( "種類為測驗" + "\r\n" ) ; 
System . out . print ( "學生學號：" + number + "\r\n" ) ; 
System . out . print ( "學生姓名：" + name + "/n" ) ; 
System . out . print ( "成績：" + test + "\r\n" ) ; 
System . out . print ( "成績比重：" + testq + "\r\n" ) ; 
System . out . print ( "截止日期：" + data + "\r\n" ) ; 
System . out . print ( "作業描述：" + work + "\r\n" ) ; 
} 
} 
