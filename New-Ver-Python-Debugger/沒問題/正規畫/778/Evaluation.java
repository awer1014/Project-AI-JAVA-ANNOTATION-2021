import java . util . * ; 
public class Evaluation { 
protected String type , num , name ; 
protected double grade , pgrade ; 
Evaluation ( String stype , String Snum , String Sname , double Sgrade , double Spgrade ) { 
type = stype ; 
num = Snum ; 
name = Sname ; 
grade = Sgrade ; 
pgrade = Spgrade ; 
} 
void print ( ) { 
System . out . println ( "您輸入的是：" ) ; 
System . out . println ( "種類：" + type ) ; 
System . out . println ( "學生學號：" + num ) ; 
System . out . println ( "學生姓名：" + name ) ; 
System . out . println ( "成績：" + grade ) ; 
System . out . println ( "成績比重：" + pgrade ) ; 
} 
} 
