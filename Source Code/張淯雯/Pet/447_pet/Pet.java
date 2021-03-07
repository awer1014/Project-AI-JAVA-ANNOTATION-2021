public class Pet
{
    static int count;
    private String pid;
    private String name;
    private String type;
    public Pet(String pid,String name,String type)
    {
        this.pid=pid;
        this.name=name;
        this.type=type;
    }
    public void speak()
    {
        if(type.equals("狗"))
            System.out.println("汪汪");
        if(type.equals("貓"))
            System.out.println("喵喵");
    }
    public void display()
    {
        System.out.println("編號:"+pid);
        System.out.println("名稱:"+name);
        System.out.println("種類:"+type);
    }
}
