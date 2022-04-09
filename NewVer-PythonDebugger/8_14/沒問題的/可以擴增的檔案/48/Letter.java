abstract public class Letter { 
protected String title ; 
protected String receiver ; 
protected String content ; 
protected String sender ; 
protected String date ; 
public Letter ( ) { 
} 
public Letter ( String a , String b , String c , String d , String e ) { 
title = a ; 
receiver = b ; 
content = c ; 
sender = d ; 
date = e ; 
} 
abstract public void display ( ) ; 
} 
