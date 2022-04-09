abstract public class Evaluation { 
String num , name ; 
double score , compete ; 
public Evaluation ( String num , String name , double score , double compete ) { 
this . num = num ; 
this . name = name ; 
this . score = score ; 
this . compete = compete ; 
} 
abstract public void play ( ) ; 
public void display ( ) { 
System . out . println ( "學生學號 : " + num ) ; 
System . out . println ( "學生姓名 : " + name ) ; 
System . out . println ( "成績 : " + score ) ; 
System . out . println ( "成績比重 : " + compete ) ; 
} 
} 
