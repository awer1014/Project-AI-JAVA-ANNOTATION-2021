public class Pet { 
private int count , flag ; 
String name , type ; 
String pid ; 
public Pet ( String t , String p , String n ) { 
this . pid = p ; 
this . name = n ; 
this . type = t ; 
} 
void speak ( ) { 
if ( flag == 1 ) 
System . out . println ( "旺旺" ) ; 
else 
System . out . println ( "喵喵" ) ; 
} 
void display ( ) { 
System . out . println ( "請輸入寵物種類:" + "狗" ) ; 
} 
} 
