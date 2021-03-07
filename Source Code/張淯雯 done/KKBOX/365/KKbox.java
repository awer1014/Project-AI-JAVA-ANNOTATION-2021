class KKbox
{
    double length,width,height;
    
    KKbox()
    {
        double length,width,height;
    }
    double volume()
    {
        return length*width*height;
    }
    double surfaceArea()
    {
        return (2 *(length*width + width* height + height * length));
    }
    public static void main(double[] args)
    {
        KKbox ans1 = new KKbox();
        //KKbox ans2 = new KKbox();
        ans1.length=args[0];
        ans1.width=args[1];
        ans1.height=args[2];
        System.out.println("請輸入長:"+ans1.length);
        System.out.println("請輸入寬:"+ans1.width);
        System.out.println("請輸入高:"+ans1.height);
        System.out.println("體積:"+ans1.volume());
        System.out.println("表面積:"+ans1.surfaceArea());
    }
}
