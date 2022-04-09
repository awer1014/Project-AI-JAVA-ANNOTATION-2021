public class ChineseLetter extends Letter { 
ChineseLetter ( String a , String b , String c , String d , String e ) { 
super ( a , b , c , d , e ) ; 
} 
public void display ( ) { 
System . out . print ( receiver + title + " " + "您好" + "\r\n" ) ; 
System . out . print ( content + "\r\n" ) ; 
System . out . print ( sender + " " + "敬上" + "\r\n" ) ; 
System . out . print ( date + "\r\n" ) ; 
} 
} 
