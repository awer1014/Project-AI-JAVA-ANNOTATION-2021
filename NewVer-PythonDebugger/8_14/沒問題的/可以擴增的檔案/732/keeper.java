class keeper { 
String id ; 
String tel ; 
public keeper ( String id , String tel ) { 
this . id = id ; 
this . tel = tel ; 
} 
public void display ( ) { 
System . out . print ( "身分證字號" + id ) ; 
System . out . print ( "電話" + tel ) ; 
} 
public String getid ( ) { 
return id ; 
} 
public String gettel ( ) { 
return tel ; 
} 
} 
