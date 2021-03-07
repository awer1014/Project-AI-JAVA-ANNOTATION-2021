class KKBox{
    static double length,width,height;
    static double volume,surfaceArea;
a1(double y){
    volume=y;
         y=(length * width * height);
         System.out.println("體積:"+y);
    }
a2(double x){
    surfaceArea=x;
    x=(2 *(length*width + width* height + height * length));
    System.out.println("表面積:"+x);
    }
    public static void main(String[] args){
    System.out.println("請輸入長: ");
    length=Integer.parseInt(args[0]); 
    System.out.println("請輸入寬: ");
    width=Integer.parseInt(args[1]);
    System.out.println("請輸入高: ");
    height=Integer.parseInt(args[2]);
    
    a1;
    a2;
}
}