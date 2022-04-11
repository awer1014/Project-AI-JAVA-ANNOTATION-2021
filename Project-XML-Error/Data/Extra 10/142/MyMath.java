public class MyMath {
		static int a(int N){
			int sum=1;
			for(int i=1;i<=N;i++){
				sum=sum*i;
			}
			sum*=2;
			return sum;
		}
		public static void main(String[] args){
			int N=Integer.parseInt(args[0]);
			System.out.println("N!:"+a(N) );
		}
}
