class Exchange
{   
    double money , rate;
    Exchange(double m , double r){
        money = m;
        rate = r;
    }
    int changeOneHundred(){
        double ans=money*rate;
        return (int)ans/100;
    }
    public static void main(String[] args){
        double m = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        Exchange ex = new Exchange(m,rate);
        int ans = ex.changeOneHundred();
        System.out.println("The answer is:"+ans);
    }
}