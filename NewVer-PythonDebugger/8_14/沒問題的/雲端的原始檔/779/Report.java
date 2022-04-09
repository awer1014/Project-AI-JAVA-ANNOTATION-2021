public class Report extends Evaluation { 
String content , references ; 
public Report ( String num , String name , double score , double compete , String content , String references ) { 
super ( num , name , score , compete ) ; 
this . content = content ; 
this . references = references ; 
} 
public void play ( ) { 
System . out . println ( "報告內容 : " + content ) ; 
System . out . println ( "參考文獻 : " + references ) ; 
} 
} 
