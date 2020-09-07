package stuMap;

import java.util.*;

public class Algorithms2{

    //寻找下一个最小的数的位置. 若没有, 则返回-1
    static int findNextMinNum(ArrayList<Integer> dis, int k){
        int min = 0,fistime=1,j=-1;
        for(int i= 0;i<dis.size();i++){
            if(!(dis.get(i)<dis.get(k)||dis.get(i)==0||(dis.get(i).equals(dis.get(k)) &&i<=k))){//排除已经已经确定是最小路径的点
                if(fistime==1){
                    min=dis.get(i);
                    j=i;
                    fistime=0;
                } else if(min>dis.get(i)){
                    min=dis.get(i);
                    j=i;
                }
            }
        }
        return j;
    }
    static int findMinNum(int[] edges, int[] book){
        int min=0,fistime=1,j=-1;
        for(int i=0;i<edges.length;i++){
            if(book[i]==0&&edges[i]!=0){
                if(fistime==1){
                    min=edges[i];
                    j=i;
                    fistime=0;
                } else if(min>edges[i]){
                    min=edges[i];
                    j=i;
                }
            }
        }
        return j;
    }


    public static ArrayList<Integer> findShortestPath(int[][] edges_weight, int start, int end,ArrayList<Integer> pass_verties){
        if (pass_verties.isEmpty()) {
            return findOneTOAllShortestPath(edges_weight, start).get(end);
        }
        else{
            return findPassShortestPath(edges_weight, start, end, pass_verties);
        }
    }

    public static ArrayList<ArrayList<Integer>> findOneTOAllShortestPath(int[][] edges_weight,int start){
        int k=start;//最小的路径的位置
        ArrayList<Integer> dis= new ArrayList<Integer>();//储存start开始到各点的最小路径
        for(int i=0;i<edges_weight.length;i++) {
            dis.add(edges_weight[start][i]);
        }
        ArrayList<ArrayList<Integer>> Allpass = new ArrayList<ArrayList<Integer>>();//储存start到各点的最小路径要经过的点
        for(int i=0;i<dis.size();i++) {//初始化
            Allpass.add(new ArrayList<Integer>() {{
            }});
        }
        Allpass.get(start).add(start);//初始化
        //Dijkstra算法
        for(int i=0;i<dis.size();i++){
            int min= findNextMinNum(dis,k);//寻找下一个最小的路径
            if(min==-1)
                break;
            if(Allpass.get(min).size()==0){//如果是start点能直接到该点
                ArrayList<Integer> pass=(ArrayList<Integer>)Allpass.get(start).clone();
                pass.add(min);
                Allpass.set(min,pass);
            }
            k=min;//修改最小路径的位置
            for(int j=0;j<dis.size();j++){//min点到其他点的距离与dis中储存的距离进行比较, 若比较小, 则替换
                int temp=dis.get(min)+edges_weight[min][j];
                if(temp<dis.get(j)){//比较小
                    dis.set(j,temp);
                    ArrayList<Integer> pass=(ArrayList<Integer>)Allpass.get(min).clone();
                    pass.add(j);
                    Allpass.set(j,pass);
                }
            }
        }
        Allpass.add(dis);
        //返回的方式可以改一下也
        return Allpass;
    }

    public static ArrayList<Integer> findPassShortestPath(int[][] edges_weight,
                                                      int start,
                                                      int end,
                                                      ArrayList<Integer> pass_verties ){
        pass_verties.add(start);
        pass_verties.add(end);
        int[][] edges = new int[pass_verties.size()][pass_verties.size()];
        int[] book= new int[pass_verties.size()];
        ArrayList<Integer> pass_place= new ArrayList<Integer>();

        ArrayList<ArrayList<ArrayList<Integer>>> Allpass = new ArrayList<ArrayList<ArrayList<Integer>>>();
        for(int i=0;i<pass_verties.size();i++) {//初始化
            Allpass.add(new ArrayList<ArrayList<Integer>>() {{
            }});
            for(int j=0;j<pass_verties.size()+2;j++){
                Allpass.get(i).add(new ArrayList<Integer>() {{
                }});
            }
        }

        for(int i=0;i<pass_verties.size();i++){
            ArrayList<ArrayList<Integer>> pass=findOneTOAllShortestPath(edges_weight, pass_verties.get(i));
            for(int j=0;j<pass_verties.size();j++){
                edges[i][j]=pass.get(pass.size()-1).get(pass_verties.get(j));
                ArrayList<Integer> temp=(ArrayList<Integer>)pass.get(pass_verties.get(j)).clone();
                Allpass.get(i).set(j,temp);
            }
        }

        int place=pass_verties.size()-2;
        book[pass_verties.size()-1]=1;
        for(int i=1;i<pass_verties.size();i++){
            if(i==pass_verties.size()-1)
                book[pass_verties.size()-1]=0;
            int j=findMinNum(edges[place],book);
            book[place]=1;
            for(int k=0;k<Allpass.get(place).get(j).size();k++){
                pass_place.add(Allpass.get(place).get(j).get(k));
            }
            if(i!=pass_verties.size()-1)
                pass_place.remove(pass_place.size()-1);
            place=j;
        }

        return pass_place;
    }


}
