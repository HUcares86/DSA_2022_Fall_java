import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.ClosestPair;

class Cluster {
    public List<double[]> cluster(List<int[]> points, int cluster_num) {
        //把點放入 Point2D array
        Point2D[] point;
        point = new Point2D[points.size()];
        for(int i = 0; i < points.size(); i++) {
            point[i] = new Point2D(points.get(i)[0], points.get(i)[1]);
        }

        HashMap<Point2D, ArrayList<Point2D>> cluster_Map = new HashMap<Point2D, ArrayList<Point2D>>();
        // 每個點都先是自己一個cluster
        for(Point2D i: point){
            cluster_Map.put(i, new ArrayList<Point2D>(){{add(i);}});
        }

        int n = point.length;
        while(n > cluster_num){
            //Computes the closest pair of points in the specified array of points.
            ClosestPair closest_Pair = new ClosestPair(point);

            //delete old nearest pair
            int a_index = 0;    // point a
            int b_index = 0;    // point b

            // first point as point a
            for(int i = 0; i < point.length; i++){
                if(point[i].equals(closest_Pair.either())){
                    a_index = i;
                    break;
                }
            }

            // remove point a from Point2D array
            Point2D[] copy = new Point2D[point.length - 1];
            for (int i = 0, j = 0; i < point.length; i++) {
                if (i != a_index) {
                    copy[j++] = point[i];
                }
            }
            point = copy;

            // second point as point b
            for(int i = 0; i < point.length; i++){
                if(point[i].equals(closest_Pair.other())){
                    b_index = i;
                    break;
                }
            }
            // remove point b from Point2D array
            Point2D[] copy2 = new Point2D[point.length - 1];
            for (int i = 0, j = 0; i < point.length; i++) {
                if (i != b_index) {
                    copy2[j++] = point[i];
                }
            }
            point = copy2;

            //calculate centroid of nearest pair
            double x_sum = 0;
            double y_sum = 0;
            for(Point2D i: cluster_Map.get(closest_Pair.either())){
                x_sum += i.x();
                y_sum += i.y();
            }
            for(Point2D i: cluster_Map.get(closest_Pair.other())){
                x_sum += i.x();
                y_sum += i.y();
            }
            // avg
            x_sum /= (cluster_Map.get(closest_Pair.either()).size()+cluster_Map.get(closest_Pair.other()).size());
            y_sum /= (cluster_Map.get(closest_Pair.either()).size()+cluster_Map.get(closest_Pair.other()).size());

            //add new cluster
            Point2D[] copy3 = new Point2D[point.length+1];
            System.arraycopy(point, 0, copy3, 0, point.length);
            // put center point into point to cluster with others
            copy3[copy3.length - 1] = new Point2D(x_sum, y_sum);
            point = copy3;


            //add point into cluster
            ArrayList<Point2D> newcluster = new ArrayList<Point2D>();

            for(Point2D i: cluster_Map.get(closest_Pair.either())){
                System.out.println(closest_Pair.either());
                System.out.println(cluster_Map.get(closest_Pair.either()));
                System.out.println(i);
                newcluster.add(i);
                cluster_Map.remove(i);
            }
            for(Point2D i: cluster_Map.get(closest_Pair.other())){
                newcluster.add(i);
                cluster_Map.remove(i);
            }

            cluster_Map.put(new Point2D(x_sum, y_sum), newcluster);
            n--;
        }

        Arrays.sort(point, Point2D.Y_ORDER);
        Arrays.sort(point, Point2D.X_ORDER);

        ArrayList<double[]> ans = new ArrayList<double[]>();
        for(Point2D i : point){
            double[] sub_ans = new double[2];
            sub_ans[0] = i.x();
            sub_ans[1] = i.y();
            ans.add(sub_ans);
        }

        return ans;
    }
//    public static void main(String[] args) {
//        System.out.println(new Cluster().cluster(new ArrayList<int[]>(){{
//            add(new int[]{0,1});
//            add(new int[]{0,2});
//            add(new int[]{3,1});
//            add(new int[]{3,2});
//        }}, 2));
//    }
}
