import java . util . Scanner ; 
public class Pet { 
public static int count = 0 ; 
public String pid ; 
public String name ; 
public String type ; 
void speak ( ) { 
if ( type == "貓" ) { 
System . out . println ( "Pet's speak: " + "喵喵" ) ; 
} 
else { 
System . out . println ( "Pet's speak: " + "汪汪" ) ; 
} 
} 
void display ( ) { 
System . out . println ( "Pet's pid: " + pid ) ; 
System . out . println ( "Pet's name: " + name ) ; 
System . out . println ( "Pet's type: " + type ) ; 
} 
Pet ( String p , String n , String t ) { 
pid = p ; 
name = n ; 
type = t ; 
} 
} 
