package stuMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Draw extends JPanel {

    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();
    int[][] edges_weight = new int[27][27];  //边的权重表
    int flag = 1; //1表示只显示点，2表示显示全部路径，3表示显示所求最短路径

    //继承的paint方法
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        if(flag == 1){
            drawPoint(vertices, g2d);
        }else if(flag == 2){
            drawPoint(vertices, g2d);
            drawAllPath(edges_weight,g2d);
        }else if(flag == 3){
            drawPoint(vertices, g2d);
            drawPath(path,g2d);
            //drawAllPath(edges_weight,g2d);
        }

    }

    //显示最短路径，更改flag，调用repaint。
    public void displayPath() {
        if(flag != 3)flag = 3;
        this.repaint();
    }

    //显示所有路径，更改flag，调用repaint
    public void displayallPath() {
        if(flag != 2)flag = 2;
        else flag = 1;
        this.repaint();
    }

    //绘制每个顶点在图中的位置
    public void drawPoint(ArrayList<Vertex> vertices, Graphics2D g2d){

        g2d.setColor(Color.RED);//设置画图的颜色

        for(int i = 0 ; i<vertices.size() ; i++){
            g2d.fillOval(vertices.get(i).getX(),vertices.get(i).getY(), 12,12);
        }

    }

    //根据序列中的结点绘制路线
    public void drawPath(ArrayList<Integer> path, Graphics2D g2d){

        g2d.setColor(Color.BLUE);//设置画图的颜色
        Stroke stroke = new BasicStroke(3.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);

        g2d.setStroke(stroke);
        for(int i = 0; i<path.size()-1 ;i++){
            int n1 = path.get(i);
            int n2 = path.get(i+1);
            int x1 = vertices.get(n1).getX();
            int y1 = vertices.get(n1).getY();
            int x2 = vertices.get(n2).getX();
            int y2 = vertices.get(n2).getY();
            g2d.drawLine(x1+6,y1+6,x2+6,y2+6);
            drawAL(x1+6,y1+6,x2+6,y2+6,g2d);
        }

    }

    //绘制所有点与点之间的路径
    public  void drawAllPath(int[][] edges_weight,Graphics2D g2d){

        g2d.setColor(Color.darkGray);//设置画图的颜色
        Stroke stroke = new BasicStroke(3.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
        g2d.setStroke(stroke);

        for(int i =0 ;i<edges_weight.length;i++){
            for(int j =i;j<edges_weight[i].length;j++){
                if(edges_weight[i][j] < 99999 && i!=j){
                    int x1 = vertices.get(i).getX();
                    int y1 = vertices.get(i).getY();
                    int x2 = vertices.get(j).getX();
                    int y2 = vertices.get(j).getY();
                    g2d.drawLine(x1+6,y1+6,x2+5,y2+5);
                }
            }
        }

    }

    private static void drawAL(int sx, int sy, int ex, int ey, Graphics2D g2) {
        double H = 40; // 箭头高度
        double L = 20; // 底边的一半
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H); // 箭头角度
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
        double y_4 = ey - arrXY_2[1];

        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();
        //起始线
        g2.drawLine(sx, sy, ex, ey);
        //箭头
        g2.drawLine(ex, ey, x3, y3);
        g2.drawLine(ex, ey, x4, y4);
    }
    // 计算
    private static double[] rotateVec(int px, int py, double ang,
                                      boolean isChLen, double newLen) {
        double mathstr[] = new double[2];
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }
}
