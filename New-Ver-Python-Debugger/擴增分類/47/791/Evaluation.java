abstract public class Evaluation { 
private String sid , name , type ; 
protected double grade , gradepc ; 
public Evaluation ( String n , String nm , String t , double grade , double gradepc ) { 
sid = n ; 
name = nm ; 
type = t ; 
this . grade = grade ; 
this . gradepc = gradepc ; 
} 
abstract public void printa ( ) ; 
public void print ( ) { 
System . out . println ( "種類 : " + type ) ; 
System . out . println ( "學生學號 : " + sid ) ; 
System . out . println ( "學生姓名 : " + name ) ; 
System . out . println ( "成績 : " + grade ) ; 
System . out . println ( "成績比重 : " + gradepc ) ; 
} 
} 
