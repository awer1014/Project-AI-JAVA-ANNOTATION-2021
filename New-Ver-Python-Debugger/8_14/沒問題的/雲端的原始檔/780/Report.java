public class Report extends Evaluation { 
protected String content ; 
protected String references ; 
public Report ( String sNumber , String sName , double sGrade , double sProportion , String content , String references ) { 
super ( sNumber , sName , sGrade , sProportion ) ; 
this . content = content ; 
this . references = references ; 
} 
} 
