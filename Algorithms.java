package stuMap;

import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

import java.util.*;

public class Algorithms {

    static int count = 0;

    /**
     * 跟据权值表，找出从开始位置到结束位置的最短路径
     * 且途中需要经过pass_places里面的地点，需要按顺序返回经过的各个顶点序号
     * @param edges_weight  权重表
     * @param start 开始顶点序号
     * @param end   结束结点序号
     * @param pass_verties   途径结点序号
     * @return 按顺序返回一个列表表示路径（包含start 和 end）
     *
     *     小明想从地点start去到地点end，但是他还要途经多个顶点，请帮小明找出一条最短路径吧。
     *     pass_places是小明途径的地点列表,start、end、pass_places中的地点不会重复。
     *     edges_weight[i][j] 表示地点i到地点j的距离，edges_weight[i][j] == edges_weight[j][i]，
     * 且edges_weight[i][i]==0,edges_weight[i][j] == MAX 表示两点不直接可达。
     *     结果需要返回小明需要的最短路径列表（返回的列表需要包含start和end）
     *
     */
    public static ArrayList<Integer> findShortestPath(int[][] edges_weight,
                                                      int start,
                                                      int end,
                                                      ArrayList<Integer> pass_verties ){

        //System.out.println("途径地点："+pass_verties.toString());

        int [][]d = new int[27][27];
        int [][][]p = new int[27][27][27];

        for(int v = 0 ; v<27; v++){
            for(int w = 0 ; w<27;w++){
                d[v][w] = edges_weight[v][w];
                for(int u = 0; u<27; u++){
                    if(d[v][w] < 99999){
                        p[v][w][v] = 1;
                        p[v][w][w] = 1;
                    }
                }
            }
        }

        for(int u = 0; u < 27 ; u++){
            for(int v = 0 ; v<27; v++){
                for(int w = 0 ; w <27 ; w++)
                    if(d[v][u]+d[u][w] < d[v][w]){
                        d[v][w] = d[v][u] + d[u][w];
                        for(int i = 0 ; i<27 ; i++){
                            p[v][w][i] = (p[v][u][i] == 1 || p[u][w][i] == 1)?1:0;
                        }
                    }
            }
        }

        int [][]two_places_dis = new int[pass_verties.size() + 2] [pass_verties.size()+2];
        ArrayList<Integer> all_verties = new ArrayList<>();

        all_verties.add(start);
        all_verties.addAll(pass_verties);
        all_verties.add(end);

        for(int i = 0 ; i<two_places_dis.length ; i++){
            for(int j = 0; j<two_places_dis.length ;j++){
                two_places_dis[i][j] = d[all_verties.get(i)][all_verties.get(j)];
//                for(int k = 0 ; k<27 ; k++){
//                    if( p[all_verties.get(i)][all_verties.get(j)][k] == 1 && (pass_verties.contains(k)) && k!=all_verties.get(i) && k!=all_verties.get(j) ){
//                        two_places_dis[i][j] = 0;
//                    }
//                }
            }
        }

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
//        for(int i =0 ; i<violent_arr.length ; i++){
//            System.out.println(Arrays.toString(violent_arr[i]));
//        }

        //寻找最短的那条
        int min_total_index = 0;
        int min_total_dis = 99999;
        int dis;
        for(int i = 0 ; i<violent_arr.length ; i++){
            dis = 0;
            //System.out.println();
            for(int j = 0  ; j<violent_arr[i].length - 1 ;j++){
                dis += d[violent_arr[i][j]][violent_arr[i][j+1]];
                //System.out.print(d[violent_arr[i][j]][violent_arr[i][j+1]] + ",");
            }
            //System.out.println();
            //System.out.println(dis);
            if(dis<min_total_dis){
                min_total_dis = dis;
                min_total_index = i;
            }
        }
        //System.out.println(Arrays.toString(violent_arr[min_total_index]));

        //获得了最短路径的顶点顺序，在violent_arr[min_total_index]中。



        //一直要最短
//        ArrayList<Integer> test_result= new ArrayList<>();
//        test_result.add(start);
//        int first = start;
//        for(int i = 0  ; i<all_verties.size() ;i++){
//            int min = 99999;
//            int min_index =0;
//            for(int j = 1; j<all_verties.size();j++){
//                if(two_places_dis[first][j] < min && two_places_dis[first][j] != 0 && !test_result.contains(j)){
//                    min_index = j;
//                    min = two_places_dis[first][j];
//                }
//            }
//            first = min_index;
//            test_result.add(first);
//        }
//
//        System.out.println("一直要最短："+test_result.toString());

        ArrayList<Integer> result  = new ArrayList<>();
        for(int i = 0 ; i<violent_arr[min_total_index].length-1 ; i++){
            int start_vertex = violent_arr[min_total_index][i];
            int end_vertex = violent_arr[min_total_index][i+1];
            //System.out.println(start_vertex + " -- >" + end_vertex);

            ArrayList<Integer> two_verties = new ArrayList<>();
            for(int j = 0 ; j<27; j++){
                if(p[start_vertex][end_vertex][j] == 1) {
                    two_verties.add(j);
                }
            }
            two_verties.remove((Object)start_vertex);
            two_verties.remove((Object)end_vertex);
            result.add(start_vertex);
            for(int l = 0 ; l<two_verties.size();l++){
                for(int m = 0 ; m<27 ; m++){
                    if( edges_weight[start_vertex][m]<99999 && two_verties.contains(m) && !result.contains(m)) {
                        start_vertex = m;
                        result.add(m);
                        break;
                    }
                }
            }

        }
        result.add(end);
        //System.out.println(result.toString());


        return result;

    }







