public class countnum
{
    String str;
    String n;
    int count=0;
        
    public int countnum(String str,String n)
    {
       
       for (int i=0;i<str.length();i++) 
       {
           count++;
           int c=str.charAt(i);
          
          
           
        
        return count;
    }
    

    public static void main(String[] args) 
    {
        String str=args[0];
        
       
        
        System.out.println("length:"+ countnum());
    }
}
