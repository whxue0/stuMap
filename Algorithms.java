package stuMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class Algorithms {



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
//未完
//        int [][]two_places_dis = new int[pass_verties.size() + 2] [pass_verties.size()+2];
//        System.out.println(pass_verties.size()+2);
//        for(int i = 0 ; i<pass_verties.size() +2 ; i++){
//            for(int j = 0; j<pass_verties.size() + 2 ;j++){
//                two_places_dis[pass_verties.get(i)][pass_verties.get(j)] = d[pass_verties.get(i)][pass_verties.get(j)];
//                System.out.println(Arrays.toString(p[i][j]));
//                for(int k = 0 ; k<27 ; k++){
//                    if( p[pass_verties.get(i)][pass_verties.get(j)][k] == 1 && (pass_verties.contains(k)) && k!=i && k!=j ){
//                        two_places_dis[i][j] = 0;
//                    }
//                }
//            }
//        }
//
//        for(int i = 0 ; i<two_places_dis.length ; i++){
//            System.out.println(Arrays.toString(two_places_dis[i]));
//        }






        ArrayList<Integer> two_verties = new ArrayList<>();
        for(int i = 0 ; i<27; i++){
            if(p[start][end][i] == 1) {
                two_verties.add(i);
            }
        }
        System.out.println(two_verties.toString());

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
        System.out.println(result.toString());

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
        System.out.println(two_verties.toString());

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
        System.out.println(result.toString());

        return result;
    }



}
