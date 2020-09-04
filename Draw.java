package stuMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Draw extends JPanel {

    ArrayList<Vertex> vertices = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    Boolean flag = true;

    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        if(flag == true){
            drawPoint(vertices, g2d);
        }else{
            drawPoint(vertices, g2d);
            drawPath(path,g2d);
        }


    }

    public void displayPath() {
        flag = false;
        this.repaint();
    }

    //绘制每个顶点在图中的位置
    public void drawPoint(ArrayList<Vertex> vertices, Graphics2D g2d){

        g2d.setColor(Color.RED);//设置画图的颜色
        g2d.fillOval(120, 240, 10, 10);//填充一个矩形
        g2d.fillOval(200, 380, 10, 10);
        g2d.fillOval(100, 500, 10, 10);
        g2d.fillOval(350, 550, 10, 10);
        g2d.fillOval(350, 420, 10, 10);
        g2d.fillOval(280, 200, 10, 10);
        g2d.fillOval(400, 150, 10, 10);
        g2d.fillOval(400, 80, 10, 10);
        g2d.fillOval(460, 240, 10, 10);
        g2d.fillOval(620, 90, 10, 10);
        g2d.fillOval(600, 200, 10, 10);
        g2d.fillOval(750, 200, 10, 10);
        g2d.fillOval(880, 250, 10, 10);
        g2d.fillOval(960, 350, 10, 10);
        g2d.fillOval(1050, 380, 10, 10);
        g2d.fillOval(900, 350, 10, 10);
        g2d.fillOval(780, 300, 10, 10);
        g2d.fillOval(650, 260, 10, 10);
        g2d.fillOval(470, 350, 10, 10);
        g2d.fillOval(530, 370, 10, 10);
        g2d.fillOval(720, 410, 10, 10);
        g2d.fillOval(880, 450, 10, 10);
        g2d.fillOval(750, 510, 10, 10);
        g2d.fillOval(900, 530, 10, 10);
        g2d.fillOval(980, 600, 10, 10);
        g2d.fillOval(1120, 490, 10, 10);
        g2d.fillOval(1030, 430, 10, 10);
        g2d.fillOval(0, 0, 10, 10);


    }

    //根据序列中的结点绘制路线
    public void drawPath(LinkedList<Integer> path, Graphics2D g2d){

        g2d.setColor(Color.BLUE);//设置画图的颜色
        Stroke stroke = new BasicStroke(3.0f);
        g2d.setStroke(stroke);
        g2d.drawLine(120,240,200,380);

    }

}
