/*
1.問題定義：顯示出姓名年齡國籍
2.類別資料：static String name
			static int age
			static String country
3.類別動作：static void selfIntroduce(){
			System.out.println("Hello!I am "+name+" aged "+age+".I come from "+country+".");}
4.測試結果：成功
5.評分結果：3
*/

public class test{
	static String name;
	static int age;
	static String country;
	static void selfIntroduce(){
	System.out.println("Hello!I am "+name+" aged "+age+".I come from "+country+".");
	}
	public static void main(String[] args){
		name = args[0];
		age = Integer.parseInt(args[1]);
		country = args[2];
		selfIntroduce();
	}

}