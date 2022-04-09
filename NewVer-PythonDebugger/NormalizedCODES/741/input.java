import java . util . Scanner ; 
import java . util . Random ; 
public class input { 
static int count = 0 ; 
static Scanner keyboard = new Scanner ( System . in ) ; 
public static void main ( String [ ] args ) { 
System . out . print ( "請輸入寵物種類:" ) ; 
String type = keyboard . nextLine ( ) ; 
while ( type != "0" ) { 
System . out . print ( "請輸入寵物編號:" ) ; 
String pid = keyboard . nextLine ( ) ; 
System . out . print ( "請輸入寵物名稱:" ) ; 
String name = keyboard . nextLine ( ) ; 
System . out . print ( "請輸入飼主身份證字號:" ) ; 
String id = keyboard . nextLine ( ) ; 
System . out . print ( "請輸入飼主電話:" ) ; 
String tel = keyboard . nextLine ( ) ; 
} 
if ( type == "0" ) { 
} 
} 
} 
