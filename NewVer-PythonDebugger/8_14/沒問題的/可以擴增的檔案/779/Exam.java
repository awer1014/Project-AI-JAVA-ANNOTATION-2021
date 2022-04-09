public class Exam extends Evaluation { 
String date , range ; 
public Exam ( String num , String name , double score , double compete , String date , String range ) { 
super ( num , name , score , compete ) ; 
this . date = date ; 
this . range = range ; 
} 
public void play ( ) { 
System . out . println ( "考試日期 : " + date ) ; 
System . out . println ( "考試範圍 : " + range ) ; 
} 
} 
