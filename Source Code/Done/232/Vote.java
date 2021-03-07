public class Vote
{
    static int sum;
    static double rate;
    private String name;
    private int ticket;
    
    Vote(String n, int t){
    this.name = n;
    this.ticket = t;
    }
    
    int sumofticket(int ticket){
     sum = sum +this.ticket;
     return sum;
    }
    
    double rate(int ticket){
        rate = this.ticket/sum;
        return rate;
    }
    void display(){
    System.out.println("姓名: "+ name +"得票數: " + ticket + "得票率: "+ rate );

    }

    
    
}
