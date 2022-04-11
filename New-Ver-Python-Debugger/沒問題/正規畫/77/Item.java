public class Item { 
private static double total_money = 0 ; 
private String id , name ; 
private double money , discnt ; 
private int cnt ; 
Item ( ) { 
} 
Item ( String iid , String nname , double mmoney , int ccnt , double ddiscnt ) { 
id = iid ; 
name = nname ; 
money = mmoney ; 
cnt = ccnt ; 
discnt = ddiscnt ; 
total_money += money * discnt * cnt ; 
} 
void print ( ) { 
System . out . println ( "教授" + name ) ; 
System . out . println ( "王" + money ) ; 
System . out . println ( "很高興跟您連絡" + cnt ) ; 
System . out . println ( "銘哥" + discnt ) ; 
} 
} 
