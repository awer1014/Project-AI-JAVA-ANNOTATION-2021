public class Teacher { 
private String id , score , name , cc ; 
public Teacher ( String n , String i , String t , String j ) { 
id = i ; 
score = t ; 
name = n ; 
cc = j ; 
} 
public void display ( ) { 
System . out . println ( "學生名稱: " + name ) ; 
System . out . println ( "學生學號: " + id ) ; 
System . out . println ( "學生成績: " + score ) ; 
System . out . println ( "成績比重: " + cc ) ; 
} 
} 
