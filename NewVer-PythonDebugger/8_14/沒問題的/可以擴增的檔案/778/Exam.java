public class Exam extends Evaluation { 
public Exam ( String stype , String Snum , String Sname , double Sgrade , double Spgrade ) { 
super ( stype , Snum , Sname , Sgrade , Spgrade ) ; 
} 
String tdate , trange ; 
void Eprint ( ) { 
System . out . println ( "考試日期: " + tdate ) ; 
System . out . println ( "考試範圍: " + trange ) ; 
} 
} 
