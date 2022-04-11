public class Student { 
private String id , name , score , prop ; 
public Student ( String i , String n , String s , String p ) { 
id = i ; 
name = n ; 
score = s ; 
prop = p ; 
} 
public void print ( ) { 
System . out . println ( "學生學號: " + id ) ; 
System . out . println ( "學生姓名: " + name ) ; 
System . out . println ( "成績: " + score ) ; 
System . out . println ( "成績比重: " + prop ) ; 
} 
} 
