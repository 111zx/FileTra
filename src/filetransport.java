
import java.awt.BorderLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

 

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JPasswordField;

import javax.swing.JTextField;

import javax.swing.SwingConstants;
import java.io.*;
 

public class filetransport extends JFrame implements ActionListener

{

    private JTextField zh1 = new JTextField("����������ļ�����·��:",30);
    private JTextField zh2 = new JTextField("�����뷢���ļ�����·��:",30);
    private	JButton dl1 = new JButton("�����ļ�");
    private	JButton dl2 = new JButton("�����ļ�");
    private	JButton dl3 = new JButton("�˳�");
    public String filename = "";
    BackgroundPanel bgp;
    JFrame f;
    
	public  filetransport(String m_id,String f_id) 

	{
		f = new JFrame("�����ļ�����");
        f.setBounds(200, 150, 330, 200);
        f.setLayout(null);
        bgp=new BackgroundPanel((new ImageIcon("C:\\Users\\14914\\Desktop\\ͼƬ��תվ\\zk\\g1.png")).getImage());

		bgp.setBounds(0,0,330,80);
        dl1.setBounds(215, 90, 105, 25);
        zh1.setBounds(20, 90, 180, 25);
        zh2.setBounds(20, 116, 180, 25);
        dl2.setBounds(215, 116, 105, 25);
        dl3.setBounds(235, 143, 70, 20);

        
        // �������ӽ�����f��
        f.add(zh1);
        f.add(zh2);
        f.add(dl1);
        f.add(dl2);
        f.add(dl3);
        f.add(bgp);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
		
		dl1.addActionListener(this); //��¼�����¼�����
		dl2.addActionListener(this); //��¼�����¼�����
		dl3.addActionListener(this); //��¼�����¼�����
		this.pack();  		//��ʾ��������Զ�������С
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	
	public void actionPerformed(ActionEvent eo)  // ��ʱ����д���

	{
		if(eo.getSource() == dl2){   
			System.out.println("11111");
			try {
				new FileTransferClient().sendFile(zh2.getText());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("22222");
		}
		else if(eo.getSource() == dl1){
			try {
				new fClient().receive(zh1.getText());
				System.out.println("111112");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("111115");
		}
		else if(eo.getSource() == dl3){
			  f.setVisible(false);
		}

	}
	public static void main(String[] args)

	{

		JFrame.setDefaultLookAndFeelDecorated(true);
		new filetransport("","");

	}

}
