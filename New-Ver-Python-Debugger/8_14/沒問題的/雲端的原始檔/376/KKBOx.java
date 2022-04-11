public class KKBOx { 
double l ; 
double w ; 
double h ; 
double volume ( ) { 
double ans = l * w * h ; 
return ans ; 
} 
public static void main ( Double [ ] n ) { 
KKBOx kb = new KKBOx ( ) ; 
kb . l = 2 ; 
kb . w = 3 ; 
kb . h = 4 ; 
System . out . println ( kb . volume ( ) ) ; 
} 
} 
