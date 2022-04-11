public class Evaluation { 
static Scanner keyboard = new Scanner ( System . in ) ; 
private static void choosetype ( ) { 
System . out . println ( "Choose evaluation:" ) ; 
System . out . println ( "1. Exam" ) ; 
System . out . println ( "2. HomeWork" ) ; 
System . out . println ( "3. Report" ) ; 
int c = 0 ; 
do 
{ 
c = keyboard . nextInt ( ) ; 
} 
while ( c > 3 || c < 1 ) ; 
return mevaluation ( c ) ; 
} 
private static Evaluation ( int c ) { 
Evalution w = null ; 
switch ( c ) { 
case 1 : 
w = new exam ( "Exam" ) ; 
break ; 
case 2 : 
w = new homework ( "Homework" ) ; 
break ; 
case 3 : 
w = new Report ( "Report" ) ; 
break ; 
} 
return w ; 
} 
String name ; 
String number ; 
double score ; 
double dscore ; 
void Evalution ( String name , String number , double score , double dscore ) { 
this . name = name ; 
this . number = number ; 
this . score = score ; 
this . dscore = dscore ; 
}
System . out . println ( "請輸入學生學號:" ) ;
} 
