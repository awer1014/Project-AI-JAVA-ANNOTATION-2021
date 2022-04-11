import java . util . Scanner ; 
import java . util . Random ; 
public class Main { 
static Scanner keyboard = new Scanner ( System . in ) ; 
Pet pt ; 
static String Pid ; 
int count ; 
static String Name ; 
static String Type ; 
static String Tell ; 
static String Kid ; 
public static void main ( ) { 
System . out . println ( "請輸入寵物種類:" ) ; 
Type = keyboard . toString ( ) ; 
System . out . println ( "請輸入寵物編號：" ) ; 
Pid = keyboard . toString ( ) ; 
System . out . println ( "請輸入寵物名稱：" ) ; 
Name = keyboard . toString ( ) ; 
System . out . println ( "請輸入飼主身分證字號：" ) ; 
Kid = keyboard . toString ( ) ; 
System . out . println ( "請輸入飼主電話：" ) ; 
Tell = keyboard . toString ( ) ; 
} 
} 
