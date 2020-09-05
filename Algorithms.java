package stuMap;

import java.util.ArrayList;

public class Algorithms {



    /**
     * 据顶点列表和边的权值表，找出从开始位置到结束位置的最短路径
     * 且途中需要经过pass_places里面的地点，需要按顺序返回经过的各个顶点序号
     * @param vertexs  顶点表
     * @param edges_weight  权重表
     * @param start 开始顶点序号
     * @param end   结束结点序号
     * @param pass_places   途径结点序号
     * @return
     */

    public static ArrayList<Integer> findShortestPath(ArrayList<Vertex> vertexs,
                                                      int[][] edges_weight,
                                                      int start,
                                                      int end,
                                                      ArrayList<Integer> pass_places ){
        ArrayList<Integer> test_path = new ArrayList<>();
        test_path.add(1);
        test_path.add(2);
        test_path.add(3);
        test_path.add(10);
        test_path.add(17);
        test_path.add(19);
        test_path.add(20);
        test_path.add(21);




        return test_path;
    }

}
