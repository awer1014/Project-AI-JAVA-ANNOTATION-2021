
public class toss
{
    int number;
    int CircleIn;
    double Inans;
    int CircleOut;
    double Outans;

    public toss(int number,int CircleIn,double Inans,int CircleOut,double Outans)
    {
        this.number=number;
        this.CircleIn=CircleIn;
        this.Inans=Inans;
        this.CircleOut=CircleOut;
        this.Outans=Outans;
    }
    
    
    
    public static void main(String args[])
    {
        double Inans = Double.parseDouble(args[0]);
        double Outans = Double.parseDouble(args[1]);
        toss t = new toss(number,CircleIn,Inana,CircleOut,Outans);
        System.out.println("請輸入模擬次數:"+number)
        System.out.println("圓內次數:"+ CircleIn)
        System.out.println("出現比率:"+ Inans)
        System.out.println("圓外次數:"+CircleOut)
        System.out.println("出現比率:"+Outans)
    }
}
