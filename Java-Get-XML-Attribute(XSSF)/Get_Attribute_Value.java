import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import org.w3c.dom.*;
import javax.xml.xpath.*;
import org.xml.sax.SAXException;
import java.util.*;

public class Get_Attribute_Value {
     int Max_Error_num=0; //check max Error number
    private  Document doc;
     ArrayList<Line_Block> line_List= new ArrayList<>();
    //static ArrayList<Source_Code_Sorter> scs_list = new ArrayList<>();
     Source_Code_Sorter scs;// = new Source_Code_Sorter();
    //the two following ArrayList is for total line
     ArrayList<Integer> Error_Begin_Lines = new ArrayList<>();
     ArrayList<Integer> Error_End_Lines = new ArrayList<>();
    //static ArrayList<Source_Code_Sorter> list_scs = new ArrayList<>();
    public void load_Xml_file(String file_name) {
        try {
            //read Xml file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            //read target file
            doc = builder.parse(new File (file_name+".xml") );
            System.out.println("Load OK!");
        } catch(Exception e) {
            System.out.println("load_Xml_file went wrong here");
            e.printStackTrace();
        }
    }

    public void add_begin_block(int begin, int end){
        Error_Begin_Lines.add(begin);
        Error_End_Lines.add(end);
    }

    public void add_total_lines(Document doc, Source_Code_Sorter scs) {
        //to get error Attribute
        for(int k = 0; k < line_List.size(); k++) {
            for(int i = 0; i < line_List.get(k).get_error_type_length(); i++) {
                //get the original file state
                String file_name = line_List.get(k).get_file_name(i)+".java";
                int org_begin = line_List.get(k).get_begin(i);
                int org_end = line_List.get(k).get_end(i);
                /*System.out.println("---------------");
                System.out.println(file_name);
                System.out.println(org_begin);
                System.out.println(org_end);*/
                //========================Test========================
                //dust switch
                /*
                System.out.println(line_List.get(k).get_Error_type(i) +" "+ line_List.get(k).key);
                System.out.println(line_List.get(k).get_file_name(i));
                System.out.println(line_List.get(k).get_begin(i));
                System.out.println(line_List.get(k).get_end(i));
                //*/
                //========================Test========================
                //get index from scs's Source_Code Array
                int total_line = scs.find_IndexOf_Source_Code(file_name);
                //System.out.println("Index: " + total_line);
                //Error_Begin_Lines & Error_End_Lines <--------- original line + begin_line
                int new_Error_Begin_Line = org_begin + scs.get_SourceCode_Begin_line(total_line);
                line_List.get(k).set_begin(i,new_Error_Begin_Line);
                //System.out.println("Begin line: " + new_Error_Begin_Line);
                int new_Error_End_Line = org_end + scs.get_SourceCode_Begin_line(total_line);
                line_List.get(k).set_end(i,new_Error_End_Line);
                //System.out.println("End line: " + new_Error_End_Line);
                //========================Test========================
                //dust switch
                /*
                System.out.print("begin line: " + new_Error_Begin_Line);
                System.out.println("end line: " + new_Error_End_Line);
                //*/
                //========================Test========================
                //get index from scs's Source_Code Array
                add_begin_block(new_Error_Begin_Line,new_Error_End_Line);
            }
        }

        //int begin_temp,end_temp;
        //for(int k = 0; k < line_List.size(); k++) {
        //    for(int i = 0; i < line_List.get(k).get_file_name_length(); i++) {
        //        for(int j=0; j<scs.get_Array_length();j++){
        //            if(line_List.get(k).get_file_name(i).equals(scs.get_SourceCode(j))){
        //                begin_temp=line_List.get(k).get_begin(i);   begin_temp+=scs.get_SourceCode_Begin_line(j);   line_List.get(k).set_begin(i,begin_temp);
        //                end_temp=line_List.get(k).get_end(i);       end_temp+=scs.get_SourceCode_Begin_line(j);line_List.get(k).set_end(i,end_temp);
        //
        //                /*System.out.print(line_List.get(k).get_begin(i)+scs.get_SourceCode_Begin_line(j));
        //                System.out.print(line_List.get(k).get_end(i)+scs.get_SourceCode_Begin_line(j));*/
        //            }
        //        }
        //    }
        //}

    }

