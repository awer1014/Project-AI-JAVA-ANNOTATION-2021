public class KKbox { 
int length ; 
int width ; 
int height ; 
int volume , surfaceArea ; 
KKbox ( int l , int w , int h ) { 
length = l ; 
width = w ; 
height = h ; 
volume = length * width * height ; 
surfaceArea = 2 * length * width + width * height + height * length ; 
} 
void selfIntroduce ( ) { 
System . out . println ( "體積: " + volume ) ; 
System . out . println ( "表面積: " + surfaceArea ) ; 
} 
public static void main ( String [ ] args ) { 
System . out . println ( selfIntroduce ) ; 
} 
} 
