public class KKBox{
    static int length;
    static int width;
    static int height;
    static void num(){        
        System.out.println("請輸入長"+length);
        System.out.println("請輸入寬"+width);
        System.out.println("請輸入高"+height);
        int volume=length*width*height;
        int surfaceArea=(2*(length*width+width*height+height*length));
        System.out.println("體積"+volume);
        System.out.println("表面積"+surfaceArea);
    }
    public static void main(String[] ar){
        num();
    }
}