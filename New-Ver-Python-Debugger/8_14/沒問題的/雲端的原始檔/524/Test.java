import java . util . * ; 
public class Test { 
public static void main ( String [ ] args ) { 
String s , type , id , name , grade , per , data , range ; 
Scanner scan = new Scanner ( System . in ) ; 
System . out . print ( "請輸入種類:1(測驗), 2(作業), 3(心得報告)" ) ; 
type = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生學號:" ) ; 
id = scan . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名:" ) ; 
name = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績" ) ; 
grade = scan . nextLine ( ) ; 
System . out . print ( "請輸入成績比重" ) ; 
per = scan . nextLine ( ) ; 
System . out . print ( "請輸入考試日期" ) ; 
data = scan . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍" ) ; 
range = scan . nextLine ( ) ; 
int num = 0 ; 
while ( true ) { 
s = scan . nextLine ( ) ; 
break ; 
} 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "種類:" + type ) ; 
System . out . println ( "學生學號:" + id ) ; 
System . out . println ( "學生姓名:" + name ) ; 
System . out . println ( "成績:" + grade ) ; 
System . out . println ( "成績比重:" + per ) ; 
System . out . println ( "考試日期:" + data ) ; 
System . out . println ( "考試範圍" + range ) ; 
} 
} 
