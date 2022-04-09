import java . util . Scanner ; 
public class test { 
public static void main ( String [ ] args ) { 
Scanner sc = new Scanner ( System . in ) ; 
System . out . print ( "請輸入信件格式  1(中文), 2(英文): " ) ; 
int i = sc . nextInt ( ) ; 
Letter letter ; 
switch ( i ) { 
case 1 : 
ChineseLetter Cl = new ChineseLetter ( ) ; 
letter = new ChineseLetter ( ) ; 
letter . readFull ( ) ; 
Cl . display ( ) ; 
break ; 
default : 
EnglishLetter El = new EnglishLetter ( ) ; 
letter = new EnglishLetter ( ) ; 
letter . readFull ( ) ; 
El . display ( ) ; 
break ; 
} 
} 
} 
