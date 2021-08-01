import java.util.*;
public class Item
{
    static Item T;
    static Scanner sc=new Scanner(System.in);
    static void SetPet(String type)
    {
       
        System.out.print("請輸入訂單編號：A00001");
        String num=sc.next();
        System.out.print("請輸入日期：2018/11/06");
        String day=sc.next();
        System.out.print("請輸入貨幣單位：台幣");
        String mon=sc.next();
        System.out.println("----------------------");
		System.out.print("請輸入客戶公司行號：銘傳電商部");
        String num=sc.next();
        System.out.print("請輸入客戶地址：桃園ＸＸ路ㄧ號");
        String id=sc.next();
        System.out.print("請輸入連絡人：李大大");
        String nam=sc.next();
        System.out.print("請輸入客戶電話：03-3507772");
		System.out.println("----------------------");
        String tel=sc.next();
        T=new Item(num,day,mon);
        T.Customer=new Customer(id,tel);
    }
    static void main()
    {
        Item.count=0;
        while(true)
        {
            System.out.print("請輸入商品種類:");
            String type=sc.next();
            if(type.equals("0"))
            {
                System.out.println("總共"+Item.count+"個商品");
                break;
            }
            SetItem(type);
            T.display();
        }
    }
}