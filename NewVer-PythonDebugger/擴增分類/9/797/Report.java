public class Report { 
private String studentid ; 
private String name ; 
private String literature ; 
private double score ; 
private int date ; 
Report ( String studentid , String name , String literature , double score , int date ) { 
this . studentid = studentid ; 
this . name = name ; 
this . literature = literature ; 
this . score = score ; 
this . date = date ; 
} 
void print ( ) { 
System . out . println ( "種類: 報告" ) ; 
System . out . println ( "學號： " + studentid ) ; 
System . out . println ( "學生姓名： " + name ) ; 
System . out . println ( "參考文獻： " + literature ) ; 
System . out . println ( "學生報告成績： " + score ) ; 
System . out . println ( "繳交日期： " + date ) ; 
} 
} 
