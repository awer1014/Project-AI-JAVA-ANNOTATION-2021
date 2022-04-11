public class Keeper { 
private String id , tel , name ; 
public Keeper ( String n , String i , String t ) { 
id = i ; 
tel = t ; 
name = n ; 
} 
public void display ( ) { 
System . out . println ( "飼主名稱 : " + name ) ; 
System . out . println ( "飼主身分證字號 : " + id ) ; 
System . out . println ( "飼主電話 : " + tel ) ; 
} 
} 
