import java . util . Random ; 
public class HomeWork extends Evaluation { 
public HomeWork ( String number , String name ) { 
super ( number , name , 0 , 0 ) ; 
System . out . println ( "請輸入學號:06360585" ) ; 
System . out . println ( "請輸入姓名:吳以樂" ) ; 
Random r = new Random ( ) ; 
grade = r . nextDouble ( ) * 100.0 ; 
hundred = 0.6 ; 
} 
} 
