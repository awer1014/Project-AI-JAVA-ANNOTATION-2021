public class Report extends Evaluation { 
public Report ( String day , String cover ) { 
super ( day , cover ) ; 
} 
void print ( ) { 
System . out . println ( "報告內容：" + day ) ; 
System . out . println ( "參考文獻：" + cover ) ; 
} 
} 
