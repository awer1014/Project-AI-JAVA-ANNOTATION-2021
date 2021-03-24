import java.util.Scanner;
public class KKBox{
    public double volume(double length,double width,double height){
        return length * width * height;
    }
    public double surfaceArea(double length,double width,double height){
        return 2 *(length*width + width* height + height * length);
    }
    public static void main(String[] args){
        double length,width,height;
        Scanner scanner=new Scanner(System.in);
        length=scanner.nextInt();
        width=scanner.nextInt();
        height=scanner.nextInt();
        double p1=volume(length,width,height);
        double p2=surfaceArea(length,width,height);
        System.out.println("體積 : "+p1);
        System.out.println("表面積 : "+p2);
    }
}