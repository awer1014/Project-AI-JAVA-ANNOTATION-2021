import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.Collator;
import javax.swing.filechooser.FileNameExtensionFilter;
public class WorkWindow extends JPanel {
    static String workingDirectory ="." ;
    static String currentFileName= "" ;
    private boolean updated = false;
    
    MainWindow mainjf;
    JFrame jf;//宣告新視窗的frame
    Vector<String> fileName = new Vector<String>();//儲存原始程式碼的檔案名稱
    Vector<String> codes = new Vector<String>();  //儲存原始程式碼的陣列
    Vector<Integer> lines = new  Vector<>();  //儲存原始程式碼行數的陣列
    JTextArea [] texts;//儲存TextArea的陣列
    JTextField XmlName;//儲存Xml的檔案名稱
    JPanel SourceCode;
    JPanel table_panel;
    Container contentPane;
    Vector<JPanel> code_panels  ;//放置行號按鈕及原始程式碼的panel
    JTextArea txt = new JTextArea();;  //文字區域
    public int file_idx = 0;//控制檔案的參數值
    int xPixels;//控制原始碼panel的x座標
    int yPixels;//控制原始碼panel的y座標

    JTabbedPane tabbedPane = new JTabbedPane();  //分頁標籤
    JTable table;
    Object[] columnNames = {"類型", "檔案名稱", "開始", "結束", "建議訊息"};// 使用表格模型创建一个表格
    Hashtable<String, String[]> subItems = new Hashtable<String, String[]>();
    static Object[][] rowData=null;
    DefaultTableModel tableModel;
    static java.util.List<Object[]> rowData2 = new ArrayList<Object[]>();
    ArrayList<ErrorLine> new_error = new  ArrayList<ErrorLine>();
    static ArrayList<MyError> table_error = new  ArrayList<MyError>();
    static AddXML addXML;
    
    public WorkWindow (MainWindow f){
        mainjf = f;
        init();
        addXML = new AddXML();
    }

    public static void copytotable() {
        rowData2.clear();
        Collections.sort(table_error);
        for (int i = 0; i < table_error.size(); i++) {
            rowData2.add(new Object[] { 
                    table_error.get(i).get(0), 
                    table_error.get(i).get(1),
                    table_error.get(i).get(2),
                    table_error.get(i).get(3),
                    table_error.get(i).get(4) 

                });
        }
        rowData = rowData2.toArray(new Object[][] {});
    }

    public void init() {
        contentPane = mainjf.getContentPane();  //宣告一個容器
        SourceCode = new JPanel();  
        table_panel = new JPanel(new BorderLayout());
        copytotable();
        table = new JTable(new AbstractTableModel() {// 把 表格内容 添加到容器中心
                @Override
                public int getRowCount() {
                    //System.out.println("row的長度為: "+rowData.length);
                    return rowData.length;
                }

                @Override
                public int getColumnCount() {
                    return columnNames.length;
                }

                @Override
                public String getColumnName(int column) {
                    return columnNames[column].toString();
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnIndex != -1;
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    //System.out.println("I get row: "+ rowIndex +" column: "+ columnIndex + " value: " + table_error.get(rowIndex).get(columnIndex));
                    return rowData[rowIndex][columnIndex];
                }

                @Override
                public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
                    // 设置新的单元格数据时，必须把新值设置到原数据数值中，
                    // 待更新UI重新调用 getValueAt(...) 获取单元格值时才能获取到最新值
                    //System.out.println("I get row: "+ rowIndex +" column: "+ columnIndex + " value: " + table_error.get(rowIndex).get(columnIndex));
                    rowData[rowIndex][columnIndex] = newValue.toString();
                    System.out.println("newValue: "+ newValue);
                    table_error.get(rowIndex).set(columnIndex,newValue.toString()); 
                    //System.out.println("I set row: "+ rowIndex +" column: "+ columnIndex + " new value: " + table_error.get(rowIndex).set(columnIndex,newValue.toString()));
                    /*MyError x = table_error.get(rowIndex);
                    x.set(columnIndex,newValue.toString());*/
                    fireTableCellUpdated(rowIndex, columnIndex);//设置完数据后，必须通知表格去更新UI（重绘单元格），否则显示的数据不会改变
                    table.revalidate();
                }
            });
        TableModel tableModel = table.getModel();// 获取 表格模型
        TableColumn tableColumn_0 = table.getColumnModel().getColumn(0);// 设置列的宽度、首选宽度、最小宽度、最大宽度
        tableColumn_0.setPreferredWidth(120);
        TableColumn tableColumn_1 = table.getColumnModel().getColumn(1);
        tableColumn_1.setPreferredWidth(80);
        TableColumn tableColumn_2 = table.getColumnModel().getColumn(2);
        tableColumn_2.setPreferredWidth(40);
        TableColumn tableColumn_3 = table.getColumnModel().getColumn(3);
        tableColumn_3.setPreferredWidth(40);
        TableColumn tableColumn_4 = table.getColumnModel().getColumn(4);
        tableColumn_4.setPreferredWidth(200);
        code_panels=  new Vector<>(); //new JPanel[5];
        /*
        tabbedPane.addTab("tab 0" , createTextPanel(0));// 创建第 1 个选项卡（选项卡只包含 标题）
        tabbedPane.addTab("tab 1" , createTextPanel(1));
        tabbedPane.addTab("tab 2" , createTextPanel(2));
        tabbedPane.addTab("tab 3" , createTextPanel(3));
        tabbedPane.addTab("tab 4" , createTextPanel(4));
         */
        SourceCode.add(tabbedPane);

