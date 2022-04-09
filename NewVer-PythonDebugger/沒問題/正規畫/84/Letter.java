public abstract class Letter { 
public static String title ; 
public static String receiver ; 
public static String content ; 
public static String sender ; 
public static String date ; 
public Letter ( String a , String b , String c , String d , String e ) { 
title = a ; 
receiver = b ; 
content = c ; 
sender = d ; 
date = e ; 
} 
abstract public void display ( ) ; 
public String getTitle ( ) { 
return title ; 
} 
public String getreceiver ( ) { 
return receiver ; 
} 
public String getsender ( ) { 
return sender ; 
} 
} 
