import java . util . Scanner ; 
public class Pet { 
public static int count = 0 ; 
public String pid ; 
public String name ; 
public String type ; 
Keeper keeper ; 
void speak ( ) { 
if ( type . equals ( "0" ) ) 
System . out . println ( "Pet's speak: " + "喵喵" ) ; 
else 
System . out . println ( "Pet's speak: " + "汪汪" ) ; 
} 
void display ( ) { 
System . out . println ( "Pet's pid: " + pid ) ; 
System . out . println ( "Pet's name: " + name ) ; 
System . out . println ( "Pet's type: " + type ) ; 
keeper . display ( ) ; 
} 
Pet ( String p , String n , String t , Keeper keeper ) { 
pid = p ; 
name = n ; 
type = t ; 
this . keeper = keeper ; 
count ++ ; 
} 
} 