        table_panel.add(table.getTableHeader(), BorderLayout.NORTH);// 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
        table_panel.add(table, BorderLayout.CENTER);

        XmlName = new JTextField("輸入xml檔案名稱");
        XmlName.setSize(1,20);

        contentPane.add(SourceCode,"West");
        contentPane.add(table_panel,"East");
        //contentPane.add(XmlName,"South");
    }

    private JComponent createTextPanel(int idx) {
        //創建tab分頁的內容物
        xPixels = 1200;
        yPixels = 600;
        JPanel code_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); //做出左邊放button右邊放text area
        code_panels.add(idx,code_panel);//用idx去控制panel陣列中的指定元素
        JScrollPane scrollPane = new JScrollPane(code_panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        code_panel.setPreferredSize(new Dimension(xPixels, yPixels));// 這是關鍵的2句
        scrollPane.setPreferredSize(new Dimension(800, yPixels));
        return scrollPane;
    }
    public  void showSave_XmlWindow(JFrame relativeWindow) {
        if (currentFileName.equals("")) {
            JFrame parentFrame = new JFrame();
            JFileChooser fc = new JFileChooser(workingDirectory);
            fc.setDialogTitle("輸入XML檔案名稱");          
            fc.setMultiSelectionEnabled(false); //不允许选择多个文件
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
            fc.setFileFilter(filter);
            int state = fc.showSaveDialog(this);
        
            if (state == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                workingDirectory = f.getParent();
                String fname = f.getAbsolutePath();
                if (! fname.toLowerCase().contains(".xml")) fname = fname + ".xml" ;
                updateCurrentFileName(fname);
            }
      }
      writeToXml(currentFileName);
    }
    public  void updateCurrentFileName(String fname) {
        currentFileName = fname;
        mainjf.setTitle(currentFileName);
    }
    public  void showNew_XmlWindow(JFrame relativeWindow) {
        JFrame parentFrame = new JFrame();
        JFileChooser fc = new JFileChooser(workingDirectory);
        fc.setDialogTitle("輸入XML檔案名稱");   
      
        
        fc.setMultiSelectionEnabled(false); //不允许选择多个文件
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
        fc.setFileFilter(filter);
        int state = fc.showSaveDialog(this);
        
         if (state == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            workingDirectory = f.getParent();
            String fname = f.getAbsolutePath() ;
            if (! fname.toLowerCase().contains(".xml")) fname = fname + ".xml" ;
            writeToXml(fname);
        }
        /*
        JFrame newJFrame = new JFrame("儲存名稱");// 创建一个新窗口
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JTextField XmlName = new JTextField();
        JLabel hint = new JLabel("請輸入xml檔案名稱");
        hint.setPreferredSize(new Dimension(250, 20));
        XmlName.setPreferredSize(new Dimension(250, 20));;
        JButton add = new JButton("確定"); //建立button
        add.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e) {
                    String name = XmlName.getText();
                    writeToXml(name);
                    newJFrame.dispose();
                } 
            });
        panel.add(hint);    
        panel.add(XmlName);
        panel.add(add);
        newJFrame.setSize(250, 250);
        newJFrame.setLocationRelativeTo(relativeWindow);// 把新窗口的位置设置到 relativeWindow 窗口的中心
        newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// 点击窗口关闭按钮, 执行销毁窗口操作（如果设置为 EXIT_ON_CLOSE, 则点击新窗口关闭按钮后, 整个进程将结束）
        newJFrame.setResizable(false);// 窗口设置为不可改变大小
        newJFrame.setContentPane(panel);
        newJFrame.setVisible(true);
*/
    }

    public void writeToXml(String name) {
        updateCurrentFileName(name);
        Collections.sort(table_error);
        addXML.addXML_List(name);
        for(int i=0; i<file_idx; i++){
            addXML.new_SrcNode(fileName.get(i),Integer.toString(lines.get(i)),codes.get(i));
        }
        for(int j=0; j<table_error.size(); j++){

            addXML.new_ErsNode(table_error.get(j).get(0), 
                table_error.get(j).get(1),
                table_error.get(j).get(2),
                table_error.get(j).get(3),
                table_error.get(j).get(4));
        }
        addXML.save(name);
    }

    public void removeErrorLine(){
        int idx = table.getSelectedRow();//jTable1.getSelectedRow()是选择当前行的下标
        if (idx!=-1) {
            String errType = (String)table.getValueAt(idx, 0);
            boolean found = false;
            int r = 0;
            while (r<table.getRowCount()) {
                if (((String)table.getValueAt(r, 0)).equals(errType)) {
                    table_error.remove(r);
                    copytotable();
                    mainjf.repaint();
                } else
                    r++;
            }

         }
    }

    public void readfile(ReadCodeStrategy st) {
        st.openFileList();
        CodeData code=null;
        //for(File f : files){
        while (( code = st.nextCode())!=null) {    
                int button_count=1;
                try {
                    tabbedPane.addTab(code.getFileName() , createTextPanel(file_idx));
                    txt.setText(code.getCode());//讀取檔案
                    fileName.add(code.getFileName());
                    lines.add(txt.getLineCount());
                    codes.add(txt.getText());
                    tabbedPane.setTitleAt(file_idx, fileName.get(file_idx));
                    JPanel line_count = code_panels.get(file_idx); //[file_idx];  //取得當前code_panel裡的line_display(panel)
                    line_count.setLayout(new FlowLayout());  //取得當前code_panel裡的line_display(panel)
                  
                    String[] lines = code.getCode().split("\\n");  
                    for (String line: lines)
                    {  
                        JButton count = new JButton(Integer.toString(button_count));
                        JTextArea label_text = new JTextArea();
                        //JLabel label_text = new JLabel();
                        JLabel blank = new JLabel();
                        int width;
                        label_text.setText(line);
                        label_text.setFont(new java.awt.Font("Dialog", 0, 15));
                        count.setPreferredSize(new Dimension(70, 20));
                        label_text.setPreferredSize(new Dimension(xPixels-100, 20));
                        button_count++;
                        line_count.add(count); 
                        line_count.add(label_text);
                    }
                    file_idx++;
                    line_count.setPreferredSize(new Dimension(xPixels, button_count*25));
                }   
                catch (Exception e) {
                   // System.out.println(e);
                    e.printStackTrace();
                }
            }
  
    }

    public  void showNew_ErrorWindow(JFrame relativeWindow, boolean editMode) {
        if (editMode && table.getSelectedRow()==-1) return;
        JFrame newJFrame = new JFrame("新的窗口");// 创建一个新窗口
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JTextArea error_suggest = new JTextArea(25,25);//创建一个 3 行 10 列的文本区域
        //error_suggest.setPreferredSize(new Dimension(250, 20));
        //error_suggest.setLineWrap(true);// 设置自动换行
        JScrollPane scroll = new JScrollPane(error_suggest, 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JTextField error_line = new JTextField();
        JTextField error_line_begin = new JTextField();
        JTextField error_line_end = new JTextField();
        error_line.setPreferredSize(new Dimension(250, 20));
        error_line_begin.setPreferredSize(new Dimension(20, 20));
        error_line_end.setPreferredSize(new Dimension(20, 20));

        JLabel hint = new JLabel("錯誤類型");
        hint.setPreferredSize(new Dimension(250, 20));
        JLabel hint_1 = new JLabel("檔案名稱");
        JLabel hint_1_1 = new JLabel("開始行號");
        JLabel hint_1_2 = new JLabel("結束行號");
        //hint_1.setPreferredSize(new Dimension(250, 20));
        JLabel hint_2 = new JLabel("有兩個錯誤以上請用\",\"隔開");
        hint_2.setPreferredSize(new Dimension(250, 20));
        JLabel hint_3 = new JLabel("請輸入建議訊息");
        hint_3.setPreferredSize(new Dimension(250, 20));

        JButton add_error_line = new JButton("確定"); //建立button
        JButton AddToTable = null;
        if (editMode) 
          AddToTable = new JButton("修改"); //建立button
        else
          AddToTable = new JButton("確定"); //建立button

        JComboBox<String> FileName = new JComboBox<String>(fileName);
        FileName.setPreferredSize(new Dimension(200, 25));
        JComboBox<String> combo_type;
        JComboBox<String> combo_SubType = new JComboBox<String>();
        combo_SubType.setPreferredSize(new Dimension(200, 25));
        JComboBox<String> combo_reason = new JComboBox<String>(fileName);
        String[] items = {"", "正確",  "變數", "運算", "函數", "字串", "io", "物件", "陣列", "其他", "繼承","抽象", "介面", "校對","迴圈"};
        combo_type = new JComboBox<String>( items );
        combo_type.setPreferredSize(new Dimension(60, 25));
        combo_type.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e) {
                    String item = (String)combo_type.getSelectedItem();
                    Object o = subItems.get( item );

                    if (o == null)
                    {
                        combo_SubType.setModel( new DefaultComboBoxModel() );
                    }
                    else
                    {
                        combo_SubType.setModel( new DefaultComboBoxModel( (String[])o ) );
                    }
                } 
            });

        combo_type.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);//  prevent action events from being fired when the up/down arrow keys are used
        combo_SubType.setPrototypeDisplayValue("XXXXXXXXXX"); // JDK1.4
        String[] subItems0 = { "" };//  Create sub combo box with multiple models
        subItems.put(items[0], subItems0);      
        String[] subItems1 = { "正確"};//  Create sub combo box with multiple models
        subItems.put(items[1], subItems1);
        String[] subItems2 = { "重複宣告變數", "變數未定義無法使用", "不了解局部變數的概念"};//  Create sub combo box with multiple models
        subItems.put(items[2], subItems2);
        String[] subItems3 = { "運算錯誤:型態不符無法運算"};
        subItems.put(items[3], subItems3);
        String[] subItems4= { "未傳遞參數或傳回值", "函數庫使用錯誤", "函數結構不清楚", "函數概念不清楚"};
        subItems.put(items[4], subItems4);
        String[] subItems5 = { "字串比較錯誤(沒有使用equals函數)"};
        subItems.put(items[5], subItems5);
        String[] subItems6 = { "不會使用輸入scanner類別", "重複宣告scanner物件"};
        subItems.put(items[6], subItems6);
        String[] subItems7 = { "物件動作概念(建構子)", "物件資料/物件動作概念", "正確的產生物件", "無法分辨物件&類別的資料/動作", "建立物件之間的關係", "不會使用物件之間的關係", "不會使用物件資料"};
        subItems.put(items[7], subItems7);
        String[] subItems8 = { "不會宣告陣列變數","陣列索引值","陣列溢位"};
        subItems.put(items[8], subItems8);
        String[] subItems9 = { "撰寫main函式", "關鍵字錯誤", "筆誤", "不熟悉JAVA程式結構"};
        subItems.put(items[9], subItems9);
        String[] subItems10 = { "使用繼承", "使用super產生繼承物件" };
        subItems.put(items[10], subItems10);
        String[] subItems11= { "繼承資料或動作", "抽象動作", "抽象類別不能直接產生物件", "覆寫抽象動作", "不會分離變跟不變的部分"};
        subItems.put(items[11], subItems11);
        String[] subItems12 = { "Comparable介面", "Comparable介面的排序應用"};
        subItems.put(items[12], subItems12);
       
        String[] subItems13 = {"校對"};
        subItems.put(items[13], subItems13);
        String[] subItems14 = {"迴圈概念錯誤"};
        subItems.put(items[14], subItems14);
        
        
        String regex = "([a-zA-Z0-9]+):(-?[1-9]\\d*|0)~(-?[1-9]\\d*|0),?";
        AddToTable.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e) {
                    if (editMode) {
                        //remve old error line
                        removeErrorLine();
                       // contentPane.remove(ww);
                    }
                    ErrorLine add_error=null;
                    String line = error_line.getText();
                    String message = error_suggest.getText();
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(line);

                    while (matcher.find()) {                                                
                        addError(combo_type.getSelectedItem().toString(), 
                                 combo_SubType.getSelectedItem().toString(),
                                 matcher.group(1), 
                                 matcher.group(2), 
                                 matcher.group(3),  message) ;
                      //  add_error = new ErrorLine(matcher.group(1),(matcher.group(2)),(matcher.group(3))); //filename, begin , end
                      //  MyError me = new MyError(combo_type.getSelectedItem().toString() + ":" + combo_SubType.getSelectedItem().toString(), 
                     //   add_error.getFilename(), add_error.getBegin(), add_error.getEnd(), message);
                      //  table_error.add(me);
                    }
                    copytotable();
                    ((JFrame)mainjf).repaint();
                    newJFrame.dispose();
                } 
            });
        add_error_line.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e) {
                    String temp = error_line.getText();
                    String filename = FileName.getSelectedItem().toString();
                    error_line.setText(temp + filename.replaceAll(".java","") + ":" + error_line_begin.getText() + "~" + error_line_end.getText());
                    error_line_begin.setText("");
                    error_line_end.setText("");
                } 
            });

        if (editMode) {
            int idx =  table.getSelectedRow();   
            if (idx==-1) return;
            String errType = (String)table.getValueAt(idx, 0); 
            Vector<String>  filenames = new Vector<>();// (String)table.getValueAt(idx, 1); 
            Vector<String> begins = new Vector<>();
            Vector<String> ends = new Vector<>();
            for (int r=0; r < table.getRowCount(); r++) {
                if (errType.equals((String)table.getValueAt(r, 0)) ) {
                    filenames.add((String)table.getValueAt(r,1));
                    begins.add((String)table.getValueAt(r,2));
                    ends.add((String)table.getValueAt(r,3));
                }
            }

            String[] types = errType.split(":",2);
            combo_type.setSelectedItem(types[0]);    //set err main type
            combo_SubType.setSelectedItem(types[1]); //set err sub type
            String el = "";
            for (int i=0; i<filenames.size(); i++) {
                el += filenames.get(i) + ":" + begins.get(i) + "~" + ends.get(i) + ",";
            }
            error_line.setText(el);
            error_suggest.setText( (String)table.getValueAt(idx, 4));             
        }
        panel.add(hint); 
        panel.add(combo_type); 
        panel.add(combo_SubType);
        panel.add(hint_1);
        panel.add(FileName);
        panel.add(hint_1_1);
        panel.add(error_line_begin);
        panel.add(hint_1_2);
        panel.add(error_line_end);
        panel.add(add_error_line);
        panel.add(hint_2);
        panel.add(error_line);
        panel.add(hint_3);
        //panel.add(error_suggest);
        panel.add(scroll);
        panel.add(AddToTable);
        newJFrame.setSize(300, 800);
        newJFrame.setLocationRelativeTo(relativeWindow);// 把新窗口的位置设置到 relativeWindow 窗口的中心
        newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// 点击窗口关闭按钮, 执行销毁窗口操作（如果设置为 EXIT_ON_CLOSE, 则点击新窗口关闭按钮后, 整个进程将结束）
        newJFrame.setResizable(false);// 窗口设置为不可改变大小
        newJFrame.setContentPane(panel);
        newJFrame.setVisible(true);

    }
    
    private void addError(String type1, String type2, String filename, String begin, String end, String message) {
         filename.replaceAll(".java","");
         ErrorLine add_error = new ErrorLine(filename,begin,end); //filename, begin , end
         MyError me = new MyError(type1 + ":" + type2, 
         add_error.getFilename(), add_error.getBegin(), add_error.getEnd(), message);
         table_error.add(me);
    }
    public void readErrors(ReadCodeFromXMLDoc st) {
       Vector<ErrorData> elist =  st.readErrors();
       for (ErrorData d: elist)
          addError(d.getType1(),d.getType2(),d.getFileName(),d.getBegin(),d.getEnd(),d.getMessage() );
    
       copytotable();
        ((JFrame)mainjf).repaint();
       }
}
