
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import static java.lang.Thread.sleep;

public class FileTree extends JDialog {
    private JLabel path_ = new JLabel(" ");
    private JTree tree_;
    private static String filepath;
    private static String serverFilePath = "C:\\Users\\14914\\Desktop\\test";
    private static String clientFilePath = "C:\\Users\\14914\\Desktop\\clienttest";
    public static String serverfilename = "C:\\Users\\14914\\Desktop\\test\\moren.txt";

    public FileTree() {
        init();
    }

    public FileTree(String serverfilename) {
    }

    ;

    private void init() {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.setSize(300, 40);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        labelPanel.setLayout(new BorderLayout());
        labelPanel.add(path_, BorderLayout.SOUTH);

        File[] roots = (new PFileSystemView()).getRoots();
        FileNode nod = new FileNode(roots[0]);
        nod.explore();
        tree_ = new JTree(new DefaultTreeModel(nod));
        tree_.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        JScrollPane sp = new JScrollPane(tree_);
        sp.setBorder(BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140)));

        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 19, 0, 0));
        JButton buttonOK = new JButton("OK");
        buttonOK.setPreferredSize(new Dimension(70, 25));
        JButton buttonCanel = new JButton("Canel");
        buttonCanel.setPreferredSize(new Dimension(70, 25));
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCanel);

//canel action
        buttonCanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
//ok action
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                System.err.println(filepath);
                System.out.println(filepath);

                JFrame frame = new JFrame(filepath);
                frame.setBounds(200, 150, 600, 400);
                frame.setLayout(null);
                //JLabel label1=new JLabel("文件：");


                String path = filepath;        //要遍历的路径
                File file = new File(path);        //获取其file对象
                File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
                String[][] datas = new String[10000][2];
                int i = 0;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (File f : fs) {                    //遍历File[]数组
                    if (!f.isDirectory()) {        //若非目录(即文件)，则打印
                        datas[i][0] = f.getName();
                        datas[i][1] = formatter.format(new Date(f.lastModified()));
                        i++;
                    }
                }
                String[] tablehead = {"filename", "last modify time"};
                DefaultTableModel tableModel = new DefaultTableModel(datas, tablehead);

                JTable table = new JTable(tableModel);

                JPanel panelUP = new JPanel();        // 新建按钮组件面板

                JScrollPane s = new JScrollPane(table);

                s.setBounds(10, 10, 450, 600);
                //panelUP.setBounds(5,70,320,550);
                // 将面板和表格分别添加到窗体中

                frame.add(panelUP);
                frame.add(s);


                //jp.add(label1);
