import java . util . Scanner ; 
public class Pet { 
String pid ; 
String name ; 
String type ; 
public Pet ( String t , String p , String n ) { 
this . type = t ; 
this . pid = p ; 
this . name = n ; 
} 
public String getname ( ) { 
return name ; 
} 
void setname ( String n ) { 
name = n ; 
} 
public String getpid ( ) { 
return pid ; 
} 
void setpid ( String p ) { 
pid = p ; 
} 
public String gettype ( ) { 
return type ; 
} 
void settype ( String t ) { 
type = t ; 
} 
public void display ( ) { 
System . out . println ( "寵物種類:" + gettype ( ) ) ; 
System . out . println ( "寵物編號:" + getpid ( ) ) ; 
System . out . println ( "寵物名稱:" + getname ( ) ) ; 
} 
void speak ( String N ) { 
name = N ; 
if ( N == "狗" ) 
System . out . println ( "您寵物的叫聲是:汪汪" ) ; 
else if ( N == "貓" ) 
System . out . println ( "您的寵物的叫聲是:喵喵" ) ; 
} 
} 
