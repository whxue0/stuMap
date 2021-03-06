package stuMap;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class TextControll {

    //读取stu_map_places.txt文件数据建立地点表
    public static void readPlace(ArrayList<Place> places)  {

        try{
            String oneLine = null;
            FileReader freader = new FileReader("src/stuMap/sources/stu_map_places.txt");
            BufferedReader bfreader = new BufferedReader(freader);
            oneLine = bfreader.readLine(); //第一行表头可忽略
            while((oneLine = bfreader.readLine()) != null){
                String[] items = oneLine.split(" ");
                int order = Integer.parseInt(items[0]);
                String name = items[1];
                int to_vertex_order = Integer.parseInt(items[2]);
                Place onePlace = new Place(order,to_vertex_order,name);
                places.add(onePlace);
            }
            freader.close();

        }catch (FileNotFoundException e){
            System.out.println("找不到文件");
        }catch (IOException e){
            System.out.println("文件读取错误");
        }
    }

    //读取stu_map_Vertex.txt文件数据建立顶点表
    public static void readVertex(ArrayList<Vertex> vertices){

        try{
            String oneLine = null;
            FileReader freader = new FileReader("src/stuMap/sources/stu_map_Vertexs.txt");
            BufferedReader bfreader = new BufferedReader(freader);
            oneLine = bfreader.readLine(); //第一行表头可忽略
            while((oneLine = bfreader.readLine()) != null){
                String[] items = oneLine.split(" ");
                int order = Integer.parseInt(items[0]);
                LinkedList<Integer> related_places = new LinkedList<>();
                for(int i = 1; i<items.length; i++){
                    related_places.add(Integer.parseInt(items[i]));
                }
                Vertex oneVertex = new Vertex(order,related_places,0,0);
                vertices.add(oneVertex);
            }
            freader.close();

        }catch (FileNotFoundException e){
            System.out.println("找不到文件");
        }catch (IOException e){
            System.out.println("文件读取错误");
        }
    }

    //读取stu_map_Vertexs_position.txt结点位置
    public static void readVertexPosition(ArrayList<Vertex> vertices){

        try{
            String oneLine = null;
            FileReader freader = new FileReader("src/stuMap/sources/stu_map_Vertexs_position.txt");
            BufferedReader bfreader = new BufferedReader(freader);
            oneLine = bfreader.readLine(); //第一行表头忽略
            while((oneLine = bfreader.readLine()) != null){
                String[] items = oneLine.split(" ");
                int order = Integer.parseInt(items[0]);
                int x_pos = Integer.parseInt(items[1]);
                int y_pos = Integer.parseInt(items[2]);
                vertices.get(order).setX(x_pos);
                vertices.get(order).setY(y_pos);
            }
            freader.close();

        }catch (FileNotFoundException e){
            System.out.println("找不到文件");
        }catch (IOException e){
            System.out.println("文件读取错误");
        }

    }

    //读取stu_map_Edges_weight.txt文件数据建立权重表
    public static void readEdgesWeight(int[][] edges_weight){
        try{
            String oneLine = null;
            FileReader freader = new FileReader("src/stuMap/sources/stu_map_Edges_weight.txt");
            BufferedReader bfreader = new BufferedReader(freader);
            oneLine = bfreader.readLine(); //第一行表头忽略

            for(int i = 0; i<edges_weight.length;i++){
                for(int j = 0; j<edges_weight[i].length; j++){
                    if(i==j) edges_weight[i][j] = 0;
                    else edges_weight[i][j] = 99999;
                }
            }

            while((oneLine = bfreader.readLine()) != null){
                String[] items = oneLine.split(" ");
                int one = Integer.parseInt(items[0]);
                int other = Integer.parseInt(items[1]);
                int weight = Integer.parseInt(items[2]);
                edges_weight[one][other] = weight;
                edges_weight[other][one] = weight;
            }
            freader.close();

        }catch (FileNotFoundException e){
            System.out.println("找不到文件");
        }catch (IOException e){
            System.out.println("文件读取错误");
        }
    }
}
