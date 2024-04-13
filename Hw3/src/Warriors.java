
import java.util.Arrays;
import java.util.Stack;

//import edu.princeton.cs.algs4.Stack;

class Warriors {
    int[] ans;
    public int[] warriors(int[] strength, int[] range) {
        // Given the attributes of each warrior and output the minimal and maximum
        // index of warrior can be attacked by each warrior.
        ans = new int[strength.length*2];
        for(int i = 0; i < strength.length; i++) ans[i*2+1] = strength.length - 1;
        Stack<Integer> Left_Stack = new Stack<Integer>();
        Stack<Integer> Right_Stack = new Stack<Integer>();

        // shoot right
        for(int i = 0; i < strength.length; i++){
            while(Left_Stack.size() != 0 && strength[i] >= strength[Left_Stack.peek()]){
                // peek():  can view the value of the top element but doesn't pop it
                ans[Left_Stack.pop()*2+1] = i - 1;
                //System.out.println(ans[Left_Stack.pop()*2+1]);
            }
            Left_Stack.push(i);
//            System.out.println(Left_Stack);
        }

        // shoot left
        for(int i = strength.length-1; i >= 0; i--){
            while(Right_Stack.size() != 0 && strength[i] >= strength[Right_Stack.peek()]){
                ans[Right_Stack.pop()*2] = i + 1;
            }
            Right_Stack.push(i);
//            System.out.println(Right_Stack);
        }
        for(int i = 0; i < strength.length; i++){

            // shoot left , out of range
            if(ans[2*i] < i - range[i]) ans[2*i] = i - range[i];
            // shoot right , out of range
            if(ans[2*i+1] > i + range[i]) ans[2*i+1] = i + range[i];
        }

        // two ends
        ans[0] = 0;
        ans[strength.length*2 - 1] = strength.length - 1;
        if(strength.length == 1)return new int[] {0, 0};
        else return ans;
    }

    public static void main(String[] args) {
        Warriors sol = new Warriors();
//        System.out.println(Arrays.toString(
//                sol.warriors(new int[] {11, 13, 11, 11, 7, 15, 8, 8, 8, 9, 9, 9, 17, 6, 8, 20, 21, 20, 18, 14, 6, 5, 1},
//                        new int[] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100})));
        // Output: [0, 0, 0, 4, 2, 2, 3, 4, 4, 4, 0, 11, 6, 6, 7, 7, 8, 8, 6, 9, 10, 10, 11, 11, 0, 14, 13, 13, 13, 14, 0, 15, 0, 22, 17, 22, 18, 22, 19, 22, 20, 22, 21, 22, 22, 22]
        System.out.println(Arrays.toString(sol.warriors(new int[] {11, 13, 11, 7, 15}, new int[] { 1,  8,  1, 7,  2})));
        // Output: [0, 0, 0, 3, 2, 3, 3, 3, 2, 4]

    }
}