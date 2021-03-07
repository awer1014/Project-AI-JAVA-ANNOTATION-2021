public class Pet{
    static String pid;
    static String name;
    static String type;
    public Pet(String p,String n,String t){
        this.pid=p;
        this.name=n;
        this.type=t;
    }
    void display(){
        System.out.println("寵物種類"+this.type);
        System.out.println("寵物編號"+this.pid);
        System.out.println("寵物名稱"+this.name);
    }
    void speak(){
        if(type=="狗"){System.out.println("你的寵物叫聲是:汪汪");}
        else if(type=="貓"){System.out.println("你的寵物叫聲是:喵喵");}
    }
}