    //返回点到点的最短距离包含地点的数组
    public static ArrayList<Integer> findShortestPath_two_verties(int[][] edges_weight, int start, int end){

        int [][]d = new int[27][27];
        int [][][]p = new int[27][27][27];

        for(int v = 0 ; v<27; v++){
            for(int w = 0 ; w<27;w++){
                d[v][w] = edges_weight[v][w];
                for(int u = 0; u<27; u++){
                    if(d[v][w] < 99999){
                        p[v][w][v] = 1;
                        p[v][w][w] = 1;
                    }
                }
            }
        }

        for(int u = 0; u < 27 ; u++){
            for(int v = 0 ; v<27; v++){
                for(int w = 0 ; w <27 ; w++)
                    if(d[v][u]+d[u][w] < d[v][w]){
                        d[v][w] = d[v][u] + d[u][w];
                        for(int i = 0 ; i<27 ; i++){
                            p[v][w][i] = (p[v][u][i] == 1 || p[u][w][i] == 1)?1:0;
                        }
                    }
            }
        }

        ArrayList<Integer> two_verties = new ArrayList<>();
        for(int i = 0 ; i<27; i++){
            if(p[start][end][i] == 1) {
                two_verties.add(i);
            }
        }
        //System.out.println(two_verties.toString());

        ArrayList<Integer> result  = new ArrayList<>();
        two_verties.remove((Object)start);
        two_verties.remove((Object)end);
        result.add(start);
        for(int l = 0 ; l<two_verties.size();l++){
            for(int i = 0 ; i<27 ; i++){
                if( edges_weight[start][i]<99999 && two_verties.contains(i) && !result.contains(i)) {
                    System.out.print(i+",");
                    start = i;
                    result.add(i);
                    break;
                }
            }
        }
        result.add(end);
        //System.out.println(result.toString());

        return result;
    }

    //数组全排列
//    void  permutation(int[][] violent_arr, int[] num, int index){
//        if(index >= num.length ){
//            violent_arr.
//        }
//        for(int i = index ; i<num.length ; i++){
//            temp
//        }
//    }

    public static void getAllOrder(int begin, int end,int[]array,int[][]violent_arr) {
        if (begin == end) {
            //violent_arr[count] = array;
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
    public static void check() {
        // 排列拿到了，可以进行你的判断了。
        //System.out.println(Arrays.toString(array));
    }

}
