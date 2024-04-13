public class backup {
}


//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
////class warrior{
////    int Strength;
////    int Range;
////    int Index;
////    myWarrior(int str,int rng, int i){
////        Strength=str;
////        Range=rng;
////        Index=i;
////    }
////}
//
//class LinkedStack{
//    public Node first = null;
//
//    private class Node{
//        int strength;
//        Node next;
//    }
//
//    public boolean isEmpty(){
//        return first == null;
//    }
//
//    public void push(int strength){
//        Node oldfirst = first;
//        first = new Node();
//        first.strength = strength;
//        first.next = oldfirst;
//    }
//
//    public int pop(){
//        int strength = first.strength;
//        first = first.next;
//        return strength;
//    }
//}
//
//class Warriors {
//    int left_count;
//    int right_count;
//    int current_warrior_str;
//    int current_warrior_rng;
////    int current_warrior_left_count;
////    int current_warrior_right_count;
////    public List<Integer> ans = new ArrayList<Integer>();
//    public List<Integer> right_stack_1 = new ArrayList<Integer>();
//    public List<Integer> left_stack_1 = new ArrayList<Integer>();
//    int[] final_ans;
//
//    public int[] warriors(int[] strength, int[] range) {
//        // Given the attributes of each warrior and output the minimal and maximum
//        // index of warrior can be attacked by each warrior.
//        this.final_ans = new int[2*strength.length];
//        for (int k : strength) {
//            right_stack_1.add(k);
//        }
//        for (int i=0; i<strength.length ;i++) {
//
////            current_warrior_left_count = 0;
////            current_warrior_right_count = 0;
//            left_count = 0;
//            right_count= 0;
//            current_warrior_str = strength[i];
//            current_warrior_rng = range[i];
//            right_stack_1.remove(0);
//
//            // left side
//            if(left_stack_1.size()==0){
////                ans.add(i-left_count);
//                final_ans[2*i] = i-left_count;
//            }
//            else{
//                for(int j=left_stack_1.size()-1;j>=0;--j){
//                    if(left_stack_1.get(j) < current_warrior_str && left_count<current_warrior_rng){//current_warrior_
//                        left_count++;
//                        //current_warrior_left_count ++;
//                    }
//                    else{
//                        break;
//                    }
//                }
////                ans.add(i-left_count);
//                final_ans[2*i] = i-left_count;
//            }
//            // right side
//            if(right_stack_1.size()==0){
////                ans.add(i+right_count);
//                final_ans[2*i+1] = i+right_count;
//            }
//            else{
//                for (Integer integer : right_stack_1) {
//                    if (integer < current_warrior_str && right_count < current_warrior_rng) {//current_warrior_
//                        right_count++;
//                        //current_warrior_right_count ++;
//                    } else {
//                        break;
//                    }
//                }
////                ans.add(i+right_count);
//                final_ans[2*i+1] = i+right_count;
//            }
//            left_stack_1.add(i,current_warrior_str);
////            System.out.println("current_warrior_left_count: "+current_warrior_left_count);
////            System.out.println("current_warrior_right_count: "+current_warrior_right_count);
//        }
////        int[] arr = ans.stream().mapToInt(i -> i).toArray();
////        return arr;
//        return final_ans;
//        // complete the code by returning an int[]
//    }
//
////    public static void main(String[] args) {
////        Warriors sol = new Warriors();
//////        System.out.println(Arrays.toString(sol.warriors(new int[] {11, 13, 11, 7, 15}, new int[] { 1,  8,  1, 7,  2})));
//////        // Output: [0, 0, 0, 3, 2, 3, 3, 3, 2, 4]
//////        System.out.println(Arrays.toString(sol.warriors(new int[] {100, 10, 100}, new int[] { 5,5,5})));
//////        // Output: [0, 1,1,1,1,2]
//////        System.out.println(Arrays.toString(sol.warriors(new int[] {100, 10, 100}, new int[] { 5,5,5})));
//////        // Output: [0, 1,1,1,1,2]
//////        System.out.println(Arrays.toString(sol.warriors(new int[] {10, 2, 10, 2,10, 2,10}, new int[] {10, 10, 10, 10,10, 10,10})));
//////        // Output: [0, 1,1,1,1,3,3,3,3,5,5,5,5,6]
//////        System.out.println(Arrays.toString(sol.warriors(new int[] {5,9,1,7,3,13,11}, new int[] {5,1,4,2,7,6,3})));
//////        // Output: [0,0,0,2,2,2,2,4,4,4,6,6]
////        System.out.println(Arrays.toString(sol.warriors(new int[] {1,11,19,5,9,3,16,2,20}, new int[] {3,5,6,2,4,9,1,3,2})));
//////        // Output: [0,0,0,1,0,7,3,3,3,5,5,5,5,7,7,7,6,8]
////    }
//}


