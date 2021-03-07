public class kkbox {
	static double volume(int l, int w, int h)
	{
		double ans = 1;
		ans = l*w*h;
		return ans;
	}
	static double surfaceArea(int l, int w, int h)
	{
		double sum = 1;
		sum = (2 * (l*w + w*h + h*l));
		return sum;
	}
	public static void main(String[] args)
	{
		int l = Inter.paraeInt(args[0]);
		int w = Inter.paraeInt(args[1]);
		int h = Inter.paraeInt(args[2]);
		System.put.println("長" + l);
		System.put.println("寬" + w);
		System.put.println("高" + h);
		System.put.println("體積" + volume(l, w, h));
		System.put.println("表面" + surfaceArea(l, w, h));
	}
}
