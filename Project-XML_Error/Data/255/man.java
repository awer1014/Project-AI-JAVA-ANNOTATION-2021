public class man
{
  private String cid;
  private String live;
  private String plpe;
  private String ple;
  man(String cid,String live,String plpe,String ple)
  {
      this.cid=cid;
      this.live=live;
      this.plpe=plpe;
      this.ple=ple;
  }
  void setcid(String cid){this.cid=cid;}
  void setlive(String live){this.live=live;}
  void setcplpe(String plpe){this.plpe=plpe;}
  void setple(String ple){this.ple=ple;}
  void display()
  {
       System.out.println("客戶公司行號："+cid);
       System.out.println("客戶地址："+live);
       System.out.println("客戶連絡人："+plpe);
       System.out.println("客戶電話："+ple);
  }
}
