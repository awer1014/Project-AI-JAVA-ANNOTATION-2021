import java . util . Scanner ; 
abstract public class Letter { 
protected String receiver , sender , title , date ; 
protected Scanner sc = new Scanner ( System . in ) ; 
public Letter ( ) { 
} 
public Letter ( String a , String b , String c , String d ) { 
receiver = a ; 
sender = b ; 
date = c ; 
title = d ; 
} 
public void readFull ( ) { 
System . out . print ( "請輸入稱呼:" ) ; 
title = sc . next ( ) ; 
System . out . print ( "請輸入姓氏:" ) ; 
receiver = sc . next ( ) ; 
System . out . print ( "請輸入寄件人:" ) ; 
sender = sc . next ( ) ; 
System . out . print ( "請輸入日期:" ) ; 
date = sc . next ( ) ; 
} 
} 
