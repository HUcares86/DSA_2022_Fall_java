
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;
class Percolation {

    int N;
//    int BoardSize;
    int count;
    boolean region_finished = false;

    int ans_root;
    public boolean[] board_char;
    public WeightedQuickUnionUF weightedQuickUnionUF;
    public WeightedQuickUnionUF weightedQuickUnionUF2;
    public WeightedQuickUnionUF weightedQuickUnionUF3;
    public List<Point2D> values2;
    public Point2D[] point_array;

    public Percolation(int N){
        this.count = 0;
        this.N = N;

        this.board_char = new boolean[N*(N + 2)];
        this.point_array = new Point2D[N*N];

        Arrays.fill(board_char, false);  // all closed
        weightedQuickUnionUF = new WeightedQuickUnionUF(N*(N + 2));
        weightedQuickUnionUF2 = new WeightedQuickUnionUF(N*N + 2);
        weightedQuickUnionUF3 = new WeightedQuickUnionUF(N*N + 1);
    }// create N-by-N grid, with all sites blocked

    public void open(int i, int j){
        count++;
        int position = i*N + j;
        int left = i*N + j - 1 ;
        int right = i*N  + j + 1 ;
        int up = (i-1) * N + j;
        int down = (i+1) * N + j ;
        board_char[position] = true;
        // top
        if(i != 0){
            if (board_char[up]) {
                weightedQuickUnionUF.union(position, up);
                weightedQuickUnionUF2.union(position, up);
                weightedQuickUnionUF3.union(position, up);
            }
        }
        else{
//            weightedQuickUnionUF.union(position, ver_top);
            weightedQuickUnionUF.union(position,  j +N*N);
            weightedQuickUnionUF2.union(j, N*N );
            weightedQuickUnionUF3.union(j, N*N );
//            this.top_open = true;
        }
        // bottom
        if(i != N-1){
            if (board_char[down]) {
                weightedQuickUnionUF.union(position, down);
                weightedQuickUnionUF2.union(position, down);
                weightedQuickUnionUF3.union(position, down);
            }
        }
        else{
            //weightedQuickUnionUF.union(position,ver_bottom);
            weightedQuickUnionUF.union(position,  position+2*N);
            weightedQuickUnionUF2.union(position,  N*N +1);
//            this.bot_open = true;
        }
        // left
        if(j != 0 && board_char[left]){
            weightedQuickUnionUF.union(position, left);
            weightedQuickUnionUF2.union(position, left);
            weightedQuickUnionUF3.union(position, left);
        }
        // right
        if(j != N-1 && board_char[right]){
            weightedQuickUnionUF.union(position,right);
            weightedQuickUnionUF2.union(position,right);
            weightedQuickUnionUF3.union(position,right);
        }
        if(count>=N && !region_finished){//&& top_open && bot_open
            if(percolates()){
                this.ans_root = weightedQuickUnionUF.find(position);
                this.values2 = new ArrayList<Point2D>();
                for(int v=0;v<N*N;v++){
                    if(board_char[v] && weightedQuickUnionUF.find(v)==ans_root){//

                        int a = v/N;
                        int b = v%N;

                        Point2D pos = new Point2D(a,b);
                        values2.add(pos);
//                        point_array[v] = pos;
                    }
                }
                region_finished = true;
            }
        }
    }// open site (row i, column j) if it is not open already
    public boolean isOpen(int i, int j){
        return board_char[i * N + j];

    }// is site (row i, column j) open?
    public boolean isFull(int i, int j){
        int place = i*N + j;
        return weightedQuickUnionUF3.find(place) == weightedQuickUnionUF3.find(N*N);
//        boolean is_full = false;
//        int place = i*N + j;
//
//        if(weightedQuickUnionUF.find(place) == weightedQuickUnionUF.find(j+N*N)){
//            is_full = true;
//        }else{
//            for(int y=N*N; y<N*N+N;y++){
//                if(board_char[y-N*N]){
//                    if(weightedQuickUnionUF.find(place) == weightedQuickUnionUF.find(y)){
//                        is_full = true;
//                    }
//                }
//            }
//        }
//        return is_full;
    }// is site (row i, column j) full?
    public boolean percolates(){

//        if(weightedQuickUnionUF2.find(ver_top) == weightedQuickUnionUF2.find(ver_bottom)){
//            this.ans_root = -1;
//            for (int y = N * N; y < N * N + N; y++) {
//                if (board_char[y - N * N]) {
//                    for (int z = N * N + N; z < N * N + 2 * N; z++) {
//                        if (board_char[z - 2 * N]) {
//                            if (weightedQuickUnionUF.find(y) == weightedQuickUnionUF.find(z)) {
//                                ans_root = weightedQuickUnionUF.find(y);
//                            }
//                        }
//                    }
//                }
//            }
////        return (ans_root != -1 );
//        }
        return weightedQuickUnionUF2.find(N*N) == weightedQuickUnionUF2.find(N*N +1);
    }// does the system percolate?


