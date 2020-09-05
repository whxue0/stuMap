package stuMap;

import java.util.ArrayList;

public class Algorithms {



    /**
     * 跟据权值表，找出从开始位置到结束位置的最短路径
     * 且途中需要经过pass_places里面的地点，需要按顺序返回经过的各个顶点序号
     * @param edges_weight  权重表
     * @param start 开始顶点序号
     * @param end   结束结点序号
     * @param pass_places   途径结点序号
     * @return 按顺序返回一个列表表示路径（包含start 和 end）
     *
     *     小明想从地点start去到地点end，但是他还要途经多个顶点，请帮小明找出一条最短路径吧。
     *     pass_places是小明途径的地点列表,start、end、pass_places中的地点不会重复。
     *     edges_weight[i][j] 表示地点i到地点j的距离，edges_weight[i][j] == edges_weight[j][i]，
     * 且edges_weight[i][i]==0,edges_weight[i][j] == -1 表示两点不直接可达。
     *     结果需要返回小明需要的最短路径列表（返回的列表需要包含start和end）
     *
     */
    public static ArrayList<Integer> findShortestPath(int[][] edges_weight,
                                                      int start,
                                                      int end,
                                                      ArrayList<Integer> pass_places ){
        return new ArrayList<Integer>();
    }

}
