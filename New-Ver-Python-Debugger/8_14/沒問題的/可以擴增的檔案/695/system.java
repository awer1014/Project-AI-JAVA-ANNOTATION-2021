import java . util . Scanner ; 
public class system { 
static Scanner keyboard = new Scanner ( System . in ) ; 
public static void main ( String [ ] args ) { 
int count = 0 ; 
String pid ; 
String name ; 
String type ; 
while ( type == "0" ) { 
System . out . println ( "請輸入寵物種類:" ) ; 
type = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入寵物編號:" ) ; 
pid = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱:" ) ; 
name = keyboard . nextLine ( ) ; 
count ++ ; 
} 
} 
} 
