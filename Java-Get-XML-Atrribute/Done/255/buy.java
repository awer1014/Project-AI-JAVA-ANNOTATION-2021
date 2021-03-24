import java.util.Scanner;
public class buy
{
    private String pid;
    private String name;
    private double prs;
    private int n;
    private double off;
    private CCC cc=null;
  buy(String pid,String name,double prs,int n,double off)
  {
      this.name=name;
      this.pid=pid;
      this.prs=prs;
      this.n=n;
      this.off=off;
  }
  void setCCC(CCC cc)
  { 
      this.cc=cc;
  }
  void setoff(double off)
  { 
      this.off=off;
  }
  void setn(int n)
  {
      this.n=n;
  }
  void setprs(double prs)
  {
      this.prs=prs;
  }
  void setpid(String pid)
  {
      this.prs=prs;
  }
  void setname(String name)
  {
      this.name=name;
  }
  void display()
  {
      System.out.println("訂購貨品編號："+pid);
      System.out.println("訂購貨品名稱："+name);
      System.out.println("訂購貨品單價："+prs);
      System.out.println("訂購貨品數量："+n);
      System.out.println("訂購貨品折扣："+off);
  }
}
