import edu.princeton.cs.algs4.MergeBU;
import java.util.*;
import static java.lang.Math.min;


class Kings {
    public int Kings_size;
    public King[] Kings_array;
    static class King implements Comparable<King>{
        private final int index,str;
        public King(int index,int str){
            this.index = index;
            this.str = str;
        }
        public int compareTo(King that){
            if (this.str > that.str) return -1;
            else if (this.str < that.str) return 1;
            else return Integer.compare(this.index, that.index);
        }
    }
    public Kings(int[] strength, int[] range) {
        int N = strength.length;
        boolean[] hitByRight = new boolean[N];
        boolean[] hitByLeft = new boolean[N];
        for (int i = 0; i < N; i++){
            hitByRight[i] = false;
            hitByLeft[i] = false;
        }
        Stack<Integer> stack_left = new Stack<Integer>();
        for (int i = N - 1; i >= 0; i--){
            while (true){
                if (!stack_left.isEmpty() && strength[i] > strength[stack_left.peek()] && range[i] >= stack_left.peek() - i){
                    hitByLeft[stack_left.pop()] = true;
                }
                else {
                    stack_left.push(i);
                    break;
                }
            }
        }
        Stack<Integer> stack_right = new Stack<Integer>();
        for (int i = 0; i < N; i++){
            while (true){
                if (!stack_right.isEmpty() && strength[i] > strength[stack_right.peek()] && range[i] >= i - stack_right.peek()){
                    hitByRight[stack_right.pop()] = true;
                }
                else {
                    stack_right.push(i);
                    break;
                }
            }
        }

        Stack<King> STACK_King = new Stack<>();
        for (int i = 0; i < N; i++){
            if (!hitByLeft[i] && !hitByRight[i]){
                STACK_King.push(new King(i, strength[i]));
            }
        }
        Kings_size = STACK_King.size();
        Kings_array =new King[Kings_size];
        for (int i = 0; i < Kings_size; i++) {
            Kings_array[i] = STACK_King.pop();
        }
        MergeBU.sort(Kings_array);

//        System.out.println(Arrays.toString(hitByLeft));
//        System.out.println(Arrays.toString(hitByRight));
    }
    public int[] topKKings(int k) {
        int[] ans = new int[min(k, Kings_size)];
        for (int i = 0; i < min(k, Kings_size); i++){
            ans[i] = Kings_array[i].index;
        }
        return ans;
    }

//        public static void main(String[] args) {
//        Kings sol = new Kings(new int[] {15, 3, 26, 2, 5, 19, 12, 8}
//                , new int[] { 1, 6, 1, 3, 2, 0, 1, 5});
//        System.out.println(Arrays.toString(sol.topKKings(3)));
//        // In this case, the kings are [0, 2, 4, 5, 6] (without sorting, only by the order of ascending indices)
//        // Output: [2, 5, 0]
//    }
}