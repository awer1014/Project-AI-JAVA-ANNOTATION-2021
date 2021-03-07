
/**
 * 在这里给出对类 Keeper 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Keeper
{
    String id,tel;
    void display(){
        System.out.println("飼主身份證字號："+id);
        System.out.println("飼主電話："+tel);
    }
    public Keeper(String i,String tl){
        id=i;
        tel=tl;
    }
}
