public class Evaluation { 
int flag ; 
protected String id ; 
protected String name ; 
protected Double score ; 
protected Double p ; 
protected String s1 ; 
protected String s2 ; 
Evaluation ( int f , String ID , String n , Double sc , Double P , String S1 , String S2 ) { 
flag = f ; 
id = ID ; 
name = n ; 
score = sc ; 
p = P ; 
s1 = S1 ; 
s2 = S2 ; 
} 
void print ( ) { 
System . out . println ( "您輸入的是:" ) ; 
System . out . println ( "種類:" + flag ) ; 
System . out . println ( "學生學號:" + id ) ; 
System . out . println ( "學生姓名:" + name ) ; 
System . out . println ( "成績:" + score ) ; 
System . out . println ( "成績比重:" + p ) ; 
if ( flag == 1 ) { 
System . out . println ( "考試日期:" + s1 ) ; 
System . out . println ( "考試範圍:" + s2 ) ; 
} 
else if ( flag == 2 ) { 
System . out . println ( "截止日期:" + s1 ) ; 
System . out . println ( "作業描述:" + s2 ) ; 
} 
else { 
System . out . println ( "報告內容:" + s1 ) ; 
System . out . println ( "參考文獻:" + s2 ) ; 
} 
} 
} 
