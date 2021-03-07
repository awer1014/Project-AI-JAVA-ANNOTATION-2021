public class Pet{
    static int count=0;
    public String pid,name,type;
    public void speak(){
        if(type.equals("狗"))System.out.println("您寵物的叫聲是 : 汪汪");
        if(type.equals("貓"))System.out.println("您寵物的叫聲是 : 喵喵");
    }
    
    public void display(){
        System.out.println("寵物編號 :"+pid);
        System.out.println("寵物名稱 :"+name);
        System.out.println("寵物種類 :"+type);
    }
    public Pet(String tp,String pd,String nm){
        pid =pd;
        name = nm;
        type = tp;
    }

}
