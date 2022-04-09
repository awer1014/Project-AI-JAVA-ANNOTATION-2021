import java . util . Scanner ; 
import java . util . Random ; 
public class Game { 
static Scanner keyboard = new Scanner ( System . in ) ; 
static Test mapTest ( int c ) { 
Test te = null ; 
switch ( c ) { 
case 1 : 
te = new Exam ( "Exam" ) ; 
break ; 
case 2 : 
te = new Homework ( "Homework" ) ; 
break ; 
case 3 : 
te = new Report ( "Report" ) ; 
break ; 
} 
return te ; 
} 
private static Test chooseTest ( ) { 
System . out . println ( "請輸入種類 1(測驗),2(作業),3(心得報告):" ) ; 
int c = 0 ; 
do 
{ 
c = keyboard . nextInt ( ) ; 
keyboard . nextLine ( ) ; 
} 
while ( c > 3 || c < 1 ) ; 
return mapTest ( c ) ; 
} 
public static void main ( String [ ] args ) { 
String a , b ; 
double c , d ; 
System . out . println ( "請輸入學生學號: " ) ; 
a = keyboard . next ( ) ; 
System . out . println ( "請輸入學生姓名: " ) ; 
b = keyboard . next ( ) ; 
System . out . println ( "請輸入成績: " ) ; 
c = keyboard . nextDouble ( ) ; 
keyboard . nextLine ( ) ; 
System . out . println ( "請輸入成績比重: " ) ; 
d = keyboard . nextDouble ( ) ; 
keyboard . nextLine ( ) ; 
Student user = new Student ( a , b , c , d ) ; 
user . setid ( a ) ; 
user . setname ( b ) ; 
user . setscore ( c ) ; 
user . setsw ( d ) ; 
} 
} 
