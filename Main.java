package stuMap;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main  {
    final static int verties_num = 27; //顶点个数
    public static ArrayList<Place> places = new ArrayList<>();    //地点列表
    public static ArrayList<Vertex> vertices = new ArrayList<>(); //顶点列表
    public static int[][] edges_weight = new int[verties_num][verties_num];  //边的权重表

    public static void main(String[] args) {
        TextControll.readPlace(places);//读地点
        TextControll.readVertex(vertices);//读顶点
        TextControll.readVertexPosition(vertices);//读顶点在图中的位置
        TextControll.readEdgesWeight(edges_weight);//读顶点权值

        Main win = new Main();

    }

    /**
     * 构造方法，创建窗口
     */
    public Main(){

        JFrame frame = new JFrame("Stu-Map");//主框架
        Draw jpanel = new Draw();//绘图面板
        JComboBox cmb_start = new JComboBox();  //起点选择框
        CmbListener cmb_start_listener = new CmbListener(cmb_start);    //创建起点监听器对象
        JComboBox cmb_end = new JComboBox();    //终点选择框
        CmbListener cmb_end_listener = new CmbListener(cmb_end);        //创建终点监听器对象
        JComboBox cmb_pass_places = new JComboBox();    //途径地点选择框
        JTextArea show_pass_place = new JTextArea(3,110);   //途径地点文本域
        //创建途径地点监听器对象
        AddPassPlaceListener pass_listener = new AddPassPlaceListener(show_pass_place,cmb_pass_places);
        //创建图片对象
        JLabel mapImage = new JLabel(new ImageIcon("src/stuMap/sources/stu_map_v2.jpg"));
        //创建字体对象
        Font font_label = new Font("黑体",Font.BOLD,18);


        frame.setLocation(200,0);
        frame.setSize(1280,960); //窗口宽度设为1280，匹配图片宽度
        frame.setResizable(false);

        //给面板赋值
        jpanel.vertices = vertices;
        jpanel.edges_weight = edges_weight;

        //添加图片到面板中
        jpanel.add(mapImage);

        //-------------起点---------------
        JLabel start = new JLabel("   起点：");
        start.setFont(font_label);
        cmb_start.setFont(font_label);
        //存下拉列表数据
        cmb_start.addItem("------请选择起点------");
        for(int i = 0 ; i<places.size() ;i++){
            cmb_start.addItem(places.get(i).getName());
        }
        //注册监听器
        cmb_start.addItemListener(cmb_start_listener);
        //添加下拉菜单到面板
        jpanel.add(start);
        jpanel.add(cmb_start);

        //-------------终点----------------
        JLabel end = new JLabel("   终点：");
        end.setFont(font_label);
        cmb_end.setFont(font_label);
        //存下拉列表数据
        cmb_end.addItem("------请选择终点------");
        for(int i = 0 ; i<places.size() ;i++){
            cmb_end.addItem(places.get(i).getName());
        }
        //注册监听器
        cmb_end.addItemListener(cmb_end_listener);
        //添加下拉菜单到面板
        jpanel.add(end);
        jpanel.add(cmb_end);

        //---------------途径地点--------------------
        JLabel pass_places = new JLabel("   途径地点：");
        pass_places.setFont(font_label);
        cmb_pass_places.setFont(font_label);
        //存入数据
        cmb_pass_places.addItem("------请选择途径地点------");
        for(int i = 0 ; i<places.size() ;i++){
            cmb_pass_places.addItem(places.get(i).getName());
        }
        //注册监听器
        cmb_pass_places.addItemListener(pass_listener);
        //添加下拉菜单到面板
        jpanel.add(pass_places);
        jpanel.add(cmb_pass_places);
        //文本域设置
        show_pass_place.setFont(font_label);
        show_pass_place.setLineWrap(true);
        show_pass_place.setWrapStyleWord(true);
        show_pass_place.setEditable(false);   //设置途径地点文本域不可编辑
        //添加文本域到面板
        jpanel.add(show_pass_place);
        //清空按钮
        JButton delete_place = new JButton("清空");
        delete_place.setFont(font_label);
        //注册监听器
        delete_place.addActionListener(pass_listener);
        //添加按钮到面板
        jpanel.add(delete_place);
        //获取最短路径按钮
        JButton get_min_path = new JButton("获取最短路径");
        get_min_path.setFont(font_label);
        //显示最短路径的文本域
        JTextArea show_min_path = new JTextArea(4,120);
        //文本域设置
        show_min_path.setFont(font_label);
        show_min_path.setEditable(false);
        show_min_path.setLineWrap(true);
        show_min_path.setWrapStyleWord(true);

        //Lamda表达式重写按钮监听器的行为方法并注册
        get_min_path.addActionListener(event -> {
            //获取监听器的开始和结束地点序号并转换成顶点序号
            int start_order = cmb_start_listener.order;
            int end_order = cmb_end_listener.order;
            start_order = places.get(start_order).getTo_vertex_order();
            end_order = places.get(end_order).getTo_vertex_order();

            //取得监听器获取的途径地点序号
            ArrayList<Integer> pass_places_list = pass_listener.pass_places;
            //hashset去重，将地点序号转换成顶点序号
            HashSet<Integer> pass_verties = new HashSet<>();
            for(int i = 0 ; i<pass_places_list.size();i++){
                pass_verties.add(places.get(pass_places_list.get(i)).getTo_vertex_order());
            }
            //迭代器获取hashset中的数据
            Iterator<Integer> iterator = pass_verties.iterator();
            //再将途径地点存入数组列表中。
            ArrayList<Integer> pass_verties_list = new ArrayList<>();
            while(iterator.hasNext()){
                pass_verties_list.add(iterator.next());
            }

            //result_verties为需要显示的路径的顶点序号列表
            ArrayList<Integer> result_verties;

            //如果途径结点为空，则直接计算并返回两点之间的最短路径
            //此算法有bug
//            if(pass_places_list.isEmpty()){
//                result_verties = Algorithms.findShortestPath_two_verties(edges_weight,start_order,end_order);
//            }else{
//                result_verties = Algorithms.findShortestPath(edges_weight, start_order,end_order, pass_verties_list);
//            }

            //更快的最短路径算法
            result_verties = Algorithms2.findShortestPath(edges_weight,start_order,end_order,pass_verties_list);


            //将结果传给绘图对象
            jpanel.path = result_verties;
            //显示路径
            jpanel.displayPath();

            //文字显示最短路径，用字符串result_str存储
            String result_str = "";
            for(int i = 0 ; i<result_verties.size(); i++){
                //用一个链表存储一个顶点的代表的地点
                LinkedList<Integer> one_vertex_places = vertices.get(result_verties.get(i)).getRelated_places();
                //如果该顶点只表示一个地点，加入该地点到字符串中。
                if(one_vertex_places.size() == 1){
                    result_str += places.get(one_vertex_places.get(0)).getName();
                    if(i != result_verties.size()-1) result_str += " -> ";
                }
                //否则需要加入该顶点包含的所有地点，以数组形式呈现。
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
            //设置文本域内容
            show_min_path.setText(result_str);

        });//监听器对象

        //添加显示最短路径的按钮
        jpanel.add(get_min_path);
        //添加显示最短路径的文本域
        jpanel.add(show_min_path);

        //---------显示所有路径的按钮---------------
        JButton show_all_path = new JButton("显示所有路径");
        show_all_path.setFont(font_label);
        //添加监听器并设置行为方法
        show_all_path.addActionListener(event -> jpanel.displayallPath());

        //添加显示所有路径的按钮到面板
        jpanel.add(show_all_path);

        //添加面板到框架中。
        frame.add(jpanel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //计算两点间的距离作为权值，已记录到文件中，可忽略，当顶点位置变动才需要重新计算。
    public static  void culculateWeight(ArrayList<Vertex> vertices,int[][] edges_weight){
        for(int i = 0 ; i<edges_weight.length ; i++){
            for(int j = i ; j<edges_weight.length ;j++){
                if(edges_weight[i][j] > 0 && edges_weight[i][j] < 99999){
                    int x1 = vertices.get(i).getX();
                    int y1 = vertices.get(i).getY();
                    int x2 = vertices.get(j).getX();
                    int y2 = vertices.get(j).getY();
                    int dis = (int)Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
                    System.out.println(vertices.get(i).getOrder() + " " + vertices.get(j).getOrder() + " " + dis);
                }
            }
        }
    }

}


