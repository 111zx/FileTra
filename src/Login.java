

import java.awt.BorderLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.ActionListener;
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

 

public class Login extends JFrame implements ActionListener

{

	private JPasswordField mima1 = new JPasswordField(30);    // 创建文本行组件, 30 列
    private JTextField zh1 = new JTextField(30);
    private	JButton dl1 = new JButton("登陆");
    private	JButton zb1 = new JButton("注册账号");
    private JLabel zh2 = new JLabel("账号:");
    private JLabel mima2 = new JLabel("密码:");
    BackgroundPanel bgp;
    JFrame f;
    String st5 = "1";
    String st6 = "0";

	public Login() 

	{

		f = new JFrame("登陆界面");
        f.setBounds(200, 150, 555, 420);
        f.setLayout(null);
        bgp=new BackgroundPanel((new ImageIcon("C:\\Users\\14914\\Desktop\\图片中转站\\背景\\2.jpg")).getImage());

		bgp.setBounds(0,0,555,188);
        zh1.setBounds(120, 190, 300, 40);
        mima1.setBounds(120, 238, 300, 40);
        dl1.setBounds(120, 300, 300, 46);
        zh2.setBounds(70, 190, 50, 40);
        zb1.setBounds(0, 350, 90, 25);
        mima2.setBounds(70, 238, 50, 40);
        // 把组件添加进窗口f中
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
		                //1.加载驱动
							Class.forName("com.mysql.cj.jdbc.Driver");
		                //2.得到链接 127.0.0.1:1433
		                ct=DriverManager.getConnection
		                ("jdbc:mysql://127.0.0.1:3306/myfiletra?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8","root","123456");
		               String sql="select password from user where id = ?";
		                ps=ct.prepareStatement(sql);
		                //给？赋值
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
						JOptionPane.showMessageDialog(null, "登录成功，欢迎到来！");
						//显示信息提示框
						(new FileTree()).show(true);;

					}
					else
					{
						JOptionPane.showMessageDialog(null, "用户或密码错误！请从新登录！");
						//显示信息提示框
						zh1.setText("");
						mima1.setText("");

					}

				}
				}

			}
		};
		mima1.addKeyListener(key_Listener);
		dl1.addActionListener(this); //登录增加事件监听
		this.pack();  		//表示随着面板自动调整大小
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
				//1.加载驱动
				Class.forName("com.mysql.cj.jdbc.Driver");
				//2.得到链接 127.0.0.1:1433
				ct = DriverManager.getConnection
						("jdbc:mysql://127.0.0.1:3306/myfiletra?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8","root","123456");
				String sql = "select id from user where id = ?";
				ps = ct.prepareStatement(sql);
				//给？赋值
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
				JOptionPane.showMessageDialog(null, "登录成功，欢迎到来！");
				//显示信息提示框
				(new FileTree()).show(true);;

			}
			else
			{
				JOptionPane.showMessageDialog(null, "用户或密码错误！请从新登录！");
				//显示信息提示框
				zh1.setText("");
				mima1.setText("");
			}
		}
		}



	public static void main(String[] args)

	{

		JFrame.setDefaultLookAndFeelDecorated(true);
		new Login();

	}

}
