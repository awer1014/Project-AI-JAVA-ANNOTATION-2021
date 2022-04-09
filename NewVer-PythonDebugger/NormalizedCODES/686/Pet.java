public class Pet { 
private String pid ; 
private String name ; 
private String type ; 
private String keeper ; 
public Pet ( String pid , String name , String type ) { 
this . pid = pid ; 
this . name = name ; 
this . type = type ; 
} 
public void keep ( String keeper ) { 
this . keeper = keeper ; 
} 
public void display ( ) { 
System . out . println ( "寵物種類:" + this . type ) ; 
System . out . println ( "寵物編號：" + this . pid ) ; 
System . out . println ( "寵物名稱：" + this . name ) ; 
} 
public void speak ( ) { 
if ( type == "狗" ) { 
System . out . println ( "您的寵物叫聲是：汪汪" ) ; 
} 
else 
System . out . println ( "您的寵物叫聲是：喵喵" ) ; 
} 
} 
