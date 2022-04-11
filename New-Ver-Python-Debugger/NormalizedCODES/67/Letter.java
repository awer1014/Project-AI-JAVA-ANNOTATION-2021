public abstract class Letter implements Comparable < Letter > { 
protected String title ; 
protected String receiver ; 
protected String content ; 
protected String sender ; 
protected String date ; 
public int compareTo ( Letter o ) { 
String [ ] date1 = date . split ( "/" ) , date2 = o . getDate ( ) . split ( "/" ) ; 
for ( int i = 0 ; i < date1 . length ; i ++ ) { 
if ( Integer . parseInt ( date1 [ i ] ) > Integer . parseInt ( date2 [ i ] ) ) return 1 ; 
else if ( Integer . parseInt ( date1 [ i ] ) < Integer . parseInt ( date2 [ i ] ) ) return - 1 ; 
} 
return 0 ; 
} 
public Letter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
String getSender ( ) { 
return sender ; 
} 
String getReceiver ( ) { 
return receiver ; 
} 
String getTitle ( ) { 
return title ; 
} 
String getDate ( ) { 
return date ; 
} 
public abstract void display ( ) ; 
} 
