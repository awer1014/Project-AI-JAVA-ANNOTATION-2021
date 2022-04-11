public class Evaluation { 
public String SID , Sname , Data , Range , Score ; 
public Double SP ; 
public Evaluation ( ) { 
} 
public void setSID ( String s ) { 
SID = s ; 
} 
public void setName ( String s ) { 
Sname = s ; 
} 
public void setScore ( String s ) { 
Score = s ; 
} 
public void setData ( String s ) { 
Data = s ; 
} 
public void setRange ( String s ) { 
Range = s ; 
} 
public void setSP ( Double s ) { 
SP = s ; 
} 
void print ( ) { 
System . out . println ( "學生學號:" + SID ) ; 
System . out . println ( "學生姓名:" + Sname ) ; 
System . out . println ( "成績:" + Score ) ; 
System . out . println ( "成績比重:" + SP ) ; 
System . out . println ( "考試日期:" + Data ) ; 
System . out . println ( "考試範圍:" + Range ) ; 
} 
} 
