import java . util . Scanner ; 
import java . util . Random ; 
public class Display { 
static Scanner keyboard = new Scanner ( System . in ) ; 
private static Evaluation chooseDisplay ( ) { 
System . out . println ( "Please choosing one to display:" ) ; 
System . out . println ( "1.Exam" ) ; 
System . out . println ( "2.HomeWork" ) ; 
System . out . println ( "3.Report" ) ; 
int x = 0 ; 
do 
{ 
x = keyboard . nextInt ( ) ; 
} 
while ( x > 3 || x < 1 ) ; 
return mapDisplay ( x ) ; 
} 
static Evaluation mapDisplay ( int x ) { 
Evaluation eva = null ; 
switch ( x ) { 
case 1 : 
eva = new Exam ( "18/12/30" , "物件導向第十二週預習範圍" ) ; 
break ; 
case 2 : 
eva = new HomeWork ( "18/12/31" , "罰寫KK音標" ) ; 
break ; 
case 3 : 
eva = new Report ( "閱讀「與黑人的相遇」的心得感想" , "圖書館典藏書籍" ) ; 
break ; 
} 
return eva ; 
} 
static Evaluation randoomChooseDisplay ( ) { 
Random y = new Random ( ) ; 
int ans = y . nextInt ( 3 ) + 1 ; 
return mapDisplay ( ans ) ; 
} 
public static void main ( String [ ] args ) { 
Student student = new Student ( "Grace" ) ; 
student . setEvaluation ( chooseDisplay ( ) ) ; 
student . display ( ) ; 
} 
} 
