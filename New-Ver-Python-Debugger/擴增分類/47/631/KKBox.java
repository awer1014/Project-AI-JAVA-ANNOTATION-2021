public class KKBox { 
static void volume ( double x , double y , double z ) { 
double sum ; 
sum = x * y ; 
sum *= z ; 
System . out . println ( "體積:" , volume ) ; 
} 
static void surfaceArea ( double x , double y , double z ) { 
double num ; 
num = 2 * x * y + y * z + z * x ; 
System . out . println ( "表面積:" , surfaceArea ) ; 
} 
public static void main ( String [ ] args ) { 
String data1 = args [ 0 ] ; 
String data2 = args [ 1 ] ; 
String data3 = args [ 2 ] ; 
double length = Double . parseDouble ( data1 ) ; 
double width = Double . parseDouble ( data2 ) ; 
double height = Double . parseDouble ( data3 ) ; 
System . out . println ( "請輸入長:" + length ) ; 
System . out . println ( "請輸入寬:" + width ) ; 
System . out . println ( "請輸入高:" + height ) ; 
System . out . println ( "體積:" , volume ) ; 
System . out . println ( "表面積:" , surfaceArea ) ; 
} 
} 
