public class Exam extends Evaluation { 
public Exam ( String id , String name , double g , double w , String type ) { 
super ( id , name , g , w , type ) ; 
} 
public void A ( ) { 
System . out . println ( "請輸入考試日期" ) ; 
} 
public void B ( ) { 
System . out . println ( "請輸入考試範圍" ) ; 
} 
public void Dis ( String a , String b ) { 
System . out . println ( "考試日期 : " + a ) ; 
System . out . println ( "考試日期 : " + b ) ; 
} 
} 
