import java . util . Scanner ; 
abstract public class Evaluation { 
protected String sNumber ; 
protected String sName ; 
protected double sGrade ; 
protected double sProportion ; 
Evaluation ( String sNumber , String sName , double sGrade , double sProportion ) { 
this . sNumber = sNumber ; 
this . sName = sName ; 
this . sGrade = sGrade ; 
this . sProportion = sProportion ; 
} 
void print ( ) { 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "學生學號:" + sNumber ) ; 
System . out . println ( "學生姓名:" + sName ) ; 
System . out . println ( "成績:" + sGrade ) ; 
System . out . println ( "比重:" + sProportion ) ; 
} 
} 
