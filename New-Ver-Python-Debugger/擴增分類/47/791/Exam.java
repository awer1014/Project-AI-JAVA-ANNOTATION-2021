public class Exam extends Evaluation { 
String date ; 
String range ; 
public Exam ( String n , String nm , String t , double grade , double gradepc ) { 
super ( n , nm , t , grade , gradepc ) ; 
} 
public void printa ( ) { 
System . out . println ( "考試日期: " + date ) ; 
System . out . println ( "考試範圍: " + range ) ; 
} 
} 
