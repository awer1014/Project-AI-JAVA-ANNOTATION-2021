public class Student { 
private String evaluation , stid , stname , stnumber , stnumberc ; 
Student ( String sevaluation , String sstid , String sstname , String sstnumber , String sstnumberc ) { 
evaluation = sevaluation ; 
stid = sstid ; 
stname = sstname ; 
stnumber = sstnumber ; 
stnumberc = sstnumberc ; 
} 
void print ( ) { 
System . out . println ( "種類：" + evaluation ) ; 
System . out . println ( "學生學號：" + stid ) ; 
System . out . println ( "學生姓名：" + stname ) ; 
System . out . println ( "成績：" + stnumber ) ; 
System . out . println ( "成績比重：" + stnumberc ) ; 
} 
} 
