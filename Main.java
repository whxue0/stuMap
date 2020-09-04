package stuMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main  {

    ArrayList<Place> Places = new ArrayList<>();    //地点列表
    ArrayList<Vertex> vertices = new ArrayList<>(); //顶点列表
    ArrayList<ArrayList<Integer>> edges_weight = new ArrayList<ArrayList<Integer>>();   //边的权重表


    public static void main(String[] args) {
        Main win = new Main();
    }

    public Main(){

        JFrame frame = new JFrame("Stu-Map");
        frame.setLocation(200,100);

        //窗口宽度设为1280，匹配图片宽度
        frame.setSize(1280,900);

        Draw jpanel = new Draw();
        JLabel mapImage = new JLabel(new ImageIcon("src/stuMap/sources/stu_map_v2.jpg"));
        jpanel.add(mapImage);
        //jpanel.displayPath();
        frame.add(jpanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}


