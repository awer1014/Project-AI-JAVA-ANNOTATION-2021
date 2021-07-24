
public class player
{
    String name;
    int ticket;
    double total=0;
    player(String name,int ticket,double total)
    {
        this.name=name;
        this.ticket=ticket;
        this.total=total;
    }
    
    void name(){
        this.name=name;
    }
    
    double total(int ticket){
        for(int n=1;n>=ticket;n++)
        {
            total +=ticket;
        return total;
        }
    }
    
    double ticket(int ticket,double total)
    {
        double ans;
        ans = ticket/total;
        return ans;
    }
    
    public static void main(String args[])
    {
        player p = new player(name,ticket);
        double ans = p.gettotal();
        System.out.println("姓名:" + name);
        System.out.println("得票數:" + ticket);
        System.out.println("得票比率:" + ans + "%");
    }
}
