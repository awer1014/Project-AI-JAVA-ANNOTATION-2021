import java . util . * ; 
public class Exam extends Evaluation { 
public Exam ( String stuno , String name , double score , double rate , String date , String range ) { 
super ( stuno , name , score , rate , date , range ) ; 
} 
void print ( ) { 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "種類:測驗" ) ; 
System . out . println ( "學生學號:" + stuno ) ; 
} 
} 
