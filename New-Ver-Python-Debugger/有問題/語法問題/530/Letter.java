abstract public class Letter implements Comparable { 
protected String title , receiver , content , sender , date ; 
public Letter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
public String getSender ( ) { 
return sender ; 
} 
public String getReceiver ( ) { 
return receiver ; 
} 
public String getTitle ( ) { 
return title ; 
} 
public String getDate ( ) { 
return date ; 
} 
abstract public void display ( ) ; 
@ Override ; 
public int compareTo ( Object o ) { 
int ans = 0 ; 
Letter obj = ( Letter ) o ; 
String d1 = this . getDate ( ) ; 
String d2 = obj . getDate ( ) ; 
int n1 = ( int ) ( d1 . charAt ( 2 ) ) * 10 + ( int ) ( d1 . charAt ( 3 ) ) ; 
int n2 = ( int ) ( d2 . charAt ( 2 ) ) * 10 + ( int ) ( d2 . charAt ( 3 ) ) ; 
if ( n1 > n2 ) return 1 ; 
else if ( n1 < n2 ) return - 1 ; 
else { 
n1 = ( int ) ( d1 . charAt ( 5 ) ) * 10 + ( int ) ( d1 . charAt ( 6 ) ) ; 
n2 = ( int ) ( d2 . charAt ( 5 ) ) * 10 + ( int ) ( d2 . charAt ( 6 ) ) ; 
if ( n1 > n2 ) return 1 ; 
else if ( n1 < n2 ) return - 1 ; 
else { 
n1 = ( int ) ( d1 . charAt ( 8 ) ) * 10 + ( int ) ( d1 . charAt ( 9 ) ) ; 
n2 = ( int ) ( d2 . charAt ( 8 ) ) * 10 + ( int ) ( d2 . charAt ( 9 ) ) ; 
if ( n1 > n2 ) return 1 ; 
else if ( n1 < n2 ) return - 1 ; 
else return 0 ; 
} 
} 
} 
} 
