import java . util . Scanner ; 
public class KKBox { 
public static void main ( String [ ] args ) { 
int L , W , H ; 
Scanner scanner = new Scanner ( System . in ) ; 
L = scanner . nextInt ( ) ; 
W = scanner . nextInt ( ) ; 
H = scanner . nextInt ( ) ; 
System . out . println ( "請輸入長:" + L ) ; 
System . out . println ( "請輸入寬:" + W ) ; 
System . out . println ( "請輸入高:" + H ) ; 
System . out . println ( "體積:" + L * W * H ) ; 
System . out . println ( "表面積:" + 2 * L * W + W * H + H * L ) ; 
} 
} 
