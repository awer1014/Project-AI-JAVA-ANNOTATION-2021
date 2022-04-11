import java . util . Scanner ; 
public class Pet { 
static int count ; 
private String pid ; 
private String name ; 
private String type ; 
private int i ; 
private String sound [ ] = { "旺旺" , "喵喵" } ; 
Pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
public void display ( ) { 
System . out . println ( "寵物種類； " + pid ) ; 
System . out . println ( "寵物編號： " + type ) ; 
System . out . println ( "寵物名稱： " + name ) ; 
if ( pid == "貓" ) 
i = 1 ; 
} 
} 
