public class Keeper { 
private static String id ; 
private static String tel ; 
Keeper ( String id , String tel ) { 
this . id = id ; 
this . tel = tel ; 
Keeper d = new Keeper ( "A123456" , "02-2586777" ) ; 
Keeper c = new Keeper ( "F123456" , "07-2856977" ) ; 
} 
public static void display2 ( ) { 
System . out . println ( "飼主身分證字號:" + id ) ; 
System . out . println ( "飼主電話：" + tel ) ; 
} 
} 
