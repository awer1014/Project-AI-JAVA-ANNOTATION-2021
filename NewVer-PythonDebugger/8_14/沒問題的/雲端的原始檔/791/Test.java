import java . util . Scanner ; 
public class Test { 
public static void main ( String [ ] args ) { 
Scanner sc = new Scanner ( System . in ) ; 
System . out . println ( "請輸入種類 1(測驗), 2(作業), 3(心得報告) : " ) ; 
int c = 0 ; 
do 
{ 
c = sc . nextInt ( ) ; 
} 
while ( c > 3 || c < 1 ) ; 
return mapEvaluation ( c ) ; 
} 
static Evaluation mapEvaluation ( int c ) { 
Evaluation eval = null ; 
switch ( c ) { 
case 1 : 
eval = new Exam ( "Exam" ) ; 
break ; 
} 
} 
} 
