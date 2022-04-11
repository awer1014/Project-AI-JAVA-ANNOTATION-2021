abstract public class require { 
private String id , date , type ; 
private student student ; 
public require ( student s , String i , String d , String t ) { 
student = s ; 
id = i ; 
date = d ; 
type = t ; 
} 
public void display ( ) { 
if ( id . equals ( "測驗" ) ) { 
System . out . println ( "測驗: " + id ) ; 
System . out . println ( "考試日期: " + date ) ; 
System . out . println ( "考試範圍: " + type ) ; 
} 
else if ( id . equals ( "作業" ) ) { 
System . out . println ( "測驗: " + id ) ; 
System . out . println ( "截止日期: " + date ) ; 
System . out . println ( "作業描述: " + type ) ; 
} 
else if ( id . equals ( "心得報告" ) ) { 
System . out . println ( "測驗: " + id ) ; 
System . out . println ( "報告內容: " + date ) ; 
System . out . println ( "參考文獻: " + type ) ; 
} 
} 
} 
