import java . util . Scanner ; 
public class kkbox { 
int length ; 
int width ; 
int height ; 
kkbox ( int length , int width , int height ) { 
this . length = length ; 
this . width = width ; 
this . height = height ; 
} 
float volume ( ) { 
return length * width * height ; 
} 
float surfaceArea ( ) { 
return 2 * length * width + width * height + height * length ; 
} 
public static void main ( String [ ] args ) { 
int l , w , h ; 
Scanner scanner = new Scanner ( System . in ) ; 
l = scanner . nextInt ( ) ; 
w = scanner . nextInt ( ) ; 
h = scanner . nextInt ( ) ; 
kkbox hh = new kkbox ( l , w , h ) ; 
System . out . println ( "體積:" + hh . volume ( ) ) ; 
System . out . println ( "表面積:" + hh . surfaceArea ( ) ) ; 
} 
} 
