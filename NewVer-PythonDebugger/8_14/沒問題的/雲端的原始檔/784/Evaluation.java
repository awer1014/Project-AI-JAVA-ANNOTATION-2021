import java . util . Scanner ; 
public class Evaluation { 
static Scanner cin = new Scanner ( System . in ) ; 
String num , name ; 
double grade , level ; 
public Evaluation ( String num , String name , double grade , double level ) { 
this . num = num ; 
this . name = name ; 
this . grade = grade ; 
this . level = level ; 
} 
public void numPrint ( ) { 
System . out . println ( num ) ; 
} 
public void namePrint ( ) { 
System . out . println ( name ) ; 
} 
public void gradePrint ( ) { 
System . out . println ( grade ) ; 
} 
public void levelPrint ( ) { 
System . out . println ( level ) ; 
} 
} 
