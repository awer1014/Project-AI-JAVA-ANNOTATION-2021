public class Pet { 
private static int count ; 
private String pid , name , type ; 
private Keeper keeper ; 
public Pet ( String p , String n , String t , Keeper k ) { 
pid = p ; 
name = n ; 
type = t ; 
count ++ ; 
keeper = k ; 
} 
public void speak ( ) { 
System . out . print ( "您的寵物叫聲是 : " ) ; 
if ( name == "狗" ) 
System . out . println ( "汪汪" ) ; 
else 
System . out . println ( "喵喵" ) ; 
} 
public void display ( ) { 
System . out . println ( "寵物種類 : " + type ) ; 
System . out . println ( "寵物編號 : " + pid ) ; 
System . out . println ( "寵物名稱 : " + name ) ; 
} 
public static int getcount ( ) { 
return count ; 
} 
} 
