public class Test extends Kindsoftest { 
public Test ( String name , int id , int gread , int date , double gp , String range ) { 
super ( name , id , gread , date , gp , range ) ; 
} 
void print ( ) { 
System . out . println ( "種類：測驗" ) ; 
System . out . println ( "學生學號：" + name ) ; 
System . out . println ( "學生姓名：" + id ) ; 
System . out . println ( "成績：" + gread ) ; 
System . out . println ( "成績比重：" + gp ) ; 
System . out . println ( "考試日期：" + date ) ; 
} 
} 
