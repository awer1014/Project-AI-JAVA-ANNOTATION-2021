
public class CodeData
{
   private String code;
   private String filename;
   
   public CodeData(String fn, String code) {
       filename = fn;
       this.code =code;
    }
    public String getFileName() {
     return filename;   
    }
    public String getCode() {
        return code;
    }
}
