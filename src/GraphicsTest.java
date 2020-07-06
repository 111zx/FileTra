import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsTest extends JFrame {
    // 定义组件
    MyPanel1 mp = null;
    public static void main(String[] args) {
        new GraphicsTest();
    }

    public GraphicsTest() {
        // 构建组件
        mp = new MyPanel1();
        // 加入组件
        this.add(mp);
        
        // 设置窗体
        this.setSize(400, 300);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

/**
 * @Description: 定义一个MyPanel(我自己的面板是用于绘图和显示绘图的区域)
 */
class MyPanel1 extends JPanel {
    private static final long serialVersionUID = 1L;

    // 重写(覆盖)JPanel的paint方法
    public void paint(Graphics g) {
        // 1、调用父类函数，完成初始化任务
        super.paint(g);
        // 画一个直线
        g.drawLine(10, 10, 40, 10);
        // 画矩形边框
        g.drawRect(50, 50, 40, 40);
        // 画椭圆边框
        g.drawOval(130, 130, 60, 60);
        // 画填充矩形
        g.setColor(Color.blue);
        g.fillRect(10, 150, 70, 70);
        
        // 画填充椭圆
        // 设置画笔颜色
        g.setColor(Color.red);
        g.fillOval(270, 140, 100, 80);

        // 如何画出字
        g.setColor(Color.red);
        g.setFont(new Font("黑体", Font.BOLD, 40));
        g.drawString("祖国万岁", 150, 100);
        // 画弧形
        g.drawArc(100, 100, 120, 200, 50, 100);
    }
}
