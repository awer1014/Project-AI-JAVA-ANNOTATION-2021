public class KKBOX { 
static double length = 1 , width = 2 , height = 3 , a1 , a2 ; 
public double volume = length * width * height ; 
public double surfaceArea = 2 * length * width + width * height + height * length ; 
public double getvolume ( ) { 
return volume ; 
} 
public double getsurfaceArea ( ) { 
return surfaceArea ; 
} 
public void setvolume ( double volume ) { 
this . volume = volume ; 
} 
public void setsurfaceArea ( double surfaceArea ) { 
this . surfaceArea = surfaceArea ; 
} 
public static void main ( String [ ] args ) { 
double length = Double . parseDouble ( args [ 0 ] ) ; 
double width = Double . parseDouble ( args [ 1 ] ) ; 
double height = Double . parseDouble ( args [ 2 ] ) ; 
KKBOX TKKBOX = new KKBOX ( ) ; 
System . out . println ( "Åé¿n = " + TKKBOX . getvolume ( ) ) ; 
System . out . println ( "ªí­±¿n = " + TKKBOX . getsurfaceArea ( ) ) ; 
} 
} 
