import java . util . Scanner ; 
import java . util . Random ; 
public class Keeper { 
public String id ; 
public String tel ; 
public Keeper ( String id , String tel ) { 
this . id = id ; 
this . tel = tel ; 
} 
void display ( ) { 
System . out . println ( "身分證字號" + id ) ; 
System . out . println ( "飼主電話" + tel ) ; 
} 
static Scanner keyboard = new Scanner ( System . in ) ; 
public static int choosePet ( ) { 
System . out . println ( "Choose a pet:" ) ; 
System . out . println ( "1. 狗狗" ) ; 
System . out . println ( "2. 貓貓" ) ; 
int c = 0 ; 
do 
{ 
c = keyboard . nextInt ( ) ; 
} 
while ( c > 2 || c < 1 ) ; 
return c ; 
} 
public static int randomChoosePet ( ) { 
int c = 0 ; 
Random w = new Random ( ) ; 
c = w . nextInt ( 2 ) + 1 ; 
return c ; 
} 
public static void main ( String [ ] args ) { 
String p , n , t , i , tel ; 
int count ; 
Scanner scanner = new Scanner ( System . in ) ; 
Pet name1 = new Pet ( "狗狗" , "d001" , "Timmy" ) ; 
Pet name2 = new Pet ( "貓貓" , "c001" , "Cathy" ) ; 
p = scanner . nextLine ( ) ; 
n = scanner . nextLine ( ) ; 
t = scanner . nextLine ( ) ; 
i = scanner . nextLine ( ) ; 
tel = scanner . nextLine ( ) ; 
System . out . println ( "寵物編號" + p ) ; 
System . out . println ( "寵物名稱" + n ) ; 
System . out . println ( "寵物類別" + t ) ; 
System . out . println ( "身分證字號" + i ) ; 
System . out . println ( "飼主電話" + tel ) ; 
System . out . println ( "總和" + count ) ; 
} 
} 
