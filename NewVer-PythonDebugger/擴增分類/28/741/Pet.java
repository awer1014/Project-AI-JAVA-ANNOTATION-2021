public class Pet { 
private String name ; 
private String pid ; 
private String type ; 
public Pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
public void speek ( String type ) { 
if ( type == "貓" ) { 
System . out . println ( "您寵物的叫聲是:喵喵" ) ; 
} 
if ( type == "狗" ) { 
System . out . println ( "您寵物的叫聲是:汪汪" ) ; 
} 
} 
public void display ( ) { 
System . out . println ( "編號:" + pid ) ; 
System . out . println ( "名稱:" + name ) ; 
System . out . println ( "種類:" + type ) ; 
} 
public String getPid ( ) { 
return pid ; 
} 
public String getName ( ) { 
return name ; 
} 
public String getType ( ) { 
return type ; 
} 
} 
