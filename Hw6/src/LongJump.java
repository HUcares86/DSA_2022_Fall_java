import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SegmentTree;
import java.util.ArrayList;
import java.util.Collections;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;

class LongJump {
    ArrayList<Integer> jump_list = new ArrayList<Integer>();

    public LongJump(int[] playerList){
        for (int j : playerList) jump_list.add(j);
    }

    public void addPlayer(int distance) {
        jump_list.add(distance);
    }
    public int winnerDistances(int from, int to) {
        int size = jump_list.size();
        int[] ans = new int[size];
        int from_index = 0;
        int to_index = size-1;
        boolean from_bool = true;
        boolean to_bool = true;
        for(int j=0;j<size;j++){
            int now = jump_list.get(j);
            ans[j] = now;
            if(now > from && from_bool){
                from_index = j;
                from_bool = false;
            }
            if (now > to && to_bool) {
                to_index = j-1;
                to_bool = false;
            }
        }
        int[] arr = jump_list.stream().mapToInt(i -> i).toArray();
        SegmentTree tree = new SegmentTree(ans);
        return tree.rsq(from_index, to_index);
    }
    public static void main(String[] args) {
//        test t = new test(args);
        LongJump solution = new LongJump(new int[]{2,5,6});
        System.out.println(solution.winnerDistances(3,10));


        solution.addPlayer(10);
        solution.addPlayer(8);

        System.out.println(solution.winnerDistances(3,10));


        System.out.println("-----------------------------");

        LongJump solution2 = new LongJump(new int[]{2,5,7,9,10,11,13});
        System.out.println(solution2.winnerDistances(9,13));
        solution2.addPlayer(4);
        System.out.println(solution2.winnerDistances(2,10));
        solution2.addPlayer(6);
        System.out.println(solution2.winnerDistances(2,11));
        solution2.addPlayer(20);
        System.out.println(solution2.winnerDistances(10,20));
    }

}
//

//
//class test{
//    public test(String[] args) {
//        LongJump g;
//        JSONParser jsonParser = new JSONParser();
//        try (FileReader reader = new FileReader(args[0])) {
//            JSONArray all = (JSONArray) jsonParser.parse(reader);
//            int count = 0;
//            for (Object CaseInList : all) {
//                count++;
//                JSONArray a = (JSONArray) CaseInList;
//                int testSize = 0;
//                int waSize = 0;
//                System.out.print("Case ");
//                System.out.println(count);
//                //Board Setup
//                JSONObject argsSetting = (JSONObject) a.get(0);
//                a.remove(0);
//
//                JSONArray argSettingArr = (JSONArray) argsSetting.get("args");
//
//                int[] arr=new int[argSettingArr.size()];
//                for(int k=0;k<argSettingArr.size();k++) {
//                    arr[k] = (int)(long) argSettingArr.get(k);
//                }
//                g = new LongJump(arr);
//
//                for (Object o : a) {
//                    JSONObject person = (JSONObject) o;
//
//                    String func = person.get("func").toString();
//                    JSONArray arg = (JSONArray) person.get("args");
//
//                    switch (func) {
//                        case "addPlayer" -> g.addPlayer(Integer.parseInt(arg.get(0).toString()));
//                        case "winnerDistances" -> {
//                            testSize++;
//                            Integer t_ans = (int)(long)person.get("answer");
//                            Integer r_ans = g.winnerDistances(Integer.parseInt(arg.get(0).toString()),
//                                    Integer.parseInt(arg.get(1).toString()));
//                            if (t_ans.equals(r_ans)) {
//                                System.out.println("winnerDistances : AC");
//                            } else {
//                                waSize++;
//                                System.out.println("winnerDistances : WA");
//                                System.out.println("Your answer : "+r_ans);
//                                System.out.println("True answer : "+t_ans);
//                            }
//                        }
//                    }
//
//                }
//                System.out.println("Score: " + (testSize - waSize) + " / " + testSize + " ");
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//}