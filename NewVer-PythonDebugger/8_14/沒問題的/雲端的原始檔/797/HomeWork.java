public class HomeWork { 
private String studentid ; 
private String name ; 
private double score ; 
private int date ; 
HomeWork ( String studentid , String name , double score , int date ) { 
this . studentid = studentid ; 
this . name = name ; 
this . score = score ; 
this . date = date ; 
} 
void print ( ) { 
System . out . println ( "\n" ) ; 
System . out . println ( "種類: 作業" ) ; 
System . out . println ( "學號： " + studentid ) ; 
System . out . println ( "學生姓名： " + name ) ; 
System . out . println ( "學生作業成績： " + score ) ; 
System . out . println ( "繳交日期： " + date ) ; 
} 
} 
