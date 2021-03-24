public class Customer
{
    String id,tel;
    Customer(String id,String tel)
    {
        this.id=id;
        this.tel=tel;
    }
    void display()
    {
        System.out.println("買家地址:"+this.id);
        System.out.println("買家電話:"+this.tel);
    }
}