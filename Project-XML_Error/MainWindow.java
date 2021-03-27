import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
public class MainWindow extends JFrame {
    WorkWindow ww;
    static JFrame jf;
    Container contentPane;
    Object lock = new Object();
    
    public static void main(String[] args) {
        MainWindow editor = new MainWindow();
        editor.init();  //匿名函數
    }

    public void init() {
        contentPane = getContentPane();  //宣告一個容器
        contentPane.add(buildMenu(),"North");
        ww = new WorkWindow(this);
        contentPane.add(ww,"Center");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1400, 1400);
        setVisible(true);
                
    }

    public JMenuBar buildMenu() {
        JMenuBar menuBar = new JMenuBar();
        //创建一级菜单
        JMenu fileMenu = new JMenu("文件");
        JMenu editMenu = new JMenu("编辑");
        // 一级菜单添加到菜单栏
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        //创建 "文件" 一级菜单的子菜单
        JMenuItem openMenuItem = new JMenuItem("開啟JAVA舊檔");
        JMenuItem openXMLMenuItem = new JMenuItem("開啟XML舊檔");
        JMenuItem saveMenuItem = new JMenuItem("儲存XML");
        JMenuItem saveAsMenuItem = new JMenuItem("另儲存XML");
        JMenuItem displayMenuItem = new JMenuItem("清空視窗");
        
        // 子菜单添加到一级菜单
        fileMenu.add(openMenuItem);
        fileMenu.addSeparator();       // 添加一条分割线
        fileMenu.add(openXMLMenuItem);
        fileMenu.addSeparator();       // 添加一条分割线
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();       // 添加一条分割线
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();       // 添加一条分割线
        fileMenu.add(displayMenuItem);
        
        // 创建 "编辑" 一级菜单的子菜单
        JMenuItem new_errorMenuItem = new JMenuItem("新增錯誤");

        JMenuItem updateMenuItem = new JMenuItem("編輯錯誤");

        JMenuItem deleteMenuItem = new JMenuItem("刪除一整列錯誤");

        // 子菜单添加到一级菜单
        editMenu.add(new_errorMenuItem);
        editMenu.addSeparator();   
        editMenu.add(updateMenuItem);
        editMenu.addSeparator();   
        editMenu.add(deleteMenuItem);

        openMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                //clearWindow();
                ww.readfile(new ReadCodeFromFile() );
                ww.updateCurrentFileName("");
                }
            });
        openXMLMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   //clearWindow();
                   ReadCodeFromXMLDoc rd = new ReadCodeFromXMLDoc(ww);
                    ww.readfile( rd );
                    ww.readErrors(rd);
                }
            });
        saveMenuItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ww.showSave_XmlWindow(jf);
                    //clearWindow();
                }
            });
        saveAsMenuItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ww.showNew_XmlWindow(jf);
                    //clearWindow();
                }
            });
     
        new_errorMenuItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ww.showNew_ErrorWindow(jf, false);
                }
            });
        updateMenuItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    //open new update winodw                   
                    ww.showNew_ErrorWindow(jf, true);
                }
            });
        deleteMenuItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ww.removeErrorLine();
                    contentPane.remove(ww);
                }
            });
        displayMenuItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearWindow();
                    ww.updateCurrentFileName("");
                }
            });
        return menuBar;
    }

    public void clearWindow(){
        //this.getContentPane().remove(ww);
        WorkWindow.currentFileName="";
        ww.contentPane.remove(ww.SourceCode); 
        ww.contentPane.remove(ww.table_panel);
        ww.table_error.clear();
        ww = new WorkWindow(this);
        
        contentPane.add(ww,"Center");
        repaint();
        setVisible(true);
    }
}