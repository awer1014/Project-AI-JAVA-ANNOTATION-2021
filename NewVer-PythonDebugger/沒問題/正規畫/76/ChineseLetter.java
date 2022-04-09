import java . util . * ; 
public class ChineseLetter { 
private String id , date , coin ; 
private Item [ ] it = new Item [ 10 ] ; 
private int num_i ; 
ChineseLetter ( String title , String receiver , String content , Item [ ] iit , int num_item ) { 
id = title ; 
date = receiver ; 
num_i = num_item ; 
for ( int i = 0 ; i < num_item ; i ++ ) 
it [ i ] = iit [ i ] ; 
} 
void print ( ) { 
System . out . println ( id + " 教授, 您好" ) ; 
System . out . println ( "很高興跟您連絡" ) ; 
System . out . println ( date + "敬上" ) ; 
} 
} 
