
public class Order
{
    private String number;
    private String date;
    private String dollar;

    Order(String number,String date,String dollar)
    {
        number = number;
        date = date;
        dollar = dollar;
    }

    void setnumber(String number)
    {
       this.number=number;
    }
    void setdate(String date){
        this.date = date;
    }
    void setdollar(String dollar){
        this.dollar=dollar;
    }
    
    void display()
    {
        
        System.out.println("請輸入訂單編號：" + number );
        System.out.println("請輸入日期：" + date);
        System.out.println("請輸入貨幣單位：" + dollar );
        
    } 
}
