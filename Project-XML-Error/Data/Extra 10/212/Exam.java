public class Exam{
    String name;
    String date;
    int time;
    double score; 
    Exam(String n , String d , int t , double s){
        name = n;
        date = d;
        time = t;
        score = s;        
    }
    /*void setExam(String name,String date,Int time,Double score){
        name=n;
        date=d;
        time=t;
        score=s;
    }*/
    double getMaxScore(){
        return score;
    }
    void listExam(){
        System.out.println(" exam name "+name+" exam date "+date+" exam time "+time+" exam score "+score);
    }
    public static void main(String args[]){
       Exam Midexam=new Exam(args[0],args[1],Integer.parseInt(args[2]),Double.parseDouble(args[3]));
       Exam lastexam=new Exam(args[4],args[5],Integer.parseInt(args[6]),Double.parseDouble(args[4]));
       Midexam.listExam();
       lastexam.listExam();
    }
}