import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.PrimMST;
import java.util.List;


class Budget {
    public Budget() {
    }
    public int plan(int island, List<int[]> bridge) {
        int Sum = 0;
        // 新增 EdgeWeightedGraph 把island跟bridge放入
        EdgeWeightedGraph graph = new EdgeWeightedGraph(island);
        for (int[] b : bridge){
            graph.addEdge(new Edge(b[0],b[1],b[2]));
        }
        // 把 graph 放入 mst 求出 結果
        PrimMST mst = new PrimMST(graph);

        // 把 結果 SUM起來就是答案
        for (Edge i : mst.edges()){
            Sum += i.weight();
        }
        return Sum;
    }
}
