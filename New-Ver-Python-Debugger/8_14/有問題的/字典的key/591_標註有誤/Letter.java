public class Letter implements Comparable { 
static String month [ ] = { "01" , "02" , "03" , "04" , "05" , "06" , "07" , "08" , "09" , "10" , "11" , "12" } ; 
static String day [ ] = { "01" , "02" , "03" , "04" , "05" , "06" , "07" , "08" , "09" , "10" , "11" , "12" , "13" , "14" , "15" , "16" , "17" , "18" , "19" , "20" , "21" , "22" , "23" , "24" , "25" , "26" , "27" , "28" , "29" , "30" , "31" } ; 
private String month ; 
private String day ; 
public static String monthAsString ( short m ) { 
return month [ m ] ; 
} 
Letter ( String month , String day ) { 
this . month = month ; 
this . day = day ; 
} 
} 
