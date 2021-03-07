
public class Customer
{
    private String cp;
    private String adress;
    private String br;
    private String tel;
    private String s;
    
    Customer(String cp,String adress,String br,String tel,String s)
    {
        this.cp=cp;
        this.adress=adress;
        this.br=br;
        this.tel=tel;
        this.s=s;
    }

    void display()
    {
        System.out.println("1.公司行號：" + cp);
        System.out.println("2.地址:" + adress);
        System.out.println("3.聯絡人:" + br);
        System.out.println("4.電話:" + tel);
        System.out.println("5.傳真:" + s); 
    }
    
}
