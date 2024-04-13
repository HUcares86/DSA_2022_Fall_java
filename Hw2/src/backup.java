//import java.awt.geom.Point2D;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Arrays;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//class p {
//
//    int N;
//    int BoardSize;
//    int ver_top;
//    int ver_bottom;
//    public char[] board_char;
//    public WeightedQuickUnionUF weightedQuickUnionUF;
//
//    private Node mainroot = null;
//
//    public Node[] Node_array;
//
//    private static class Node {
//        private Point2D site;
//        private Node rightNode;
//        private Node leftNode;
//        private Node downNode;
//        private Node upNode;
//        private boolean status;
//        private boolean visited;
//
//        public Node(){
//            this.status = false;
//            this.visited = false;
//        }
//
//        public void set_site(int i, int j){
//            site = new java.awt.geom.Point2D.Double(i,j);
//        }
//
//        public void set_rightNode(Node rightNode){
//            this.rightNode = rightNode;
//        }
//
//        public void set_downNode(Node downNode){
//            this.downNode = downNode;
//        }
//
//        public void set_upNode(Node upNode){
//            this.upNode = upNode;
//        }
//
//        public void set_leftNode(Node leftNode){
//            this.leftNode = leftNode;
//        }
//
//        public Node get_rightNode(){
//            return this.rightNode;
//        }
//
//        public Node get_downNode(){
//            return this.downNode;
//        }
//
//        public Node get_leftNode(){
//            return this.leftNode;
//        }
//
//        public  Node get_upNode(){
//            return this.upNode;
//        }
//
//        public Point2D get_site(){
//            return this.site;
//        }
//
//        public boolean get_status(){
//            return this.status;
//        }
//
//        public void open(){
//            this.status = true;
//        }
//
//        public boolean visited(){
//            return this.visited;
//        }
//
//        public void set_visite(boolean state){
//            this.visited = state;
//        }
//    }
//    public Percolation(int N){
//        this.N = N;
//        this.ver_top = N*N;
//        this.ver_bottom = N*N +1;
//        this.BoardSize = N*N + 2;
//        this.board_char = new char[BoardSize];   // put 'C' and 'O'
//        Node Node_array[] = new Node[BoardSize];
//
////        Arrays.fill(board_char, 'C');  // all closed
////        board_char[N*N] = 'O';
////        board_char[N*N + 1] = 'O';
//        Node_array[N*N].status = true;
//        System.out.println(Node_array[N*N].status);
//
//
////        this.Node_array[N*N + 1].open_close = 'O';
//
//        weightedQuickUnionUF = new WeightedQuickUnionUF(BoardSize);
//
////        Node first = new Node();
////        Node last = new Node();
////        first.next = last;
////        first.site = new Point2D(0,0);
////        last.site= new Point2D(1,1);
//
//    }// create N-by-N grid, with all sites blocked
//    public void open(int i, int j){
//        int position = i*N + j;
//        int left = i*N + j - 1 ;
//        int right = i*N  + j + 1 ;
//        int up = (i-1) * N + j;
//        int down = (i+1) * N + j ;
////        Node new_node = new Node();
//
//        Node_array[position].status = true;
//        if(i != 0){
//            if (Node_array[up].get_status()) {
//                weightedQuickUnionUF.union(position, up);
//                Node_array[position].set_upNode(Node_array[up]);
//                Node_array[up].set_downNode(Node_array[position]);
//            }
//        }
//        else{
//            weightedQuickUnionUF.union(position, ver_top);
//
//        }
//        // bottom
//        if(i != N-1){
//            if (Node_array[down].get_status()) {
//                weightedQuickUnionUF.union(position, down);
//                Node_array[position].set_downNode(Node_array[down]);
//                Node_array[down].set_upNode(Node_array[position]);
//            }
//        }
//        else{
//            weightedQuickUnionUF.union(position,ver_bottom);
//
//        }
//        // left
//        if(j != 0 && board_char[left] == 'O'){
//            weightedQuickUnionUF.union(position, left);
//            Node_array[position].set_leftNode(Node_array[left]);
//            Node_array[left].set_rightNode(Node_array[position]);
//        }
//        // right
//        if(j != N-1 && board_char[right] == 'O'){
//            weightedQuickUnionUF.union(position,right);
//            Node_array[position].set_rightNode(Node_array[right]);
//            Node_array[right].set_leftNode(Node_array[position]);
//        }
//
////        board_char[position] = 'O';
////        // top
////        if(i != 0){
////            if (board_char[up] == 'O') {
////                weightedQuickUnionUF.union(position, up);
////            }
////        }
////        else{
////            weightedQuickUnionUF.union(position, ver_top);
////        }
////        // bottom
////        if(i != N-1){
////            if (board_char[down] == 'O') {
////                weightedQuickUnionUF.union(position, down);
////            }
////        }
////        else{
////            weightedQuickUnionUF.union(position,ver_bottom);
////        }
////        // left
////        if(j != 0 && board_char[left] == 'O'){
////                weightedQuickUnionUF.union(position, left);
////        }
////        // right
////        if(j != N-1 && board_char[right] == 'O'){
////                weightedQuickUnionUF.union(position,right);
////        }
//
//    }// open site (row i, column j) if it is not open already
//    public boolean isOpen(int i, int j){
//        return Node_array[i * N + j].get_status();
////        return board_char[i * N + j] == 'O';
//
//    }// is site (row i, column j) open?
//    public boolean isFull(int i, int j){
//        int place = i*N + j;
//        return weightedQuickUnionUF.find(place) == weightedQuickUnionUF.find(ver_top);
//    }// is site (row i, column j) full?
//    public boolean percolates(){
//        return weightedQuickUnionUF.find(ver_top) == weightedQuickUnionUF.find(ver_bottom);
//    }// does the system percolate?
////    public int PercolatedRegion() {
////        int[] s;
////        return s;
////    }// print the sites of the percolated region in order
//
//
////    public static void test(String[] args){
////        Percolation g;
////        JSONParser jsonParser = new JSONParser();
////        try (FileReader reader = new FileReader(args[0])){
////            JSONArray all = (JSONArray) jsonParser.parse(reader);
////            int count = 0;
////            for(Object CaseInList : all){
////                count++;
////                JSONArray a = (JSONArray) CaseInList;
////                int testSize = 0; int waSize = 0;
////                System.out.print("Case ");
////                System.out.println(count);
////                //Board Setup
////                JSONObject argsSeting = (JSONObject) a.get(0);
////                a.remove(0);
////
////                JSONArray argSettingArr = (JSONArray) argsSeting.get("args");
////                g = new Percolation(
////                        Integer.parseInt(argSettingArr.get(0).toString()));
////
////                for (Object o : a)
////                {
////                    JSONObject person = (JSONObject) o;
////
////                    String func =  person.get("func").toString();
////                    JSONArray arg = (JSONArray) person.get("args");
////
////                    switch(func){
////                        case "open":
////                            g.open(Integer.parseInt(arg.get(0).toString()),
////                                    Integer.parseInt(arg.get(1).toString()));
////                            break;
////                        case "isOpen":
////                            testSize++;
////                            String true_isop = (Boolean)person.get("answer")?"1":"0";
////                            String ans_isop = g.isOpen(Integer.parseInt(arg.get(0).toString()),
////                                    Integer.parseInt(arg.get(1).toString()))?"1":"0";
////                            if(true_isop.equals(ans_isop)){
////                                System.out.println("isOpen : AC");
////                            }else{
////                                waSize++;
////                                System.out.println("isOpen : WA");
////                            }
////                            break;
////                        case "isFull":
////                            testSize++;
////                            String true_isfu =  (Boolean)person.get("answer")?"1":"0";
////                            String ans_isfu = g.isFull(Integer.parseInt(arg.get(0).toString()),
////                                    Integer.parseInt(arg.get(1).toString()))?"1":"0";
////                            if(true_isfu.equals(ans_isfu)){
////                                System.out.println("isFull : AC");
////                            }else{
////                                waSize++;
////                                System.out.println("isFull : WA");
////                            }
////                            break;
////                        case "percolates":
////                            testSize++;
////                            String true_per = (Boolean)person.get("answer")?"1":"0";
////                            String ans_per = g.percolates()?"1":"0";
////                            if(true_per.equals(ans_per)){
////                                System.out.println("percolates : AC");
////                            }else{
////                                waSize++;
////                                System.out.println("percolates : WA");
////                            }
////                            break;
////                        case "PercolatedRegion":
////                            testSize++;
////                            String true_reg = person.get("args").toString();
////                            String reg = "[";
////                            Point2D[] pr = g.PercolatedRegion();
////                            for (int i = 0; i < pr.length; i++) {
////                                reg = reg + ((int)pr[i].x() + "," + (int)pr[i].y());
////                                if(i != pr.length - 1){
////                                    reg =reg + ",";
////                                }
////                            }
////                            reg = reg +"]";
////                            if(true_reg.equals(reg)){
////                                System.out.println("PercolatedRegion : AC");
////                            }else{
////                                waSize++;
////                                System.out.println("PercolatedRegion : WA");
////                            }
////                            break;
////                    }
////
////                }
////                System.out.println("Score: " + (testSize-waSize) + " / " + testSize + " ");
////            }
////        }catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
////    }
//
//
//    public static void main(String args[]){
//        //test(args);
//        Percolation s = new Percolation(3);
//
//        s.open(1,1);
//        System.out.println(s.weightedQuickUnionUF.find(1));
//        System.out.println(s.weightedQuickUnionUF.find(4));
//        System.out.println(s.weightedQuickUnionUF.find(6));
//        System.out.println(s.weightedQuickUnionUF.find(7));
//        System.out.println(s.isFull(1, 1));
//        System.out.println(s.percolates());
//        System.out.println();
//        s.open(0,1);
//
//        System.out.println(s.weightedQuickUnionUF.find(1));
//        System.out.println(s.weightedQuickUnionUF.find(4));
//        System.out.println(s.weightedQuickUnionUF.find(6));
//        System.out.println(s.weightedQuickUnionUF.find(7));
//
//        s.open(2,0);
//
//        System.out.println(s.weightedQuickUnionUF.find(1));
//        System.out.println(s.weightedQuickUnionUF.find(4));
//        System.out.println(s.weightedQuickUnionUF.find(6));
//        System.out.println(s.weightedQuickUnionUF.find(7));
//
//        System.out.println(s.isFull(1, 1));
//        System.out.println(s.isFull(0, 1));
//        System.out.println(s.isFull(2, 0));
//
//
//        System.out.println(s.percolates());
//        System.out.println();
//
//        s.open(2,1);
//
//        System.out.println(s.weightedQuickUnionUF.find(1));
//        System.out.println(s.weightedQuickUnionUF.find(4));
//        System.out.println(s.weightedQuickUnionUF.find(6));
//        System.out.println(s.weightedQuickUnionUF.find(7));
//
//        System.out.println(s.isFull(1, 1));
//        System.out.println(s.isFull(0, 1));
//        System.out.println(s.isFull(2, 0));
//        System.out.println(s.isFull(2, 1));
//
//        System.out.println(s.percolates());
//        System.out.println(s.weightedQuickUnionUF.find(3*3+1));
//    }
