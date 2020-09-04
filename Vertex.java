package stuMap;

import java.util.LinkedList;

//顶点类
public class Vertex {
    private int order;  //顶点序号
    private LinkedList<Integer> related_vetexs; //与顶点相连的其他顶点
    private int x;  //在图中位置的x坐标
    private int y;  //在图中位置的y坐标

    public Vertex(int order, LinkedList<Integer> related_vetexs, int x, int y) {
        this.order = order;
        this.related_vetexs = related_vetexs;
        this.x = x;
        this.y = y;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public LinkedList<Integer> getRelated_vetexs() {
        return related_vetexs;
    }

    public void setRelated_vetexs(LinkedList<Integer> related_vetexs) {
        this.related_vetexs = related_vetexs;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
