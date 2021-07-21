
public class Test
{  
    static int n;
    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);    
        System.out.println("請輸入模擬次數:");
        n = scan.nextInt();
        Target.toss(n);
        System.out.println("圓內次數"+Target.in +"出現比率:"+Target.rate1);
        System.out.println("圓外次數"+Target.out+"出現比率:"+(1-Target.rate1));
    }
}
