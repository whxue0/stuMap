package stuMap;

import java.util.*;

public class Algorithms {

    static int count = 0; //用来计数暴力数组violent[count][]此时进行到的序号。
    final static int verties_num = 27; //顶点个数
    /**
     * 跟据权值表，找出从开始位置到结束位置的最短路径
     * 且途中需要经过pass_places里面的地点，需要按顺序返回经过的各个顶点序号
     * @param edges_weight  权重表
     * @param start 开始顶点序号
     * @param end   结束结点序号
     * @param pass_verties   途径结点序号
     * @return 按顺序返回一个列表表示路径（包含start 和 end）
     *
     */
    public static ArrayList<Integer> findShortestPath(int[][] edges_weight,
                                                      int start,
                                                      int end,
                                                      ArrayList<Integer> pass_verties ){

        int [][]d = new int[verties_num][verties_num]; //d[i][j]表示i到j的最短路径的距离。
        //p[i][j][u]==1表示i到j的最短路径上经过u，等于0反之。
        int [][][]p = new int[verties_num][verties_num][verties_num];

        //初始化d[][]和p[][][]
        for(int v = 0 ; v<verties_num; v++){
            for(int w = 0 ; w<verties_num;w++){
                d[v][w] = edges_weight[v][w];
                for(int u = 0; u<verties_num; u++){
                    if(d[v][w] < 99999){
                        p[v][w][v] = 1;
                        p[v][w][w] = 1;
                    }
                }
            }
        }
        //如果v到u，u再到w 的距离之和 小于 v到w的距离，那么令d[v][w] = d[v][u] + d[u][w]；
        //并且让存在于vu和uw路径上的顶点也在vw中，即p[v][w][i] = (p[v][u][i] == 1 || p[u][w][i] == 1)?1:0;
        for(int u = 0; u < verties_num ; u++){
            for(int v = 0 ; v<verties_num; v++){
                for(int w = 0 ; w <verties_num ; w++)
                    if(d[v][u]+d[u][w] < d[v][w]){
                        d[v][w] = d[v][u] + d[u][w];
                        for(int i = 0 ; i<verties_num ; i++){
                            p[v][w][i] = (p[v][u][i] == 1 || p[u][w][i] == 1)?1:0;
                        }
                    }
            }
        }

        ArrayList<Integer> all_verties = new ArrayList<>();
        all_verties.add(start);
        all_verties.addAll(pass_verties);
        all_verties.add(end);

        //violent表示途径顶点数的阶乘
        int violent=1;
        for(int i =0 ; i<pass_verties.size();i++){
            violent*=i+1;
        }
        int [][]violent_arr = new int[violent][all_verties.size()];
        for(int i =0 ; i<violent ;i++){
            violent_arr[i][0] = start;
            violent_arr[i][all_verties.size()-1] = end;
        }
        int []pass_verties_arr = new int[pass_verties.size()];
        for(int i = 0 ; i<pass_verties.size();i++){
            pass_verties_arr[i] = pass_verties.get(i);
        }
        count = 0;
        getAllOrder(0,pass_verties_arr.length-1,pass_verties_arr,violent_arr);

        //计算所有排序的路径长度，寻找最短的那条的顶点顺序
        //获得的最短路径的顶点顺序，在violent_arr[min_total_index]中。
        int min_total_index = 0;
        int min_total_dis = 99999;
        int dis;
        for(int i = 0 ; i<violent_arr.length ; i++){
            dis = 0;
            for(int j = 0  ; j<violent_arr[i].length - 1 ;j++){
                dis += d[violent_arr[i][j]][violent_arr[i][j+1]];
                //System.out.print(d[violent_arr[i][j]][violent_arr[i][j+1]] + ",");
            }
            if(dis<min_total_dis){
                min_total_dis = dis;
                min_total_index = i;
            }
        }

        //结果存在result列表中。
        ArrayList<Integer> result  = new ArrayList<>();

        //按照最短路径的顺序，求出每两个点的最短路径
        for(int i = 0 ; i<violent_arr[min_total_index].length-1 ; i++){

            int start_vertex = violent_arr[min_total_index][i];
            int end_vertex = violent_arr[min_total_index][i+1];

            ArrayList<Integer> two_verties = new ArrayList<>();
            for(int j = 0 ; j<verties_num; j++){
                if(p[start_vertex][end_vertex][j] == 1) {
                    two_verties.add(j);
                }
            }

            two_verties.remove((Object)start_vertex);
            two_verties.remove((Object)end_vertex);

            result.add(start_vertex);

            for(int l = 0 ; l<two_verties.size();l++){
                for(int m = 0 ; m<verties_num ; m++){
                    if( edges_weight[start_vertex][m]<99999 && two_verties.contains(m) && !result.contains(m)) {
                        start_vertex = m;
                        result.add(m);
                        break;
                    }
                }
            }

        }

        result.add(end);//还要添加终点

        return result;

    }

