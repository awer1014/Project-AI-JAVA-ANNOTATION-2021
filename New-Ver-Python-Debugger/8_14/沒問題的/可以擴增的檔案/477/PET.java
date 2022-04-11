import java . util . Scanner ; 
class PET { 
static int count ; 
String pid ; 
String name ; 
String type ; 
static void speak ( String s1 ) { 
String strData5 ; 
if ( s1 == "狗" ) 
System . out . println ( "你的寵物叫聲為:汪汪" ) ; 
else if ( s1 == "貓" ) 
System . out . println ( "你的寵物叫聲為:喵喵" ) ; 
} 
static void display ( String strData , String strData1 , String strData2 , String strData3 , String strData4 ) { 
System . out . println ( "你輸入的寵物種類為:" + strData ) ; 
System . out . println ( "你輸入的寵物編號為:" + strData1 ) ; 
System . out . println ( "你輸入的寵物名稱為:" + strData2 ) ; 
System . out . println ( "你輸入的飼主身分證字號為:" + strData3 ) ; 
System . out . println ( "你輸入的電話為:" + strData4 ) ; 
} 
public static void main ( String [ ] args ) { 
String strData , strData1 , strData2 , strData3 , strData4 ; 
int num ; 
Scanner scanner = new Scanner ( System . in ) ; 
strData = scanner . nextLine ( ) ; 
System . out . println ( "請輸入寵物種類：" ) ; 
speak ( strData ) ; 
System . out . println ( "請輸入寵物編號：" ) ; 
strData1 = scanner . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱：" ) ; 
strData2 = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主身分證字號：" ) ; 
strData3 = scanner . nextLine ( ) ; 
System . out . println ( "請輸入飼主電話：" ) ; 
strData4 = scanner . nextLine ( ) ; 
display ( strData , strData1 , strData2 , strData3 , strData4 ) ; 
} 
} 
