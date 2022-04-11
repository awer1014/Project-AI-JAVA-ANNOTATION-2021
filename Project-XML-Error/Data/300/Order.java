public class Order{
    private String bh;
    private String day;
    private String hb;
    private Customer cu;
    private Item[] i = new Item[10];
    public Order(String s1,String s2,String s3){
        String bh = s1;
        String day = s2;
        String hb = s3;
    }
    public void setCustomer(Customer c){
        cu = c;
    }
    public void setItem(int a,Item b){
        i[a] = b ;
    }
    public void display(int i){
    System.out.println("訂單編號 ： "+ bh);
    System.out.println("日期 ： "+day);
    System.out.println("貨幣單位 ： "+hb);
    }
}
