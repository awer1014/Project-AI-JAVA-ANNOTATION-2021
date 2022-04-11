abstract public class Evaluation { 
protected String id ; 
protected String type ; 
protected String name ; 
protected double g ; 
protected double w ; 
public Evaluation ( String id , String name , double g , double w , String type ) { 
this . id = id ; 
this . name = name ; 
this . g = g ; 
this . w = w ; 
this . type = type ; 
} 
abstract public void A ( ) ; 
abstract public void B ( ) ; 
abstract public void Dis ( String a , String b ) ; 
public void display ( ) { 
System . out . println ( "種類 : " + type ) ; 
System . out . println ( "學生學號 : " + id ) ; 
System . out . println ( "學生姓名 : " + name ) ; 
System . out . println ( "成績 : " + g ) ; 
System . out . println ( "成績比重 : " + w ) ; 
} 
} 
