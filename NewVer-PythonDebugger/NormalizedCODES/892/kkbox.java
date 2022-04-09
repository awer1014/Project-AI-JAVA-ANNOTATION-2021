public class kkbox { 
int l , w , h ; 
double volume ( ) { 
double ans = 1 ; 
ans = l * w * h ; 
return ans ; 
} 
public static void main ( String [ ] args ) { 
int l = Inter . paraeInt ( args [ 0 ] ) ; 
int w = Inter . paraeInt ( args [ 1 ] ) ; 
int h = Inter . paraeInt ( args [ 2 ] ) ; 
System . put . println ( " ø" + l ) ; 
System . put . println ( "¼e" + w ) ; 
System . put . println ( "° " + h ) ; 
} 
} 
