public class HomeWork extends Evaluation { 
String off , scribe ; 
public HomeWork ( String num , String name , double score , double compete , String off , String scribe ) { 
super ( num , name , score , compete ) ; 
this . off = off ; 
this . scribe = scribe ; 
} 
public void play ( ) { 
System . out . println ( "截止日期 : " + off ) ; 
System . out . println ( "作業描述 : " + scribe ) ; 
} 
} 
