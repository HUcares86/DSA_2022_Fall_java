import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.*;

class Flood {
    public Flood() {};
    //return which village is the latest one flooded
    public int village(int villages, List<int[]> road) {

        double max_dist = 0;
        int longest_Village = 0;
        EdgeWeightedDigraph EWDG = new EdgeWeightedDigraph(villages);
        for (int[] i : road){
            EWDG.addEdge(new DirectedEdge(i[0],i[1],i[2]));
        }

        DijkstraSP DSP = new DijkstraSP(EWDG, 0);
        for (int i = 1; i < villages; i++) {
            if (DSP.distTo(i) > max_dist && DSP.hasPathTo(i)) {  //connection[i] &&
                longest_Village = i;
                max_dist = DSP.distTo(i);
            }
        }

//        BellmanFordSP BSP = new BellmanFordSP(EWDG, 0);
//        for (int i = 1; i < villages; i++) {
//            if (BSP.hasPathTo(i) && BSP.distTo(i) > maxDist) {
//                farestVillage = i;
//                maxDist = BSP.distTo(i);
//            }
//        }

        return longest_Village;
    }
//    public static void main(String[] args) {
//        Flood solution = new Flood();
//        System.out.println(solution.village(4, new ArrayList<int[]>() {{
//            add(new int[]{0, 1, 3});
//            add(new int[]{0, 2, 6});
//            add(new int[]{1, 2, 2});
//            add(new int[]{2, 1, 3});
//            add(new int[]{2, 3, 3});
//            add(new int[]{3, 1, 1});
//        }}));
//        System.out.println(solution.village(6, new ArrayList<int[]>() {{
//            add(new int[]{0, 1, 5});
//            add(new int[]{0, 4, 3});
//            add(new int[]{1, 2, 1});
//            add(new int[]{1, 3, 3});
//            add(new int[]{1, 5, 2});
//            add(new int[]{2, 3, 4});
//            add(new int[]{3, 2, 1});
//            add(new int[]{4, 0, 2});
//            add(new int[]{4, 1, 4});
//            add(new int[]{5, 0, 3});
//        }}));
//    }
}