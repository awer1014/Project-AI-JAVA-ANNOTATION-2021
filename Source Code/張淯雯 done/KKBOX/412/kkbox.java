
/**
 * Write a description of class kkbox here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class kkbox
{
    static int n;
    static int b; 
    static int length;
    static int width ;
    static int height;
    static double volume(int n)
    {
        n=length*width*height;
        return n;
    }
    static double surfaceArea(int b)
    {
        b= (2*(length*width+width*height+height*length));
        return b;
    }
    public static void main(String[] x)
    {
        System.out.println("請輸入長:"+length);
        System.out.println("請輸入寬:"+width);
        System.out.println("請輸入高:"+height);
        System.out.println("體積:"+n);
        System.out.println("表面積:"+b);
    }
    }

