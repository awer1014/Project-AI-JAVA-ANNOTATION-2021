import java . util . * ; 
abstract public class Evaluation { 
protected int type ; 
protected String a ; 
protected String b ; 
Scanner n = new Scanner ( System . in ) ; 
public Evaluation ( String name , String number , double score , double percent ) { 
System . out . println ( "請輸入種類:" ) ; 
type = n . nextInt ( ) ; 
System . out . println ( "請輸入學生學號:" ) ; 
name = n . nextLine ( ) ; 
System . out . println ( "請輸入學生姓名:" ) ; 
number = n . nextLine ( ) ; 
System . out . println ( "請輸入成績:" ) ; 
score = n . nextDouble ( ) ; 
System . out . println ( "請輸入成績比重:" ) ; 
percent = n . nextDouble ( ) ; 
if ( type == 1 ) { 
System . out . println ( "請輸入考試日期:" ) ; 
a = n . nextLine ( ) ; 
System . out . println ( "請輸入考試範圍:" ) ; 
b = n . nextLine ( ) ; 
} 
else if ( type == 2 ) { 
System . out . println ( "請輸入截止日期:" ) ; 
a = n . nextLine ( ) ; 
System . out . println ( "請輸入作業描述:" ) ; 
b = n . nextLine ( ) ; 
} 
else { 
System . out . println ( "請輸入報告內容:" ) ; 
a = n . nextLine ( ) ; 
System . out . println ( "請輸入參考文獻:" ) ; 
b = n . nextLine ( ) ; 
} 
} 
void print ( ) { 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "種類: " + type ) ; 
System . out . println ( "學生學號: " ) ; 
} 
public static void main ( String [ ] args ) { 
} 
} 
