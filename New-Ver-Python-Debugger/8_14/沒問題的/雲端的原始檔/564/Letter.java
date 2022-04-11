public class Letter implements Comparable { 
private int x ; 
public static Evaluation [ ] list = { new ChineseLetter ( "教授" , "朱" , "很高興跟您連絡" , "豐緒" , "2019/01/05" ) , new EnglishLetter ( "Professor" , "Wang" , "I am glad to write to you!" , "J. J. Lin" , "2019/01/05" ) , new EnglishLetter ( "Professor" , "Liu" , "I am sorry!" , "K. Ming" , "2018/07/05" ) } ; 
Letter ( int x ) { 
this . x = x ; 
} 
public void display ( int i ) { 
list [ i ] . Display ( ) ; 
} 
public int compareTo ( Object o ) { 
Letter obj = ( Letter ) o ; 
if ( this . x > obj . x ) return 1 ; 
else return - 1 ; 
} 
public static void main ( String [ ] args ) { 
int i ; 
for ( i = list . length - 1 ; i >= 0 ; i -- ) { 
int p = i ; 
Letter c1 = new Letter ( i ) ; 
Letter c2 = new Letter ( p - 1 ) ; 
if ( c1 . compareTo ( c2 ) == 1 ) 
c1 . display ( i ) ; 
} 
} 
} 
