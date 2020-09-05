package stuMap;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main  {

    public static ArrayList<Place> places = new ArrayList<>();    //地点列表
    public static ArrayList<Vertex> vertices = new ArrayList<>(); //顶点列表
    public static int[][] edges_weight = new int[27][27];  //边的权重表
    public static JFrame frame = new JFrame("Stu-Map");
    public static Draw jpanel = new Draw();

    public static JComboBox cmb_start = new JComboBox();
    public static CmbListener cmb_start_listener = new CmbListener(cmb_start);
    public static JComboBox cmb_end = new JComboBox();
    public static CmbListener cmb_end_listener = new CmbListener(cmb_end);

    public static JComboBox cmb_pass_places = new JComboBox();
    public static JTextArea show_pass_place = new JTextArea(3,110);
    public static AddPassPlaceListener pass_listener = new AddPassPlaceListener(show_pass_place,cmb_pass_places);


    public static void main(String[] args) {
        TextControll.readPlace(places);//读地点
        TextControll.readVertex(vertices);//读顶点
        TextControll.readVertexPosition(vertices);//读顶点在图中的位置
        TextControll.readEdgesWeight(edges_weight);//读顶点权值

        Main win = new Main();
    }

    public Main(){


        frame.setLocation(200,0);

        //窗口宽度设为1280，匹配图片宽度
        frame.setSize(1280,960);
        frame.setResizable(false);
        jpanel.vertices = vertices;
        jpanel.edges_weight = edges_weight;
        JLabel mapImage = new JLabel(new ImageIcon("src/stuMap/sources/stu_map_v2.jpg"));
        jpanel.add(mapImage);

        Font font_label = new Font("黑体",Font.BOLD,18);

        //起点
        JLabel start = new JLabel("   起点：");
        start.setFont(font_label);
        //存下拉列表数据
        cmb_start.setFont(font_label);
        cmb_start.addItem("------请选择起点------");
        for(int i = 0 ; i<places.size() ;i++){
            cmb_start.addItem(places.get(i).getName());
        }
        cmb_start.addItemListener(cmb_start_listener);
        jpanel.add(start);
        jpanel.add(cmb_start);

        //终点
        JLabel end = new JLabel("   终点：");
        end.setFont(font_label);
        //存下拉列表数据
        cmb_end.setFont(font_label);
        cmb_end.addItem("------请选择终点------");
        for(int i = 0 ; i<places.size() ;i++){
            cmb_end.addItem(places.get(i).getName());
        }
        cmb_end.addItemListener(cmb_end_listener);
        jpanel.add(end);
        jpanel.add(cmb_end);


        //途径地点
        JLabel pass_places = new JLabel("   途径地点：");
        pass_places.setFont(font_label);
        cmb_pass_places.setFont(font_label);
        cmb_pass_places.addItem("------请选择途径地点------");
        for(int i = 0 ; i<places.size() ;i++){
            cmb_pass_places.addItem(places.get(i).getName());
        }
        cmb_pass_places.addItemListener(pass_listener);
        jpanel.add(pass_places);
        jpanel.add(cmb_pass_places);
        show_pass_place.setFont(font_label);
        show_pass_place.setLineWrap(true);
        show_pass_place.setWrapStyleWord(true);

        jpanel.add(show_pass_place);

        JButton delete_place = new JButton("清空");
        delete_place.setFont(font_label);
        delete_place.addActionListener(pass_listener);
        jpanel.add(delete_place);

        JButton get_min_path = new JButton("获取最短路径");
        get_min_path.setFont(font_label);
        JTextArea show_min_path = new JTextArea(4,120);
        show_min_path.setFont(font_label);
        show_min_path.setEditable(false);
        show_min_path.setLineWrap(true);
        show_min_path.setWrapStyleWord(true);

        get_min_path.addActionListener(event -> {
            int start_order = cmb_start_listener.order;
            int end_order = cmb_end_listener.order;
            //取得监听器获取的途径地点序号
            ArrayList<Integer> pass_places_list = pass_listener.pass_places;

            HashSet<Integer> pass_verties = new HashSet<>();
            for(int i = 0 ; i<pass_places_list.size();i++){
                pass_verties.add(places.get(pass_places_list.get(i)).getTo_vertex_order());
            }
            Iterator<Integer> iterator = pass_verties.iterator();
            ArrayList<Integer> pass_verties_list = new ArrayList<>();
            while(iterator.hasNext()){
                pass_verties_list.add(iterator.next());
            }

            ArrayList<Integer> result_verties = Algorithms.findShortestPath(edges_weight,
                                                                        start_order,end_order,
                                                                        pass_places_list);
            jpanel.path = result_verties;
            jpanel.displayPath();
            String result_str = "";
            for(int i = 0 ; i<result_verties.size(); i++){

                LinkedList<Integer> one_vertex_places = vertices.get(result_verties.get(i)).getRelated_places();
                if(one_vertex_places.size() == 1){
                    result_str += places.get(one_vertex_places.get(0)).getName();
                    if(i != result_verties.size()-1) result_str += " -> ";
                }
                else {
                    result_str += "[";
                    for(int j= 0 ; j<one_vertex_places.size()-1;j++){
                        result_str += places.get(one_vertex_places.get(j)).getName() + ",";
                    }
                    result_str += places.get(one_vertex_places.get(one_vertex_places.size()-1)).getName();
                    if(i!=result_verties.size()-1) result_str += "] -> ";
                    else result_str += "]";
                }

            }
            show_min_path.setText(result_str);

        });
        jpanel.add(get_min_path);
        jpanel.add(show_min_path);

        JButton show_all_path = new JButton("显示所有路径");
        show_all_path.setFont(font_label);
        show_all_path.addActionListener(event -> jpanel.displayallPath());
        jpanel.add(show_all_path);


        frame.add(jpanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}


