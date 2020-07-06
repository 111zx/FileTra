import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsTest extends JFrame {
    // �������
    MyPanel1 mp = null;
    public static void main(String[] args) {
        new GraphicsTest();
    }

    public GraphicsTest() {
        // �������
        mp = new MyPanel1();
        // �������
        this.add(mp);
        
        // ���ô���
        this.setSize(400, 300);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

/**
 * @Description: ����һ��MyPanel(���Լ�����������ڻ�ͼ����ʾ��ͼ������)
 */
class MyPanel1 extends JPanel {
    private static final long serialVersionUID = 1L;

    // ��д(����)JPanel��paint����
    public void paint(Graphics g) {
        // 1�����ø��ຯ������ɳ�ʼ������
        super.paint(g);
        // ��һ��ֱ��
        g.drawLine(10, 10, 40, 10);
        // �����α߿�
        g.drawRect(50, 50, 40, 40);
        // ����Բ�߿�
        g.drawOval(130, 130, 60, 60);
        // ��������
        g.setColor(Color.blue);
        g.fillRect(10, 150, 70, 70);
        
        // �������Բ
        // ���û�����ɫ
        g.setColor(Color.red);
        g.fillOval(270, 140, 100, 80);

        // ��λ�����
        g.setColor(Color.red);
        g.setFont(new Font("����", Font.BOLD, 40));
        g.drawString("�������", 150, 100);
        // ������
        g.drawArc(100, 100, 120, 200, 50, 100);
    }
}
