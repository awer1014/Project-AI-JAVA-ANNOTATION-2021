abstract public class Letter { 
protected String title ; 
protected String receiver ; 
protected String content ; 
protected String sender ; 
protected String date ; 
public Letter ( String t , String r , String c , String s , String d ) { 
title = t ; 
receiver = r ; 
content = c ; 
sender = s ; 
date = d ; 
} 
public void display ( int flag ) { 
if ( flag == 1 ) { 
System . out . println ( receiver + ' ' + title + ",您好" ) ; 
System . out . println ( content ) ; 
System . out . println ( sender + " 敬上" ) ; 
System . out . println ( date ) ; 
} 
else { 
System . out . println ( "Dear " + title + " " + receiver + ',' ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
} 
} 
