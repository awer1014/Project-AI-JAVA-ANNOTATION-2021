
public class ErrorData
{
   private String type1, type2,  filename,  begin,  end,  message;
   public ErrorData(String type1, String type2, String filename, String begin, String end, String message) {
       this.type1 = type1;
       this.type2 = type2;
       this.filename = filename;
       this.begin = begin;
       this.end = end;
       this.message = message;      
    }
   
   public String getType1() {return type1; }
   public String getType2() {return type2; }
   public String getFileName() {return filename; }
   public String getBegin() {return begin; }
   public String getEnd() {return end; }
   public String getMessage() {return message; }
    
}
