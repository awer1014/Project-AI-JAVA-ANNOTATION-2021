public class length { 
double a , b , c ; 
length ( double x , double y , double z ) { 
this . a = x ; 
this . b = y ; 
this . c = z ; 
} 
volume ( ) { 
return a * b ; 
} 
double surfaceArea ( ) { 
return 2 * a + 2 * b + 2 * c ; 
} 
public static void main ( String [ ] args ) { 
length len1 = new length ( a , b , c ) ; 
System . out . println ( a ) ; 
System . out . println ( b ) ; 
System . out . println ( c ) ; 
System . out . println ( len1 . volume ) ; 
System . out . println ( len1 . surfaceArea ) ; 
} 
} 
