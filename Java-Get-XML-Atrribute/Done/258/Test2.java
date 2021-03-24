import java.util.StringTokenizer;
public class Test2{
    public static void main(String[]args){
        String str="abcd++ef";
        System.out.println(str.toLowerCase());
    }    
public class Test2StringTokenizer{
    public static void main(String[]args){
        StringTokenizer str=new StringTokenizer();
        String tk;
        while(str.hasMoreTokens()){
             tk=str.nextToken();
             System.out.println(tk);
            }
        }
    }
int Test2(String str,String word){
    int count=0;
       for(int i=0;i<str.length()-word.length()+1;i++){
           String tmp=str.substring(i,i+word.length());
           if(tmp.equals(word))
              count++;
           }
    return count;
}
public static void main(String[]args){
      String str=new str(args[0]);
      String str=new str(args[1]);
      System.out.println(str);    
    }
}
