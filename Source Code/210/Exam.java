import java.util.Scanner;
class Exam{
    String name;
    String testday;
    int testtime ;
    double num;
    Exam(String name,String testday,int testtime,double num){
        this.name=name;
        this.testday=testday;
        this.testtime=testtime;
        this.num=num;
    }
    void setExam(String a,String b,int c,double d){
        name=a;
        testday=b;
        testtime=c;
        num=d;
    }
    double getMaxScore (){
        return num;
    }
     void listExam (){
  System.out.println("name="+ name);
 System.out.println("testday="+ testday);
 System.out.println("testtime="+testtime) ; 
 System.out.println("num="+num) ; 
}
 public static void main(String[] args) {
     Scanner input=new Scanner(System.in);
      System.out.println("enter name: " );
      String a=input.nextLine();
      System.out.println("enter testday: " );
      String b=input.nextLine();
      System.out.println("enter testtime: " );
      int c=input.nextInt();
      System.out.println("enter num: " );
      double d=input.nextDouble();
      Exam midexam=new Exam(a,b,c,d);
    System.out.println("midexam list----- " );
    midexam.listExam ();
    a=input.nextLine();
      System.out.println("enter name: ");
      a=input.nextLine();
      System.out.println("enter testday: " );
      b=input.nextLine();
      System.out.println("enter testtime: " );
      c=input.nextInt();
      System.out.println("enter num: " );
      d=input.nextDouble();
  
    Exam lastexam=new Exam(a,b,c,d);
    System.out.println("lastexam list----- " );
    lastexam.listExam ();
  
    }
}
        
        