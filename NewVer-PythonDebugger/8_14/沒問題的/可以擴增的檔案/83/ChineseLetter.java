public class ChineseLetter extends Letter { 
public ChineseLetter ( String a , String b , String c , String d , String e ) { 
super ( a , b , c , d , e ) ; 
} 
public void display ( ) { 
System . out . println ( receiver + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + "敬上" ) ; 
System . out . println ( date ) ; 
} 
} 
