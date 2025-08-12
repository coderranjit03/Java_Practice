package DSA.ArrayList;

import java.util.ArrayList;

public class pairsum2 {
    //brute force approach 
    public static boolean pairSum(ArrayList<Integer> list, int target){
        
        boolean found = false;
        for(int i =0; i<list.size();i++){
            for(int j = i+1; j<list.size();j++){
                if(list.get(i) + list.get(j) == target){
                    System.out.println("Pair found: " + list.get(i) + ", " + list.get(j));
                    found= true;// Pair found
                }
            }
        }
        if (!found) {
            System.out.println("No pair found");
            return false; // No pair found
        }
        return true; // Pair found
    }

    //two pointer approach
    public static boolean pairSumTwoPointer(ArrayList<Integer> list, int target) {
        int bp = -1;
        int n = list.size();
        for(int i=0; i<n;i++){
            if(list.get(i)>list.get(i+1)){
                bp = i;
                break;
            }
        }
        int left = (bp + 1) % n;
        int right = bp;

        while (left != right) {
            if (list.get(left) + list.get(right) == target) {
                System.out.println("Pair found: " + list.get(left) + ", " + list.get(right));
                return true; // Pair found
            } else if (list.get(left) + list.get(right) < target) {
                left = (left + 1) % n;
            } else {
                right = (right - 1 + n) % n;
            }
        }

        System.out.println("No pair found");
        return false; // No pair found
    
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(15);
        list.add(6);
        list.add(8);
        list.add(9);
        list.add(10);

        int target = 16;
        pairSum(list, target); // Output: Pair found: 4, 2 or Pair found: 5, 1

        pairSumTwoPointer(list, target); // Output: Pair found: 4, 2 or Pair found: 5, 1
    }
}
