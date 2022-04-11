import java . util . * ; 
public class Evalution { 
public static void main ( String [ ] args ) { 
Scanner sc = new Scanner ( System . in ) ; 
boolean b = true ; 
while ( true ) { 
System . out . println ( "請輸入種類:" ) ; 
System . out . println ( "1. 測驗" ) ; 
System . out . println ( "2. 作業" ) ; 
System . out . println ( "3. 心得報告" ) ; 
int c = 0 ; 
c = sc . nextInt ( ) ; 
if ( c == 1 ) { 
String number , name , grade , date , range , p ; 
System . out . print ( "請輸入學生學號：" ) ; 
number = sc . nextLine ( ) ; 
System . out . print ( "請輸入學生姓名：" ) ; 
name = sc . nextLine ( ) ; 
System . out . print ( "請輸入成績：" ) ; 
grade = sc . nextLine ( ) ; 
System . out . print ( "請輸入比重：" ) ; 
p = sc . nextLine ( ) ; 
System . out . print ( "請輸入日期：" ) ; 
date = sc . nextLine ( ) ; 
System . out . print ( "請輸入範圍：" ) ; 
range = sc . nextLine ( ) ; 
System . out . println ( "------------------------------------" ) ; 
TEST t = new TEST ( number , name , grade , p , date , range ) ; 
t . print ( ) ; 
} 
if ( c == 2 ) { 
HW H = new HW ( date ) ; 
} 
} 
} 
} 
