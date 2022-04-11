public class Person { 
static String name ; 
static int weight ; 
static void selfIntroduce ( ) { 
System . out . println ( "Hi!I am " + name + "  weights  " + weight ) ; 
} 
public static void main ( String [ ] args ) { 
name = args [ 0 ] ; 
weight = Integer . parseInt ( args [ 1 ] ) ; 
Person Kent = new Person ( name , weight ) ; 
selfIntroduce ( ) ; 
} 
} 
