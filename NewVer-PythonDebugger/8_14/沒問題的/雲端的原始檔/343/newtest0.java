import java . util . Random ; 
class test { 
static int Random ( int sp , int su , int fa , int wi ) { 
int Sp = sp ; 
int Su = su ; 
int F = fa ; 
int W = wi ; 
return 0 ; 
} 
public static void main ( String [ ] args ) { 
Random ran = new Random ( ) ; 
int sp = Integer . parseInt ( args [ 0 ] ) ; 
int su = Integer . parseInt ( args [ 1 ] ) ; 
int fa = Integer . parseInt ( args [ 2 ] ) ; 
int wi = Integer . parseInt ( args [ 3 ] ) ; 
System . out . println ( ran . nextInt ( 4 ) ) ; 
} 
} 