//                    list.setBounds(10, 10, 450, 600);
//                    frame.add(list);


                JButton upload = new JButton("upload");
                upload.setBounds(470, 50, 90, 30);
                frame.add(upload);

                JButton download = new JButton("download");
                download.setBounds(470, 150, 90, 30);
                frame.add(download);

                JButton exit = new JButton("exit");
                exit.setBounds(470, 250, 90, 30);
                frame.add(exit);


                frame.setVisible(true);


                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                //文件上传
                upload.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int rowcount = table.getSelectedRow();
                        for (File f : fs) {                    //遍历File[]数组
                            if (datas[rowcount][0].equals(f.getName())) {        //若非目录(即文件)，则打印
                                System.out.println(datas[rowcount][0]);
                                new fileaddsql().fileAdd(f.getName(), f.length(), serverFilePath + "\\" + f.getName());
                            }
                        }
                        try {
                            new FileTransferClient().sendFile(filepath + "\\" + datas[rowcount][0]);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                download.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame1 = new JFrame("server resources");
                        frame1.setBounds(820, 150, 600, 400);
                        frame1.setLayout(null);
                        Vector rowData = PutinStorage.getRows();

                        // 取得haha数据库的aa表的表头数据

                        Vector columnNames = PutinStorage.getHead();
                        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);

                        JTable table1 = new JTable(tableModel);

                        JPanel panelUP1 = new JPanel();        // 新建按钮组件面板

                        JScrollPane s1 = new JScrollPane(table1);

                        s1.setBounds(10, 10, 450, 600);
                        //panelUP.setBounds(5,70,320,550);
                        // 将面板和表格分别添加到窗体中

                        frame1.add(panelUP1);
                        frame1.add(s1);
                        JButton download1 = new JButton("download");
                        download1.setBounds(470, 50, 90, 30);
                        frame1.add(download1);

                        JButton exit1 = new JButton("exit");
                        exit1.setBounds(470, 250, 90, 30);
                        frame1.add(exit1);
                        exit1.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent a) {

                                frame1.dispose();
                            }
                        });
                        download1.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent a) {
                                int rowcount = table1.getSelectedRow();
                                int column = table1.getColumnCount();        // 表格列数
                                int row = table1.getRowCount();        // 表格行数
                                // value数组存放表格中的所有数据
                                String[][] value = new String[row][column];
                                for (int i = 0; i < row; i++) {
                                    for (int j = 0; j < column; j++) {
                                        value[i][j] = table1.getValueAt(i, j).toString().trim();
                                    }
                                }
                                serverfilename = serverFilePath + "\\" + value[rowcount][0];
                                try {
                                    new FileTransferClient("test").sendM(serverfilename);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                new fClient().receive(clientFilePath + "\\" + value[rowcount][0]);
                            }
                        });
                        frame1.setVisible(true);
                        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    }
                });

                exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a) {

                        frame.dispose();
                    }
                });

            }
        });
        tree_.setShowsRootHandles(true);
        tree_.addTreeExpansionListener(new TreeExpansionListener() {
            public void treeCollapsed(TreeExpansionEvent e) {

            }

            public void treeExpanded(TreeExpansionEvent e) {
                TreePath path = e.getPath();
                FileNode node = (FileNode) path.getLastPathComponent();
                if (!node.isExplored()) {
                    DefaultTreeModel model = (
                            (DefaultTreeModel) tree_.getModel());
                    node.explore();
                    model.nodeStructureChanged(node);
                }
            }
        });
        tree_.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JTree tree = (JTree) e.getSource();
                int row = tree.getRowForLocation(e.getX(), e.getY());
                if (row == -1) {
                    return;
                }
                TreePath path = tree.getPathForRow(row);
                if (path == null) {
                    return;
                }
                FileNode node = (FileNode) path.getLastPathComponent();
                if (node == null) {
                    return;
                }
                filepath = node.getString();
                path_.setText(filepath);
            }
        });
        getContentPane().add(sp, BorderLayout.CENTER);
        getContentPane().add(labelPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        setSize(350, 400);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setTitle("File Tree");
    }

    class FileNode extends DefaultMutableTreeNode {
        private boolean explored_ = false;

        public FileNode(File file) {
            setUserObject(file);
        }

        public boolean getAllowChildren() {
            return isDirectory();
        }

        public boolean isLeaf() {
            return !isDirectory();
        }

        public File getFile() {
            return (File) getUserObject();
        }

        public boolean isExplored() {
            return explored_;
        }

        public boolean isDirectory() {
            File file = getFile();
            return file.isDirectory();
        }

        public String toString() {
            File file = getFile();
            String filename = file.toString();
            int index = filename.lastIndexOf("\\");
            return (index != -1 && index != filename.length() - 1) ?
                    filename.substring(index + 1) :
                    filename;
        }

        public String getString() {
            File file = getFile();
            String filename = file.getAbsolutePath();
            return filename;
        }

        public void explore() {
            if (!isDirectory()) {
                return;
            }
            if (!isExplored()) {
                File file = getFile();
                File[] children = file.listFiles();
                for (int i = 0; i < children.length; ++i) {
                    if (children[i].isDirectory()) {
                        add(new FileNode(children[i]));
                    }
                }
                explored_ = true;
            }
        }
    }

    class PFileSystemView extends FileSystemView {
        public File createNewFolder(File containingDir) throws IOException {
            return null;
        }
    }

    public static void main(String[] args) {
        (new FileTree()).show(true);
    }
}
