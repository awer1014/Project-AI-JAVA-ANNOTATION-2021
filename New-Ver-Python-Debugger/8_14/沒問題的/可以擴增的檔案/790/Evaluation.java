abstract public class Evaluation { 
protected String stuno ; 
protected String name ; 
protected double score , rate ; 
public Evaluation ( String stuno , String name , double score , double rate , String x , String y ) { 
this . stuno = stuno ; 
this . name = name ; 
this . score = score ; 
this . rate = rate ; 
} 
double getScore ( ) { 
return score ; 
} 
double getRate ( ) { 
return rate ; 
} 
} 
