import java . util . Scanner ; 
public class kkbox { 
static double leg ; 
static double wid ; 
static double hei ; 
static double volume ( double x , double y , double z ) { 
return x * y * z ; 
} 
static double surfaceArea ( double x , double y , double z ) { 
return 2 * x * y + y * z + z * x ; 
} 
public static void main ( String [ ] args ) { 
Scanner scanner = new Scanner ( System . in ) ; 
System . out . print ( "請輸入一個長：" ) ; 
leg = scanner . nextDouble ( ) ; 
System . out . print ( "請輸入一個寬：" ) ; 
wid = scanner . nextDouble ( ) ; 
System . out . print ( "請輸入一個高：" ) ; 
hei = scanner . nextDouble ( ) ; 
System . out . println ( "體積 = " + volume ( leg , wid , hei ) ) ; 
System . out . println ( "表面積 = " + surfaceArea ( leg , wid , hei ) ) ; 
} 
} 
