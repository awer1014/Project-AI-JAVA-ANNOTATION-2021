abstract public class Score { 
protected int grade ; 
protected double percentage ; 
protected String date , scale , kind ; 
public Score ( ) { 
} 
public Score ( int ggrade , double ppercentage , String ddate , String sscale ) { 
grade = ggrade ; 
percentage = ppercentage ; 
date = ddate ; 
scale = sscale ; 
} 
public void print ( ) { 
System . out . println ( "種類: " + kind ) ; 
System . out . println ( "成績：" + grade ) ; 
System . out . println ( "成績比重：" + percentage ) ; 
System . out . println ( "考試日期: " + date ) ; 
System . out . println ( "考試範圍: " + scale ) ; 
} 
} 
