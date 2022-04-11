import java . util . Scanner ; 
public class Main { 
public static void main ( String [ ] args ) { 
Scanner scan = new Scanner ( System . in ) ; 
boolean b = true ; 
while ( b ) { 
System . out . println ( "請輸入種類,0為結束(測驗、作業、心得報告)：" ) ; 
String ptype = scan . nextLine ( ) ; 
if ( ptype . equals ( "0" ) ) { 
b = false ; 
break ; 
} 
if ( ptype . equals ( "測驗" ) || ptype . equals ( "作業" ) || ptype . equals ( "心得報告" ) ) { 
System . out . println ( "請輸入學生學號：" ) ; 
String pnum = scan . nextLine ( ) ; 
System . out . println ( "請輸入學生姓名：" ) ; 
String pname = scan . nextLine ( ) ; 
System . out . println ( "請輸入成績：" ) ; 
double ps = Double . parseDouble ( scan . nextLine ( ) ) ; 
System . out . println ( "請輸入成績比重：" ) ; 
double pp = Double . parseDouble ( scan . nextLine ( ) ) ; 
String pend = null ; 
String pdis = null ; 
String pt = null ; 
String pr = null ; 
String pinfo = null ; 
String pinfo_dis = null ; 
if ( ptype . equals ( "測驗" ) ) { 
System . out . println ( "請輸入考試日期：" ) ; 
pt = scan . nextLine ( ) ; 
System . out . println ( "請輸入考試範圍：" ) ; 
pr = scan . nextLine ( ) ; 
} 
if ( ptype . equals ( "作業" ) ) { 
System . out . println ( "請輸入截止日期：" ) ; 
pend = scan . nextLine ( ) ; 
System . out . println ( "請輸入作業描述：" ) ; 
pdis = scan . nextLine ( ) ; 
} 
if ( ptype . equals ( "心得報告" ) ) { 
System . out . println ( "請輸入報告內容：" ) ; 
pinfo = scan . nextLine ( ) ; 
System . out . println ( "請輸入參考文獻：" ) ; 
pinfo_dis = scan . nextLine ( ) ; 
} 
System . out . println ( "=============" ) ; 
Test t = null ; 
System . out . println ( "" ) ; 
System . out . println ( "您輸入的是：" ) ; 
if ( ptype . equals ( "測驗" ) ) 
t = new Exam ( pnum , pname , ps , pp ) ; 
else if ( ptype . equals ( "作業" ) ) 
t = new HomeWork ( pnum , pname , ps , pp ) ; 
else if ( ptype . equals ( "心得報告" ) ) 
t = new Report ( pnum , pname , ps , pp ) ; 
if ( t != null ) { 
t . print ( ) ; 
if ( ptype . equals ( "測驗" ) ) { 
System . out . println ( "考試日期：" + pt ) ; 
System . out . println ( "考試範圍：" + pr ) ; 
} 
else if ( ptype . equals ( "作業" ) ) { 
System . out . println ( "截止日期：" + pend ) ; 
System . out . println ( "作業描述：" + pdis ) ; 
} 
else if ( ptype . equals ( "心得報告" ) ) { 
System . out . println ( "報告內容：" + pinfo ) ; 
System . out . println ( "參考文獻：" + pinfo_dis ) ; 
} 
System . out . println ( "" ) ; 
t . display ( ) ; 
System . out . println ( "=============" ) ; 
} 
} 
} 
} 
} 
