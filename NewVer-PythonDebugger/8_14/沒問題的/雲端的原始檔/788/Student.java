import java . util . Scanner ; 
class Student { 
static Scanner number_keyboard = new Scanner ( System . in ) ; 
String name ; 
Evaluation eva ; 
void setEvaluation ( Evaluation a ) { 
eva = a ; 
} 
Evaluation getEvaluation ( ) { 
return eva ; 
} 
Student ( String n ) { 
this . name = n ; 
} 
String getName ( ) { 
return name ; 
} 
void display ( ) { 
System . out . println ( "請輸入日期或項目:" + eva . number ) ; 
System . out . println ( "請輸入描述:" + eva . name ) ; 
System . out . println ( "請輸入成績:" + eva . grade ) ; 
} 
} 
