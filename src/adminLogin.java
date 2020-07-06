

import java.awt.BorderLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


import javax.swing.*;

import javax.swing.table.DefaultTableModel;


public class adminLogin extends JFrame implements ActionListener

{

    private JPasswordField mima1 = new JPasswordField(30);    // �����ı������, 30 ��
    private JTextField zh1 = new JTextField(30);
    private	JButton dl1 = new JButton("��½");
    private	JButton zb1 = new JButton("ע���˺�");
    private JLabel zh2 = new JLabel("�˺�:");
    private JLabel mima2 = new JLabel("����:");
    private static String serverFilePath = "C:\\Users\\14914\\Desktop\\test";
    private static String clientFilePath = "C:\\Users\\14914\\Desktop\\clienttest";
    BackgroundPanel bgp;
    JFrame f;
    String st5 = "1";
    String st6 = "0";
    public void Serverframe(){
        //��ʾ��Ϣ��ʾ��
        JFrame frame1 = new JFrame("server resources");
        frame1.setBounds(820, 150, 600, 400);
        frame1.setLayout(null);
        Vector rowData = PutinStorage.getRows();

        // ȡ��haha���ݿ��aa��ı�ͷ����

        Vector columnNames = PutinStorage.getHead();
        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);

        JTable table1 = new JTable(tableModel);

        JPanel panelUP1 = new JPanel();        // �½���ť������

        JScrollPane s1 = new JScrollPane(table1);

        s1.setBounds(10, 10, 450, 600);
        //panelUP.setBounds(5,70,320,550);
        // �����ͱ��ֱ���ӵ�������

        frame1.add(panelUP1);
        frame1.add(s1);
        JButton download1 = new JButton("remove");
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
                int column = table1.getColumnCount();        // �������
                int row = table1.getRowCount();        // �������
                // value�����ű���е���������
                String[][] value = new String[row][column];
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        value[i][j] = table1.getValueAt(i, j).toString().trim();
                    }
                }
                String serverfilename = serverFilePath + "\\" + value[rowcount][0];
                new fileaddsql().fileRremove(serverfilename);
                File file = new File(serverfilename);
                file.delete();
                JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
                frame1.dispose();
                Serverframe();
            }
        });
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public adminLogin()

    {

        f = new JFrame("��½����");
        f.setBounds(200, 150, 555, 420);
        f.setLayout(null);
        bgp=new BackgroundPanel((new ImageIcon("C:\\Users\\14914\\Desktop\\ͼƬ��תվ\\����\\2.jpg")).getImage());

        bgp.setBounds(0,0,555,188);
        zh1.setBounds(120, 190, 300, 40);
        mima1.setBounds(120, 238, 300, 40);
        dl1.setBounds(120, 300, 300, 46);
        zh2.setBounds(70, 190, 50, 40);
        zb1.setBounds(0, 350, 90, 25);
        mima2.setBounds(70, 238, 50, 40);
        // �������ӽ�����f��
        f.add(mima1);
        f.add(zh1);
        f.add(dl1);
        f.add(zb1);
        f.add(zh2);
        f.add(mima2);
        f.add(bgp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);


        KeyListener key_Listener = new KeyListener()

        {

            public void keyTyped(KeyEvent e) {}

            public void keyReleased(KeyEvent e){}

            public void keyPressed(KeyEvent e){

                if(e.getKeyChar() == KeyEvent.VK_ENTER )

                {
                    PreparedStatement ps=null;
                    Connection ct=null;
                    ResultSet rs=null;
                    String st1 = "null";
                    {
                        try {
                            //1.��������
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            //2.�õ����� 127.0.0.1:1433
                            ct=DriverManager.getConnection
                                    ("jdbc:mysql://127.0.0.1:3306/myfiletra?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8","root","123456");
                            String sql="select password from manager where id = ?";
                            ps=ct.prepareStatement(sql);
                            //������ֵ
                            ps.setString(1, zh1.getText());
                            rs=ps.executeQuery();
                            while(rs.next()){
                                st1=rs.getString(1).trim();
                            }
                        }catch(Exception d) {
                            d.printStackTrace();
                        }finally {
                            try {
                                if(rs!=null)rs.close();
                                if(ps!=null)ps.close();
                                if(ct!=null)ct.close();
                            }catch(Exception d) {
                                d.printStackTrace();
                            }
                        }
                        if(st1.equals(String.valueOf(mima1.getPassword())))

                        {
                            f.setVisible(false);
                            JOptionPane.showMessageDialog(null, "��¼�ɹ�����ӭ������");
                            Serverframe();
                        }

                        else
                        {
                            JOptionPane.showMessageDialog(null, "�û��������������µ�¼��");
                            //��ʾ��Ϣ��ʾ��
                            zh1.setText("");
                            mima1.setText("");

                        }

                    }
                }

            }
        };
        mima1.addKeyListener(key_Listener);
        dl1.addActionListener(this); //��¼�����¼�����
        this.pack();  		//��ʾ��������Զ�������С
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void actionPerformed(ActionEvent q)  //
    {
        if(q.getSource() == dl1) {
            PreparedStatement ps = null;
            Connection ct = null;
            ResultSet rs = null;
            String st1 = "null";
            try {
                //1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.�õ����� 127.0.0.1:1433
                ct = DriverManager.getConnection
                        ("jdbc:mysql://127.0.0.1:3306/myfiletra?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8","root","123456");
                String sql = "select id from manager where id = ?";
                ps = ct.prepareStatement(sql);
                //������ֵ
                ps.setString(1, zh1.getText());
                rs = ps.executeQuery();
                while (rs.next()) {
                    st1 = rs.getString(1).trim();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (ct != null) ct.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(st1.equals(String.valueOf(mima1.getPassword())))

            {
                f.setVisible(false);
                JOptionPane.showMessageDialog(null, "��¼�ɹ�����ӭ������");
                //��ʾ��Ϣ��ʾ��
                (new FileTree()).show(true);;

            }
            else
            {
                JOptionPane.showMessageDialog(null, "�û��������������µ�¼��");
                //��ʾ��Ϣ��ʾ��
                zh1.setText("");
                mima1.setText("");
            }
        }
    }



    public static void main(String[] args)

    {

        JFrame.setDefaultLookAndFeelDecorated(true);
        new adminLogin();

    }

}