    public void get_Error_Value(/*String file_name,*/ Document doc) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        //ArrayList-->Mapper
        line_List = new ArrayList<Line_Block>();
        Mapper map = new Mapper();
        
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        //to get Error_List Attribute
        NodeList Error_List = (NodeList) xpath.evaluate("/ErrorList/Errors/Error", doc, XPathConstants.NODESET);
        for (int i = 0; i < Error_List.getLength(); i++) {
            Element Error = (Element)Error_List.item(i);
            NodeList Line_List = (NodeList) xpath.evaluate("Linelist/line", Error, XPathConstants.NODESET);
            String type = Error.getAttribute("tpye");
            //=====================Test======================
            //System.out.println(type);
            //=====================Test======================
            int key = map.get_Index(type);
            Line_Block lb = new Line_Block(key);
            /*int Error_num= 0;*/
            for(int k = 0; k < Line_List.getLength(); k++) {
                //String type = Error.getAttribute("tpye");
                Element Line = (Element)Line_List.item(k);
                //print Error Source code
                String src = Line.getAttribute("src");
                //print Error Begin-
                int Begin = Integer.parseInt(Line.getAttribute("Begin"));
                //print Error End
                int End = Integer.parseInt(Line.getAttribute("End"));
                //wtt.write_Error_End(End);
                lb.add_Block(src,type,Begin,End);
                //lb.add_Block(src,Begin, End);
                /*
                Error_num += 1;
                if(Error_num > Max_Error_num){
                    Max_Error_num = Error_num;
                }*/
            }
            
            line_List.add(lb);
        }
        /*System.out.print("Max_Error_num:"+Max_Error_num);*/
        //line_List.add(lb);
        Collections.sort(line_List); //need to sort for safe
        get_Source_Value(doc);
        //===============================================================================================
        //test Attribute output

        //System.out.println("===========NEW TEST===========");
        /*for(int k = 0; k < line_List.size(); k++) {
        for(int i = 0; i < line_List.get(k).get_error_type_length(); i++) {
        System.out.print(line_List.get(k).get_Error_type(i) +" "+ line_List.get(k).key);
        System.out.print(line_List.get(k).get_file_name(i));
        System.out.print(line_List.get(k).get_begin(i));
        System.out.println(line_List.get(k).get_end(i));
        }
        }*/
        //================================================================================================

    }

    public void get_Source_Value(/*String file_name,*/ Document doc) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        NodeList Source_Code_List = (NodeList) xpath.evaluate("/ErrorList/SourceCode_List/SourceCode", doc, XPathConstants.NODESET);
        //Source_Code_Sorter scs = new Source_Code_Sorter();
        scs = new Source_Code_Sorter();
        boolean array_last = false;
        for(int i = 0; i < Source_Code_List.getLength(); i++) {
            //get Source Code
            if (i == (Source_Code_List.getLength()-1)) {
                array_last = true;
            }
            Element Source = (Element)Source_Code_List.item(i);
            String Code_name = Source.getAttribute("name");
            //System.out.println("Code_name: " + Code_name);
            int code_line = Integer.parseInt(Source.getAttribute("lines"));
            //System.out.println("code_line: " + code_line);
            scs.add_SourceCode_and_line(Code_name, code_line, array_last);
        }
        //list_scs.add(scs);

        //================================================================================================
        //TEST
        //*
        /*for(int i = 0; i < Source_Code_List.getLength(); i++) {
            System.out.println("Source Code :" + scs.get_SourceCode(i));
            System.out.println("Source Code Begin Line :" + scs.get_SourceCode_Begin_line(i));
        }*/
        //*/
        //================================================================================================

        //to add_total_lines
        add_total_lines(doc, scs);
    }

    public String get_list_Error_type(int list_Index, int Index) {
        return line_List.get(list_Index).get_Error_type(Index);
    }

    public String get_list_file_name(int list_Index, int Index) {
        return line_List.get(list_Index).get_file_name(Index);
    }

    public int get_list_Error_begin(int list_Index, int Index) {
        return line_List.get(list_Index).get_begin(Index);
    }

    public int get_list_Error_end(int list_Index, int Index) {
        return line_List.get(list_Index).get_end(Index);
    }

    public int get_list_size() {
        return line_List.size();
    }

    public int get_list_element_size(int list_Index) {
        return line_List.get(list_Index).get_error_type_length();
    }

    public int get_Max_Error_num() {
        return Max_Error_num;
    }

    public List<Line_Block> get_List_Line_Block_() {
        return line_List;
    }

    public int get_Error_Begin_Lines(int Index) {
        return Error_Begin_Lines.get(Index);
    }

    public int get_Error_End_Lines(int Index) {
        return Error_End_Lines.get(Index);
    }

}
