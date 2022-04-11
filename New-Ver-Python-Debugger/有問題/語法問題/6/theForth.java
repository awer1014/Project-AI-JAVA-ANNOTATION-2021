import java . util . Arrays ; 
public class theForth implements Comparable { 
private String date ; 
Letter [ ] list ; 
public String getDate ( ) { 
return date ; 
} 
@ Override ; 
public int compareTo ( Object o ) { 
Letter obj = ( Letter ) o ; 
if ( this . getDate ( ) . compareTo ( obj . getDate ( ) ) < 0 ) { 
return 1 ; 
} 
else if ( this . getDate ( ) . compareTo ( obj . getDate ( ) ) < 0 ) { 
return - 1 ; 
} 
else { 
return 2 ; 
} 
} 
} 
