package stuMap;

//地点类
//方便起见，多个密集地点看作一个顶点
public class Place {
    private int order;          //地点序号
    private int to_vertex_order; //对应顶点序号
    private String name;        //地点名

    public Place(int order, int to_vetex_order, String name){
        this.name = name;
        this.order = order;
        this.to_vertex_order = to_vetex_order;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getTo_vertex_order() {
        return to_vertex_order;
    }

    public void setTo_vertex_order(int to_vertex_order) {
        this.to_vertex_order = to_vertex_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
