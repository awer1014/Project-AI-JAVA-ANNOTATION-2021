class PET { 
int count ; 
String pid ; 
String name ; 
String type ; 
PET ( String Qpid , String Qname , String Qtype ) { 
pid = Qpid ; 
name = Qname ; 
type = Qtype ; 
} 
public static void main ( String [ ] args ) { 
String pid = args [ 1 ] ; 
String name = args [ 0 ] ; 
String type = args [ 2 ] ; 
String id = args [ 3 ] ; 
String tel = args [ 4 ] ; 
PET PNT = new PET ( pid , name , type ) ; 
System . out . println ( "請輸入寵物種類: " + PNT . pid ) ; 
System . out . println ( "請輸入寵物編號: " + PNT . name ) ; 
System . out . println ( "請輸入寵物名稱: " + PNT . type ) ; 
} 
} 
