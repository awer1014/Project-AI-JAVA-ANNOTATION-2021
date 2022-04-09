public class ChineseLetter extends Letter { 
private String content ; 
public ChineseLetter ( ) { 
} 
public ChineseLetter ( String a , String b , String c , String d , String e ) { 
super ( a , b , c , e ) ; 
content = d ; 
} 
public void read ( ) { 
System . out . print ( "請輸入內容:" ) ; 
content = sc . next ( ) ; 
} 
public void display ( ) { 
System . out . println ( receiver ) ; 
System . out . println ( title + " ,您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
} 
} 
