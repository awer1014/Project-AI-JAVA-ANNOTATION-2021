public class Exam { 
private String studentid ; 
private String name ; 
private double score ; 
private double proportion ; 
private int date ; 
private String range ; 
Exam ( String studentid , String name , double score , double proportion , int date , String range ) { 
this . studentid = studentid ; 
this . name = name ; 
this . score = score ; 
this . proportion = proportion ; 
this . date = date ; 
this . range = range ; 
} 
void print ( ) { 
System . out . println ( "種類: 考試" ) ; 
System . out . println ( "學號： " + studentid ) ; 
System . out . println ( "學生姓名: " + name ) ; 
System . out . println ( "學生考試成績： " + score ) ; 
System . out . println ( "成績比重： " + proportion ) ; 
System . out . println ( "繳交日期： " + date ) ; 
System . out . println ( "考試範圍： " + range ) ; 
} 
} 
