import java . util . Scanner ; 
public class Keeper { 
static String id ; 
static String tel ; 
public Keeper ( String id , String tel ) { 
this . id = id ; 
this . tel = tel ; 
} 
public String getId ( ) { 
return id ; 
} 
public String getTel ( ) { 
return tel ; 
} 
public void display ( String id , String tel ) { 
System . out . println ( "身分證:" + id ) ; 
System . out . println ( "電話:" + tel ) ; 
} 
} 
