import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class BoardGame {
    int h;
    int w;
    int BoardSize;

    public char[] board_char;
    public int[] board_num;
    public WeightedQuickUnionUF weightedQuickUnionUF;
    public BoardGame(int h, int w){
        this.h = h;
        this.w = w;
        BoardSize = h*w;
        board_char = new char[BoardSize];
        board_num = new int[BoardSize];
        Arrays.fill(board_num, 4);
        weightedQuickUnionUF = new WeightedQuickUnionUF(BoardSize);
    } // create a board of size h*w

    public void putStone(int[] x, int[] y, char stoneType){
        for (int i = 0; i < x.length; ++i) {
            int position = x[i] * w + y[i];
            board_char[position] = stoneType;
            int up = (x[i] - 1) * w + y[i];
            int down = (x[i] + 1) * w + y[i];
            int left = x[i] * w + y[i] - 1;
            int right = x[i] * w + y[i] + 1;
            // Ex：0, 3 Union起來時 Parent指向會改變，會隸屬同一個roots，是改weightedQuickUnionUF裡面的parent等成員變量
            if (x[i] != 0) {
                int upRoot = weightedQuickUnionUF.find(up);
                int placeRoot = weightedQuickUnionUF.find(position);
                board_num[upRoot] -= 1;
                if (board_char[up] == stoneType && upRoot!=placeRoot) {//
                    // size相同時p是根，一定是新的連到周圍，一定是place接到up
                    weightedQuickUnionUF.union(upRoot, placeRoot);
                    int upRoot2 = weightedQuickUnionUF.find(up);
                    if (upRoot2 == upRoot){
                        board_num[upRoot] += board_num[placeRoot];
                    }
                    else {
                        board_num[placeRoot] += board_num[upRoot];
                    }
                }
            }
            if (x[i] != h - 1) {
                int downRoot = weightedQuickUnionUF.find(down);
                int placeRoot = weightedQuickUnionUF.find(position);
                board_num[downRoot] -= 1;
                if (board_char[down] == stoneType && downRoot!=placeRoot) {
                    // size相同時p是根，一定是新的連到周圍，一定是place接到down
                    weightedQuickUnionUF.union(downRoot, placeRoot);
                    int downRoot2 = weightedQuickUnionUF.find(down);
                    if (downRoot2 == downRoot){
                        board_num[downRoot] += board_num[placeRoot];
                    } else {
                        board_num[placeRoot] += board_num[downRoot];
                    }
                }
            }
            if (y[i] != 0) {
                int leftRoot = weightedQuickUnionUF.find(left);
                int placeRoot = weightedQuickUnionUF.find(position);
                board_num[leftRoot] -= 1;

                if (board_char[left] == stoneType && leftRoot!=placeRoot) {
                    // size相同時p是根，一定是新的連到周圍，一定是place接到left
                    weightedQuickUnionUF.union(leftRoot, placeRoot);
                    int leftRoot2 = weightedQuickUnionUF.find(left);
                    if (leftRoot2 == leftRoot){
                        board_num[leftRoot] += board_num[placeRoot];
                    } else {
                        board_num[placeRoot] += board_num[leftRoot];
                    }
                }
            }
            if (y[i] != w - 1) {
                int rightRoot = weightedQuickUnionUF.find(right);
                int placeRoot = weightedQuickUnionUF.find(position);
                board_num[rightRoot] -= 1;

                if (board_char[right] == stoneType && rightRoot!=placeRoot) {
                    // size相同時p是根，一定是新的連到周圍，一定是place接到right
                    weightedQuickUnionUF.union(rightRoot, placeRoot);
                    int rightRoot2 = weightedQuickUnionUF.find(right);
                    if (rightRoot2 == rightRoot){
                        board_num[rightRoot] += board_num[placeRoot];
                    } else {
                        board_num[placeRoot] += board_num[rightRoot];
                    }
                }
            }
        }
    }
    // put stones of the specified type on the board according to the coordinates

    public boolean surrounded(int x, int y){
        int place = x * w + y;
        int thisRoot = weightedQuickUnionUF.find(place);
        if (board_num[thisRoot] == 0){
            return true;
        }else {
            return false;
        }
    } // Answer if the stone and its connected stones are surrounded by another type of stones
    public char getStoneType(int x, int y){
        int position = (x+1)*(w+2) + (y+1);
        return board_char[position];
    } // Get the type of the stone at (x,y)
    public int countConnectedRegions(){
        int allCount = weightedQuickUnionUF.count();
        int nullCount = 0;
        for (int i = 0; i < board_char.length; ++i) {
            if (board_char[i] != 'O' && board_char[i] != 'X') {
                nullCount += 1;
            }
        }
        return allCount - nullCount;
    } // Get the number of connected regions in the board, including both types of the stones

    public void printBoard(){
        int targetTest = weightedQuickUnionUF.find(13);
        System.out.println("root");
        System.out.print(targetTest);
        System.out.println();
        System.out.println("Board");
        for (int i = 0; i < BoardSize; ++i){
            if (i % w == 0){
                System.out.println();
            }
            System.out.print(board_char[i]);
        }
        System.out.println();
    }

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
