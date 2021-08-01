
public class Item
{
    private String num;
    String name;
     double money;
     int nums;
    double discount;
    
    Item(String num,String name,double money,int nums,double discount,double ans)
    {
        this.num=num;
        this.name=name;
        this.money=money;
        this.nums=nums;
        this.discount=discount;
      
    }
    
    double getans(double money,int num,double discount)
    {
        double ans;
        ans= (money*num)*discount;
        return ans;
    }
    
    void display()
    {
        System.out.println("1.貨品編號：" + num);
        System.out.println("2.貨品名稱:" + name);
        System.out.println("3.單價:" + money);
        System.out.println("4.數量:" + nums);
        System.out.println("5.折扣:" + discount); 
        
    }
}
