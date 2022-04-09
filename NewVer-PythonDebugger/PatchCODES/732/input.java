import java . util . Scanner ; 
class input { 
static int count = 0 ; 
static Scanner keyboard = new Scanner ( System . in ) ; 
public void print ( ) { 
for ( int i = 0 ; i < count ; i ++ ) { 
System . out . println ( "---------------------------------------" ) ; 
System . out . println ( "你的輸入是 ：" ) ; 
System . out . println ( "寵物種類 ：" ) ; 
list . gettyper ( ) ; 
System . out . println ( "寵物編號：" ) ; 
list . getpid ( ) ; 
System . out . println ( "寵物名稱 ：" ) ; 
list . getname ( ) ; 
System . out . println ( "飼主身份證字號 ：" ) ; 
list . getid ( ) ; 
System . out . println ( "飼主電話：" ) ; 
list . gettel ( ) ; 
System . out . println ( "你的寵物的叫聲：" ) ; 
list . speak ( ) ; 
System . out . println ( "總共有" + count + "隻寵物" ) ; 
} 
} 
public static void main ( String [ ] args ) { 
keeper list1 [ ] = new keepr [ 100 ] ; 
pet list2 [ ] = new keeper [ 100 ] ; 
String kind ; 
for ( int i = 0 ; i < 100 ; i ++ ) { 
System . out . println ( "請輸入寵物種類" ) ; 
String type = keyboard . nextLine ( ) ; 
while ( kind == "0" ) { 
print ( ) ; 
return ; 
} 
print ( ) ; 
System . out . println ( "請輸入寵物編號" ) ; 
String pid = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入寵物名稱" ) ; 
String name = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入飼主身分證字號" ) ; 
String id = keyboard . nextLine ( ) ; 
System . out . println ( "請輸入飼主電話" ) ; 
String tel = keyboard . nextLine ( ) ; 
keeper list1 [ ] = new keeper ( id , tel ) ; 
pet list2 [ ] = new pet ( pid , name , type ) ; 
count ++ ; 
} 
} 
} 
