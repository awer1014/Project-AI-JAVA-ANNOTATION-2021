
public class Keeper
{
    private String id;
    private String tel;
    public Keeper(String id,String tel)
    {
        this.id=id;
        this.tel=tel;
    }
    public void display()
    {
        System.out.println("身分證字號:"+id);
        System.out.println("電話:"+tel);
    }
}
