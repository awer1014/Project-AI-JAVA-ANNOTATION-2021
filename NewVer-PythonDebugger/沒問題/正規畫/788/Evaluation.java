abstract public class Evaluation { 
protected String number ; 
protected String name ; 
protected double grade ; 
protected double hundred ; 
Evaluation ( String number , String name , double grade , double hundred ) { 
this . number = number ; 
this . name = name ; 
this . grade = grade ; 
this . hundred = hundred ; 
} 
double getGrade ( ) { 
return grade ; 
} 
double getHundred ( ) { 
return hundred ; 
} 
} 
