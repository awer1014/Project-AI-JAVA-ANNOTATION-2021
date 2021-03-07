class KKBox
{   double length;
    double width;
    double height;
    double volume;
    double surf;
    KKBox(double length,double width,double height){
        this.length=length;
        this.width=width;
        this.height=height;
    }
    double vol(double length,double width,double height){
        double volume=length*width*height;
        return volume;
    }
    double volume(){
        return volume;
    }
    double sur(double length,double width,double height){
        double surf=2*((length*width)+(width*height)+(height*length));
        return surf;
    }
    double surfaceArea(){
        return surf;
    }
    public static void main(String[] args){
        double length=Double.parseDouble(args[0]);
        double width=Double.parseDouble(args[1]);
        double height=Double.parseDouble(args[2]);
        
        KKBox ex=new KKBox(length,width,height);
        double vol=ex.volume();
        double sur=ex.surfaceArea();
        System.out.println("體積:"+vol+"表面積"+sur);
    }
}
