import java . util . Scanner ; 
public class Exam { 
public static void main ( String [ ] args ) { 
Scanner sc = new Scanner ( System . in ) ; 
String test , id , name , gread , gp , date , range ; 
System . out . print ( "請輸入種類1(測驗)，2(作業)，3(心得報告)：" ) ; 
test = sc . nextLine ( ) ; 
System . out . print ( "請輸入學生學號：" ) ; 
id = sc . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名：" ) ; 
name = sc . nextLine ( ) ; 
System . out . print ( "請輸入成績：" ) ; 
gread = sc . nextLine ( ) ; 
System . out . print ( "請輸入成績比重：" ) ; 
gp = sc . nextLine ( ) ; 
System . out . print ( "請輸入考試日期：" ) ; 
date = sc . nextLine ( ) ; 
System . out . print ( "請輸入考試範圍：" ) ; 
range = sc . nextLine ( ) ; 
if ( test . equals ( "1" ) ) { 
Kindsoftest kindsoftest = new Test ( name , id , gread , date , gp , range ) ; 
Test . print ( ) ; 
System . out . println ( "-----------------------------------" ) ; 
} 
else if ( test . equals ( "2" ) ) { 
Kindsoftest kindsoftest = new Hwork ( name , id , gread , date , gp , range ) ; 
Hwork . print ( ) ; 
System . out . println ( "-----------------------------------" ) ; 
} 
else { 
Kindsoftest kindsoftest = new Mind ( name , id , gread , date , gp , range ) ; 
Mind . print ( ) ; 
System . out . println ( "-----------------------------------" ) ; 
} 
} 
} 
