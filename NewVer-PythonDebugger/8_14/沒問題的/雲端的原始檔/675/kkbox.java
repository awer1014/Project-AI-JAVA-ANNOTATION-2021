public class kkbox { 
private double length ; 
private double width ; 
private double height ; 
public kkbox ( ) { 
this . height = height ; 
this . length = length ; 
this . width = width ; 
} 
public static void getSurfaceArea ( double length , double width , double height ) { 
double SurfaceArea = 2 * length * width + width * height + length * height ; 
System . out . println ( "長:" + length ) ; 
System . out . println ( "高:" + height ) ; 
System . out . println ( "寬:" + width ) ; 
System . out . println ( "表面積體積為: " ) ; 
System . out . printf ( "%.2f" , SurfaceArea ) ; 
} 
public static void getvolume ( double length , double width , double height ) { 
double volume = length * width * height ; 
double SurfaceArea = 2 * length * width + width * height + length * height ; 
System . out . println ( "長:" + length ) ; 
System . out . println ( "高:" + height ) ; 
System . out . println ( "寬:" + width ) ; 
System . out . println ( "表面積體積為: " ) ; 
System . out . printf ( "%.2f" , SurfaceArea ) ; 
System . out . println ( "體積為:" ) ; 
System . out . printf ( "%.2f" , volume ) ; 
} 
} 