    /**
     * 返回点到点的最短距离包含地点的数组
     * @param edges_weight
     * @param start
     * @param end
     * @return
     */
    public static ArrayList<Integer> findShortestPath_two_verties(int[][] edges_weight, int start, int end){

        int [][]d = new int[verties_num][verties_num];
        int [][][]p = new int[verties_num][verties_num][verties_num];

        for(int v = 0 ; v<verties_num; v++){
            for(int w = 0 ; w<verties_num;w++){
                d[v][w] = edges_weight[v][w];
                for(int u = 0; u<verties_num; u++){
                    if(d[v][w] < 99999){
                        p[v][w][v] = 1;
                        p[v][w][w] = 1;
                    }
                }
            }
        }

        for(int u = 0; u < verties_num ; u++){
            for(int v = 0 ; v<verties_num; v++){
                for(int w = 0 ; w <verties_num ; w++)
                    if(d[v][u]+d[u][w] < d[v][w]){
                        d[v][w] = d[v][u] + d[u][w];
                        for(int i = 0 ; i<verties_num ; i++){
                            p[v][w][i] = (p[v][u][i] == 1 || p[u][w][i] == 1)?1:0;
                        }
                    }
            }
        }

        ArrayList<Integer> two_verties = new ArrayList<>(); //存储两个顶点最短路径经过的顶点
        for(int i = 0 ; i<verties_num; i++){
            if(p[start][end][i] == 1) {
                two_verties.add(i);
            }
        }
        two_verties.remove((Object)start);
        two_verties.remove((Object)end);

        ArrayList<Integer> result  = new ArrayList<>(); //结果路径列表
        result.add(start);  //添加起点

        for(int l = 0 ; l<two_verties.size();l++){
            for(int i = 0 ; i<verties_num ; i++){
                if( edges_weight[start][i]<99999 && two_verties.contains(i) && !result.contains(i)) {
                    start = i;
                    result.add(i);
                    break;
                }
            }
        }
        result.add(end);

        return result;
    }

    /**
     * 求array数组的全排列，并把所有全排列结果存到violent_arr中。
     * @param begin array数组开始索引
     * @param end array数组结束索引
     * @param array 需要求阶乘的数组
     * @param violent_arr 存入阶乘结果的数组
     */
    public static void getAllOrder(int begin, int end,int[]array,int[][]violent_arr) {
        if (begin == end) {
            for(int i = 1 ; i<violent_arr[0].length-1 ; i++){
                violent_arr[count][i] = array[i-1];
            }
            count++;
        } else {
            for (int i = begin; i <= end; i++) {
                // 交换数据
                swap(begin, i,array);
                getAllOrder(begin + 1, end,array,violent_arr);
                swap(i, begin,array);
            }
        }
    }

    /**
     * 交换数组的两个数
     * @param from
     * @param to
     * @param array
     */
    public static void swap(int from, int to,int[] array) {
        // 这里应该加上各种防止无效交换的情况
        // 比如位置相同，或者2个位置的数据相同
        if (from == to) {
            return;
        }
        int tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }

}
