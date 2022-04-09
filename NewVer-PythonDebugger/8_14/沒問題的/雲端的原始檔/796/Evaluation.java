abstract public class Evaluation { 
private static int count ; 
protected String pid , name , type ; 
protected Keeper keeper ; 
public Evaluation ( String p , String n , String t , Keeper k ) { 
pid = p ; 
name = n ; 
type = t ; 
count ++ ; 
keeper = k ; 
} 
public static int getcount ( ) { 
return count ; 
} 
} 
