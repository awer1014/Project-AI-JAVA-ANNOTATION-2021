import java . util . Scanner ; 
public class Pet { 
public static int count ; 
public String pid ; 
public String name ; 
public String type ; 
public Pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
count ++ ; 
} 
public void setName ( int a ) { 
if ( a == 1 ) 
this . name = "狗狗" ; 
if ( a == 2 ) 
this . name = "貓貓" ; 
} 
public void speak ( int n ) { 
if ( n == 1 ) 
this . name = "喵喵" ; 
if ( n == 2 ) 
this . name = "旺旺" ; 
} 
void display ( ) { 
System . out . println ( "寵物編號" + pid ) ; 
System . out . println ( "寵物名稱" + name ) ; 
System . out . println ( "寵物類別" + type ) ; 
} 
} 
