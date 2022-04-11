import java . util . * ; 
public class season { 
static String [ ] sea = new String [ ] { "春" , "夏" , "秋" , "冬" } ; 
static List < String > strList = Arrays . asList ( sea ) ; 
public static void main ( String [ ] args ) { 
Collections . shuffle ( strList ) ; 
System . out . println ( strList ) ; 
} 
} 
