
import java.awt.*;

 

import javax.swing.*;

 

 

public class Test extends JFrame

{

	//����һ������

	Container ct;

	//����������塣

	BackgroundPanel bgp;

	

	//����һ����ť������֤�����ǵ�ȷ�Ǵ����˱���ͼƬ��������һ��ͼƬ��

	JButton jb;

	public static void main(String[] args)

	{

		new Test();

	}

	public Test()

	{

		//�������κβ��ַ�ʽ��

		ct=this.getContentPane();

		this.setLayout(null);

		

		//�����������һ��400*300����Ƭ�ȿ��Կ������Խ����

		bgp=new BackgroundPanel((new ImageIcon("C:\\Users\\14914\\Pictures\\background.jpg")).getImage());

		bgp.setBounds(0,0,400,300);

		ct.add(bgp);

		

		//������ť

		jb=new JButton("���԰�ť");

		jb.setBounds(60,30,160,30);

		ct.add(jb);

		

		this.setSize(400,300);

		this.setLocation(400,300);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}

}

class BackgroundPanel extends JPanel

{

	Image im;

	public BackgroundPanel(Image im)

	{

		this.im=im;

		this.setOpaque(true);

	}

	//Draw the back ground.

	public void paintComponent(Graphics g)

	{

		super.paintComponents(g);

		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);

		

	}

}
