public class Keeper { 
private String id ; 
private String tel ; 
Keeper ( String id , String tel ) { 
this . id = id ; 
this . tel = tel ; 
} 
public void display1 ( ) { 
System . out . println ( "飼主身分證字號：" + id ) ; 
System . out . println ( "飼主電話：" + tel ) ; 
} 
public String getid ( ) { 
return id ; 
} 
public String gettel ( ) { 
return tel ; 
} 
} 
