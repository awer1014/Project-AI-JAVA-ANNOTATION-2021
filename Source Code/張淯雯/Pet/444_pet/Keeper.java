public class Keeper{
    static String id;
    static String tel;
    public Keeper(String i,String t){
        this.id=i;
        this.tel=t;
    }
    void display(){
        {System.out.println("飼主身分證字號"+this.id);}
        {System.out.println("飼主電話"+this.tel);}
    }
}