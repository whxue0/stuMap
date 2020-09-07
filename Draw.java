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
        Stroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        for(int i = 0; i<path.size()-1 ;i++){
            int n1 = path.get(i);
            int n2 = path.get(i+1);
            int x1 = vertices.get(n1).getX();
            int y1 = vertices.get(n1).getY();
            int x2 = vertices.get(n2).getX();
            int y2 = vertices.get(n2).getY();
            g2d.drawLine(x1+6,y1+6,x2+6,y2+6);
        }

    }

    //绘制所有点与点之间的路径
    public  void drawAllPath(int[][] edges_weight,Graphics2D g2d){

        g2d.setColor(Color.darkGray);//设置画图的颜色
        Stroke stroke = new BasicStroke(3.0f);
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

}
