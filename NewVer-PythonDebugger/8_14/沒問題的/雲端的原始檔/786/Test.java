abstract public class Test { 
private String time , range , end_time , description ; 
private String number ; 
private String name ; 
private double score ; 
private double propotation ; 
String info ; 
String info_description ; 
public Test ( String n , String N , double s , double p ) { 
number = n ; 
name = N ; 
score = s ; 
propotation = p ; 
} 
public void display ( ) { 
System . out . println ( "學生學號：" + number ) ; 
System . out . println ( "學生姓名：" + name ) ; 
System . out . println ( "成績：" + score ) ; 
System . out . println ( "成績比重：" + propotation ) ; 
} 
abstract public void print ( ) ; 
} 
