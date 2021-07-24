public class Cel{
	static double degree;
	static double ans(double degree){
		return degree*9.0/5.0+32.0;
	}
	
    public static void main(String[] args){
		
        double degree = Double.parseDouble(args[0]);
        System.out.println( degree + " 度C = " + Cel.ans(degree));
    }
}


/*
問題定義：攝氏轉華氏
類別資料：degree 
類別動作：ans
測試結果：成功
評分結果：4
*/