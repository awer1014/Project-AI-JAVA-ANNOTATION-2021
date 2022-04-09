public class TEST { 
private String grade , name , date , range , number , p ; 
public TEST ( String nnumber , String nname , String ggrade , String pp , String ddate , String rrange ) { 
nnumber = number ; 
nname = name ; 
ggrade = grade ; 
ddate = date ; 
rrange = range ; 
pp = p ; 
} 
void print ( ) { 
System . out . println ( "您輸入的種類資料如下：" ) ; 
System . out . println ( "學生學號：" + number ) ; 
System . out . println ( "學生姓名：" + name ) ; 
System . out . println ( "成績：" + grade ) ; 
System . out . println ( "成績比重：" + p ) ; 
System . out . println ( "考試日期：" + date ) ; 
System . out . println ( "範圍：" + range ) ; 
} 
} 
