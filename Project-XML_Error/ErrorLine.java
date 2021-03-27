public class ErrorLine
{
    String filename;
    String begin;
    String end; 
    ErrorLine(String filename,String begin,String end){
        this.filename = filename;
        this.begin = begin;
        this.end = end;
    }

    public String getFilename()
    {
        return filename;
    }

    public String getBegin()
    {
        return begin;
    }

    public String getEnd()
    {
        return end;
}
}
