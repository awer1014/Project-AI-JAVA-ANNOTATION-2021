public class Kind { 
private static int count ; 
private String aa , ab ; 
private Teacher Teacher ; 
public Kind ( String p , String n ) { 
aa = p ; 
ab = n ; 
count ++ ; 
} 
public void display ( int n ) { 
if ( n == 1 ) { 
System . out . println ( "考試日期: " + aa ) ; 
System . out . println ( "考試範圍: " + ab ) ; 
} 
else if ( n == 2 ) { 
System . out . println ( "截止日期: " + aa ) ; 
System . out . println ( "作業描述: " + ab ) ; 
} 
else if ( n == 3 ) { 
System . out . println ( "報告內容: " + aa ) ; 
System . out . println ( "參考文獻: " + ab ) ; 
} 
} 
public static int getcount ( ) { 
return count ; 
} 
} 
