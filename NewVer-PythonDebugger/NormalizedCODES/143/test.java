public class test { 
static String name ; 
static int age ; 
static String country ; 
static void selfIntroduce ( ) { 
System . out . println ( "Hello!I am " + name + " aged " + age + ".I come from " + country + "." ) ; 
} 
public static void main ( String [ ] args ) { 
name = args [ 0 ] ; 
age = Integer . parseInt ( args [ 1 ] ) ; 
country = args [ 2 ] ; 
selfIntroduce ( ) ; 
} 
} 
