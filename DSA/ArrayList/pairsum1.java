package DSA.ArrayList;

import java.util.ArrayList;

//for given an array of integers, find if there exists a pair of elements that sum up to a target value.

public class pairsum1 {

    //Brute Force Approach => Time Complexity: O(n^2) Space Complexity: O(1)
    //This method checks all pairs of elements in the list to find a pair that sums to the target value.
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

    //two pointer approach => Time Complexity: O(n) Space Complexity: O(1)
    //This method uses two pointers to find a pair that sums to the target value.
    public static boolean pairSumTwoPointer(ArrayList<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        boolean found = false;

        while (left != right) {
            if (list.get(left) + list.get(right) == target) {
                System.out.println("Pair found: " + list.get(left) + ", " + list.get(right));
                found = true; // Pair found
                left++;
                right--;
            } else if (list.get(left) + list.get(right) < target) {
                left++;
            } else {
                right--;
            }
        }
        if (!found) {
            System.out.println("No pair found");
            return false; // No pair found
        }
        return true; // Pair found
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        int target = 6;
        pairSum(list, target); // Output: Pair found: 1, 5 or Pair found: 2, 4
        pairSumTwoPointer(list, target); // Output: Pair found: 1, 5 or Pair found: 2, 4
    }
}
