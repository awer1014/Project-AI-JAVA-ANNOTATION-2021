public class Report extends Evaluation { 
public Report ( String stype , String Snum , String Sname , double Sgrade , double Spgrade ) { 
super ( stype , Snum , Sname , Sgrade , Spgrade ) ; 
} 
String rep , bok ; 
void Rprint ( ) { 
System . out . println ( "報告內容: " + rep ) ; 
System . out . println ( "參考文獻: " + bok ) ; 
} 
} 
