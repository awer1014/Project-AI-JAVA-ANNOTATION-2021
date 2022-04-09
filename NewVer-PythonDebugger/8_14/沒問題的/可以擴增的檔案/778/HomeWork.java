public class HomeWork extends Evaluation { 
public HomeWork ( String stype , String Snum , String Sname , double Sgrade , double Spgrade ) { 
super ( stype , Snum , Sname , Sgrade , Spgrade ) ; 
} 
String odate , des ; 
void Hprint ( ) { 
System . out . println ( "截止日期: " + odate ) ; 
System . out . println ( "作業描述: " + des ) ; 
} 
} 
