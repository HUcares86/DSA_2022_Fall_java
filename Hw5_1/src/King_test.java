//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Stack;
//import java.util.*;
//
//class King implements Comparable<King>{
//    // optional, for reference only
//    public int Strength; int Range; int Index; int Left; int Right;
//    public King(int str,int rng, int i, int left, int right){
//        this.Strength=str;
//        this.Range=rng;
//        this.Index=i;
//        this.Left=left;
//        this.Right=right;
//    }
//    @Override
//    public int compareTo(King other)
//    {
//        //simplify
//        return other.Strength -this.Strength;
//    }
//}
//
//class Kings {
//    ArrayList<King> nodes = new ArrayList<King>();
//    ArrayList<Integer> attacked = new ArrayList<Integer>();
//    //    ArrayList<Integer> final_ans = new ArrayList<Integer>();
//    int[] ans;
//    int length;
//    public Kings(int[] strength, int[] range){
//        // Given the attributes of each warrior
//        this.length = strength.length;
//        this.ans = new int[strength.length*2];
//        for(int i = 0; i < strength.length; i++) ans[i*2+1] = strength.length - 1;
//        Stack<Integer> Left_Stack = new Stack<Integer>();
//        Stack<Integer> Right_Stack = new Stack<Integer>();
//
//        // shoot right
//        for(int i = 0; i < strength.length; i++){
//            while(Left_Stack.size() != 0 && strength[i] >= strength[Left_Stack.peek()]){
//                // peek():  can view the value of the top element but doesn't pop it
//                ans[Left_Stack.pop()*2+1] = i - 1;
//                //System.out.println(ans[Left_Stack.pop()*2+1]);
//            }
//            Left_Stack.push(i);
////            System.out.println(Left_Stack);
//        }
//        // shoot left
//        for(int i = strength.length-1; i >= 0; i--){
//            while(Right_Stack.size() != 0 && strength[i] >= strength[Right_Stack.peek()]){
//                ans[Right_Stack.pop()*2] = i + 1;
//            }
//            Right_Stack.push(i);
////            System.out.println(Right_Stack);
//        }
//        // two ends
//        ans[0] = 0;
//        ans[strength.length*2 - 1] = strength.length - 1;
//        for(int i = 0; i < strength.length; i++){
//
//            // shoot left , out of range
//            if(ans[2*i] < i - range[i]) ans[2*i] = i - range[i];
//            // shoot right , out of range
//            if(ans[2*i+1] > i + range[i]) ans[2*i+1] = i + range[i];
//
//            this.nodes.add(new King(strength[i], range[i], i, ans[2*i], ans[2*i+1]));
//        }
//
////        System.out.println(ans[4]);
////        for(int i=0; i<strength.length; i++){
////            this.nodes.add(new King(strength[i], range[i], i, ans[2*i], ans[2*i+1]));
////        }
//
//        //System.out.println(nodes.get(0).Strength);
//        Collections.sort(nodes);
////        for(King s :nodes)
////            System.out.println(s.Strength);
////        System.out.println("----------------");
////        System.out.println(nodes.get(0).Right);
//
//    }
//    public int[] topKKings(int k) {
//        for(int j=nodes.get(0).Left; j == nodes.get(0).Right;j++ ){
//            attacked.add(j);
//        }
////        final_ans.add(nodes.get(0).Index);
//
//        int K_count = 1;
//        int[] ans_array2 = new int[k];
//        ans_array2[0] = nodes.get(0).Index;
//        for(int i=1; i<length; i++){
////            System.out.println(attacked.contains(nodes.get(i).Index));
//            if(K_count < k){
//                if(!attacked.contains(nodes.get(i).Index)) {
//                    ans_array2[K_count] = nodes.get(i).Index;
//                    //                final_ans.add(nodes.get(i).Index);
//                    K_count++;
//                }
//                for(int j=nodes.get(i).Left; j == nodes.get(i).Right;j++ ){
//                    attacked.add(j);
////                    if(!attacked.contains(j)){
////                        attacked.add(j);
////                    }
//                }
//            }
//            else{
//                break;
//            }
//        }
//        return ans_array2;
////        System.out.println(attacked);
////        System.out.println(final_ans.size());
//
//
////        if(k >= final_ans.size()){
////            return final_ans.stream().mapToInt(i -> i).toArray();
////        }else{
////            int[] ans_array = new int[k];
////            for (int i = 0; i < k; i++)
////            {
////                ans_array[i] = final_ans.get(i);
////            }
////            return ans_array;
////        }
//        // complete the code by returning an int[]
//        // remember to return the array of indexes in the descending order of strength
//    }
//
//    public static void main(String[] args) {
//        Kings sol = new Kings(new int[] {15, 3, 26, 2, 5, 19, 12, 8}
//                , new int[] { 1, 6, 1, 3, 2, 0, 1, 5});
//        System.out.println(Arrays.toString(sol.topKKings(3)));
//        // In this case, the kings are [0, 2, 4, 5, 6] (without sorting, only by the order of ascending indices)
//        // Output: [2, 5, 0]
//    }
//}
