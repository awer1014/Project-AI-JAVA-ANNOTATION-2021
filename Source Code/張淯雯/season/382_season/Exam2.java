public class Exam2 {
 exit(StringToInt number){
        switch((number - 1) / 13) { 
            case 0 : return "春";
            case 1 : return "夏";
            case 2 : return "秋";
            default: return "冬";
        }
    }
    
 public static void main(String args[]) { 
             Exam ex=new Exam();
             ex.SetExam(args[0]);
             System.out.printf(Exit);
        }
    }