    public Point2D[] PercolatedRegion() {
//        List<Point2D> values = new ArrayList<Point2D>();
//        for(Point2D data: point_array) {
//            if(data != null) {
//                values.add(data);
//            }
//        }
//        Point2D[] target = values.toArray(new Point2D[values.size()]);
        Point2D[] target = values2.toArray(new Point2D[values2.size()]);
        Quick.sort(target);

        return target;
    }// print the     public static void test(String[] args){
    Percolation g;
    JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(args[0])){
        JSONArray all = (JSONArray) jsonParser.parse(reader);
        int count = 0;
        for(Object CaseInList : all){
            count++;
            JSONArray a = (JSONArray) CaseInList;
            int testSize = 0; int waSize = 0;
            System.out.print("Case ");
            System.out.println(count);
            //Board Setup
            JSONObject argsSeting = (JSONObject) a.get(0);
            a.remove(0);

            JSONArray argSettingArr = (JSONArray) argsSeting.get("args");
            g = new Percolation(
                    Integer.parseInt(argSettingArr.get(0).toString()));

            for (Object o : a)
            {
                JSONObject person = (JSONObject) o;

                String func =  person.get("func").toString();
                JSONArray arg = (JSONArray) person.get("args");

                switch(func){
                    case "open":
                        g.open(Integer.parseInt(arg.get(0).toString()),
                                Integer.parseInt(arg.get(1).toString()));
                        break;
                    case "isOpen":
                        testSize++;
                        String true_isop = (Boolean)person.get("answer")?"1":"0";
                        String ans_isop = g.isOpen(Integer.parseInt(arg.get(0).toString()),
                                Integer.parseInt(arg.get(1).toString()))?"1":"0";
                        if(true_isop.equals(ans_isop)){
                            System.out.println("isOpen : AC");
                        }else{
                            waSize++;
                            System.out.println("isOpen : WA");
                        }
                        break;
                    case "isFull":
                        testSize++;
                        String true_isfu =  (Boolean)person.get("answer")?"1":"0";
                        String ans_isfu = g.isFull(Integer.parseInt(arg.get(0).toString()),
                                Integer.parseInt(arg.get(1).toString()))?"1":"0";
                        if(true_isfu.equals(ans_isfu)){
                            System.out.println("isFull : AC");
                        }else{
                            waSize++;
                            System.out.println("isFull : WA");
                        }
                        break;
                    case "percolates":
                        testSize++;
                        String true_per = (Boolean)person.get("answer")?"1":"0";
                        String ans_per = g.percolates()?"1":"0";
                        if(true_per.equals(ans_per)){
                            System.out.println("percolates : AC");
                        }else{
                            waSize++;
                            System.out.println("percolates : WA");
                        }
                        break;
                    case "PercolatedRegion":
                        testSize++;
                        String true_reg = person.get("args").toString();
                        String reg = "[";
                        Point2D[] pr = g.PercolatedRegion();
                        for (int i = 0; i < pr.length; i++) {
                            reg = reg + ((int)pr[i].x() + "," + (int)pr[i].y());
                            if(i != pr.length - 1){
                                reg =reg + ",";
                            }
                        }
                        reg = reg +"]";
                        if(true_reg.equals(reg)){
                            System.out.println("PercolatedRegion : AC");
                        }else{
                            waSize++;
                            System.out.println("PercolatedRegion : WA");
                        }
                        break;
                }

            }
            System.out.println("Score: " + (testSize-waSize) + " / " + testSize + " ");
        }
    }catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
}sites of the percolated region in order





    public static void main(String args[]){
        //test(args);
        Percolation s = new Percolation(3);

        s.open(1,1);
        System.out.println(s.isFull(1, 1));
        System.out.println(s.percolates());
        System.out.println();
        s.open(0,1);
        s.open(2,0);



        System.out.println(s.isFull(1, 1));
        System.out.println(s.isFull(0, 1));
        System.out.println(s.isFull(2, 0));


        System.out.println(s.percolates());
        System.out.println();

        s.open(2,1);



        System.out.println(s.isFull(1, 1));
        System.out.println(s.isFull(0, 1));
        System.out.println(s.isFull(2, 0));
        System.out.println(s.isFull(2, 1));

        System.out.println(s.percolates());
        Point2D[] pr = s.PercolatedRegion();
//        System.out.println(s.point_array[1]);
//        System.out.println(pr[1]);
        for (int i = 0; i < pr.length; i++) {
            if(pr[i]!=null){
                System.out.println("("+(int)pr[i].x() + "," + (int)pr[i].y()+")");
            }
        }

    }
}
