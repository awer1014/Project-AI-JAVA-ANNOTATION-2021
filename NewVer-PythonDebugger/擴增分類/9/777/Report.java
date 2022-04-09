public class Report extends Evaluation { 
public Report ( String id , String name , double g , double w , String type ) { 
super ( id , name , g , w , type ) ; 
} 
public void A ( ) { 
System . out . println ( "請輸入報告內容" ) ; 
} 
public void B ( ) { 
System . out . println ( "請輸入參考文獻" ) ; 
} 
public void Dis ( String a , String b ) { 
System . out . println ( "報告內容 : " + a ) ; 
System . out . println ( "參考文獻 : " + b ) ; 
} 
} 
