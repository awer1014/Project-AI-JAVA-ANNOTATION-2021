public class Homework extends Evaluation { 
String day ; 
String meow ; 
Homework ( String number , String name , double grade , double percent , String day , String meow ) { 
super ( number , name , grade , percent ) ; 
this . day = day ; 
this . meow = meow ; 
} 
public void print ( int i ) { 
System . out . println ( "種類：" + Eva [ i ] ) ; 
System . out . println ( "學生學號：" + number ) ; 
System . out . println ( "學生姓名：" + name ) ; 
System . out . println ( "成績：" + grade ) ; 
System . out . println ( "成績比重：" + percent ) ; 
System . out . println ( "截止日期：" + day ) ; 
System . out . println ( "作業描述：" + meow ) ; 
} 
} 
