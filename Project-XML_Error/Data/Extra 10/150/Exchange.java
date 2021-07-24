public class Exchange
{
    double money;
    double rate;
    double ans;
    
    Exchange(double m,double r)
    {
        money = m;
        rate = r;
    }
    int changeOneHundred()
    {
        ans = money * rate;
        return (int)ans/100;
    }
    

   public static void main(String[] args) {

       double m = Double.parseDouble(args[0]); //台幣金額

       double rate = Double.parseDouble(args[1]); //美金匯率

       Exchange ex = new Exchange(m, rate);

       int ans = ex.changeOneHundred();

       System.out.println("The answer is: " + ans);

   }


}
