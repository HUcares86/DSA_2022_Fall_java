
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
class BoardGame {
    int h;
    int w;
    int BoardSize;

    public char[] board_char;
    public int[] board_num;
    public WeightedQuickUnionUF weightedQuickUnionUF;
    public BoardGame(int h, int w){
        this.h = h;     // height
        this.w = w;     // width
        BoardSize = h*w;
        board_char = new char[BoardSize];   // put 'X' and 'O'
        board_num = new int[BoardSize];     // number of stones connected
        Arrays.fill(board_num, 4);  // 4 sides
        weightedQuickUnionUF = new WeightedQuickUnionUF(BoardSize);
    } // create a board of size h*w

    public void putStone(int[] x, int[] y, char stoneType){
        for (int i = 0; i < x.length; i++) {
            // left to right is y
            // top to down is x
            int position = x[i]* w  + y[i];
            board_char[position] = stoneType;
            int left = y[i]- 1  + x[i]* w ;
            int right = y[i]+ 1  + x[i]* w ;
            int up = (x[i] - 1) * w + y[i];
            int down = (x[i]+ 1) * w + y[i] ;


            // not on boarder , boarder are never surrounded
            // left column
            if (y[i] != 0) {
                int left_root = weightedQuickUnionUF.find(left);
                int position_root = weightedQuickUnionUF.find(position);
                board_num[left_root] -= 1;
                if (board_char[left] == stoneType && left_root!=position_root) {
                    weightedQuickUnionUF.union(left_root, position_root);
                    int left_root_2 = weightedQuickUnionUF.find(left);
                    if (left_root_2 == left_root){
                        board_num[left_root] += board_num[position_root];
                    }
                    else {
                        board_num[position_root] += board_num[left_root];
                    }
                }
            }
            // right column
            if (y[i] != w - 1) {
                int right_root = weightedQuickUnionUF.find(right);
                int position_root = weightedQuickUnionUF.find(position);
                board_num[right_root] -= 1;
                if (board_char[right] == stoneType && right_root!=position_root) {
                    weightedQuickUnionUF.union(right_root, position_root);
                    int right_root_2 = weightedQuickUnionUF.find(right);
                    if (right_root_2 == right_root){
                        board_num[right_root] += board_num[position_root];
                    } else {
                        board_num[position_root] += board_num[right_root];
                    }
                }
            }
            // top row
            if (x[i] != 0) {
                int up_root = weightedQuickUnionUF.find(up);
                int position_root = weightedQuickUnionUF.find(position);
                board_num[up_root] -= 1;
                if (board_char[up] == stoneType && up_root!=position_root) {
                    //upRoot=placeRoot : already connected
                    // union
                    weightedQuickUnionUF.union(up_root, position_root);
                    // check after union
                    int up_root_2 = weightedQuickUnionUF.find(up);
                    if (up_root_2 == up_root){
                        // up ---> place
                        board_num[up_root] += board_num[position_root];
                    }
                    else {
                        //  place ---> up
                        board_num[position_root] += board_num[up_root];
                    }
                }
            }
            // bottem row
            if (x[i] != h - 1) {
                int down_root = weightedQuickUnionUF.find(down);
                int position_root = weightedQuickUnionUF.find(position);
                board_num[down_root] -= 1;
                if (board_char[down] == stoneType && down_root!=position_root) {
                    weightedQuickUnionUF.union(down_root, position_root);
                    int down_root_2 = weightedQuickUnionUF.find(down);
                    if (down_root_2 == down_root){
                        board_num[down_root] += board_num[position_root];
                    }
                    else {
                        board_num[position_root] += board_num[down_root];
                    }
                }
            }
        }
    }
    // put stones of the specified type on the board according to the coordinates
    public boolean surrounded(int x, int y){
        int place = y  + x * w;
        int thisRoot = weightedQuickUnionUF.find(place);
        return board_num[thisRoot] == 0;
    } // Answer if the stone and its connected stones are surrounded by another type of stones

    public int countConnectedRegions(){
        int count = 0;
        for (char a : board_char) {
            if (a != 'O' && a != 'X') {
                count += 1;
            }
        }
        return weightedQuickUnionUF.count() - count;
    } // Get the number of connected regions in the board, including both types of the stones

    public static void test(String[] args){
        BoardGame g;
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
                g = new BoardGame(
                        Integer.parseInt(argSettingArr.get(0).toString())
                        ,Integer.parseInt(argSettingArr.get(1).toString()));

                for (Object o : a)
                {
                    JSONObject person = (JSONObject) o;

                    String func =  person.get("func").toString();
                    JSONArray arg = (JSONArray) person.get("args");

                    switch(func){
                        case "putStone":
                            int xArray[] = JSONArraytoIntArray((JSONArray) arg.get(0));
                            int yArray[] = JSONArraytoIntArray((JSONArray) arg.get(1));
                            String stonetype =  (String) arg.get(2);

                            g.putStone(xArray,yArray,stonetype.charAt(0));
                            break;
                        case "surrounded":
                            Boolean answer = (Boolean) person.get("answer");
                            testSize++;
                            System.out.print(testSize + ": " + func + " / ");
                            Boolean ans = g.surrounded(
                                    Integer.parseInt(arg.get(0).toString()),
                                    Integer.parseInt(arg.get(1).toString())
                            );
                            if(ans==answer){
                                System.out.println("AC");
                            }else{
                                waSize++;
                                System.out.println("WA");
                            }
                            break;
                        case "countConnectedRegions":
                            testSize++;
                            int ans2 = Integer.parseInt(arg.get(0).toString());
                            int ansCR = g.countConnectedRegions();
                            System.out.print(testSize + ": " + func + " / ");
                            if(ans2==ansCR){
                                System.out.println("AC");
                            }else{
                                waSize++;
                                System.out.println("WA");
                            }
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
    }

    public static int[] JSONArraytoIntArray(JSONArray x){
        int sizeLim = x.size();
        int MyInt[] = new int[sizeLim];
        for(int i=0;i<sizeLim;i++){
            MyInt[i]= Integer.parseInt(x.get(i).toString());
        }
        return MyInt;
    }

    public static void main(String[] args) {//mian
        test(args);
    }

}
