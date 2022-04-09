import java . util . * ; 
public class test { 
public static void main ( String [ ] args ) { 
Scanner sc = new Scanner ( System . in ) ; 
String sname , snum ; 
int kkind ; 
System . out . print ( "請輸入種類：" ) ; 
kkind = sc . nextInt ( ) ; 
System . out . print ( "請輸入學生學號：" ) ; 
sc . nextLine ( ) ; 
snum = sc . nextLine ( ) ; 
System . out . print ( "請輸入姓名：" ) ; 
sname = sc . nextLine ( ) ; 
System . out . println ( "------------------------------------" ) ; 
Student student = new Student ( snum , sname ) ; 
int tgrade ; 
double tpercentage ; 
String tdate , tscale ; 
System . out . print ( "請輸入成績：" ) ; 
tgrade = sc . nextInt ( ) ; 
System . out . print ( "請輸入成績比重：" ) ; 
tpercentage = sc . nextDouble ( ) ; 
System . out . print ( "請輸入考試日期：" ) ; 
sc . nextLine ( ) ; 
tdate = sc . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍：" ) ; 
tscale = sc . nextLine ( ) ; 
System . out . println ( "-----------------------------------" ) ; 
System . out . println ( "您輸入的是：" ) ; 
Score score ; 
if ( kkind == 1 ) 
score = new Exam ( tgrade , tpercentage , tdate , tscale ) ; 
else if ( kkind == 2 ) 
score = new Homework ( tgrade , tpercentage , tdate , tscale ) ; 
else 
score = new Report ( tgrade , tpercentage , tdate , tscale ) ; 
score . print ( ) ; 
System . out . println ( "-----------------------------------" ) ; 
} 
} 
