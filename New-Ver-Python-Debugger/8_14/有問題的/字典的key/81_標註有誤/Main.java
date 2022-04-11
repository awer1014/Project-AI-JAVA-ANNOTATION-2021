import java . util . * ; 
import java . util . Collections ; 
public class Main { 
public static void main ( String [ ] args ) { 
Vector < Letter > a = new Vector < Letter > ( ) ; 
a . addElement ( new ChineseLetter ( "教授" , "王" , "很高興跟您連絡" , "銘哥" , "2019/01/05" ) ) ; 
a . addElement ( new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2018/11/01" ) ) ; 
a . addElement ( new EnglishLetter ( "Professor" , "Liu" , "I am sorry!" , "K. Ming" , "2018/07/05" ) ) ; 
int min , locate ; 
for ( int k = 0 ; k < a . size ( ) ; k ++ ) { 
min = a . elementAt ( k ) . c ; 
locate = k ; 
for ( int y = k ; y < a . size ( ) ; y ++ ) { 
min = Math . min ( min , a . elementAt ( y ) . c ) ; 
if ( a . elementAt ( k ) . c > a . elementAt ( y ) . c ) 
locate = y ; 
} 
Collections . swap ( a , k , locate ) ; 
} 
for ( int k = 0 ; k < a . size ( ) ; k ++ ) { 
a . elementAt ( k ) . display ( ) ; 
} 
} 
} 
