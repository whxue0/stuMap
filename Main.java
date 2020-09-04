package stuMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main  {

    public static ArrayList<Place> places = new ArrayList<>();    //地点列表
    public static ArrayList<Vertex> vertices = new ArrayList<>(); //顶点列表
    public static int[][] edges_weight = new int[27][27];  //边的权重表
    public static JFrame frame = new JFrame("Stu-Map");
    public static Draw jpanel = new Draw();

    public static void main(String[] args) {
        TextControll.readPlace(places);//读地点
        TextControll.readVertex(vertices);//读顶点
        TextControll.readVertexPosition(vertices);//读顶点在图中的位置
        TextControll.readEdgesWeight(edges_weight);//读顶点权值
        ArrayList<Integer> test_path = new ArrayList<>();
        test_path.add(1);
        test_path.add(2);
        test_path.add(3);
        test_path.add(10);
        test_path.add(17);
        test_path.add(19);
        test_path.add(20);
        test_path.add(21);
        jpanel.path = test_path;
        jpanel.displayPath();
        Main win = new Main();
    }

    public Main(){


        frame.setLocation(200,100);

        //窗口宽度设为1280，匹配图片宽度
        frame.setSize(1280,900);

        jpanel.vertices = vertices;
        jpanel.edges_weight = edges_weight;
        JLabel mapImage = new JLabel(new ImageIcon("src/stuMap/sources/stu_map_v2.jpg"));
        jpanel.add(mapImage);
        //jpanel.displayPath();
        frame.add(jpanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}


