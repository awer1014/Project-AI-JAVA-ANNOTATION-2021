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
} 
public void speak ( ) { 
System . out . println ( "您的寵物叫聲是：" ) ; 
if ( type == "狗" ) 
System . out . println ( "汪汪" ) ; 
} 
} 
