public class student { 
private String id , name ; 
private double grade , gradepp ; 
student ( String i , String n , double g , double p ) { 
id = i ; 
name = n ; 
grade = g ; 
gradepp = p ; 
} 
public void display ( ) { 
System . out . println ( "學生學號：" + id ) ; 
System . out . println ( "學生姓名：" + name ) ; 
System . out . println ( "學生成績：" + grade ) ; 
System . out . println ( "成績比重：" + gradepp ) ; 
} 
} 
