abstract public class Evaluation { 
private String kind ; 
public Evaluation ( String k ) { 
kind = k ; 
} 
abstract public void ask ( ) ; 
public void print ( ) { 
String str = "" ; 
if ( kind . equals ( "1" ) ) 
str += "測驗" ; 
else if ( kind . equals ( "2" ) ) 
str += "作業" ; 
else if ( kind . equals ( "3" ) ) 
str += "心得報告" ; 
System . out . println ( "種類 : " + str ) ; 
} 
} 
