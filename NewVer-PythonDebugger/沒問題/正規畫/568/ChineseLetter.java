public class ChineseLetter { 
public static String title ; 
public static String receiver ; 
public static String content ; 
public static String sender ; 
public static String date ; 
ChineseLetter ( String title , String receiver , String content , String sender , String date ) { 
this . title = title ; 
this . receiver = receiver ; 
this . content = content ; 
this . sender = sender ; 
this . date = date ; 
} 
static String getSender ( ) { 
return sender ; 
} 
static String getReceiver ( ) { 
return receiver ; 
} 
static String getDate ( ) { 
return date ; 
} 
} 
