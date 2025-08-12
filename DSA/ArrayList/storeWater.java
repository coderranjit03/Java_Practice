package DSA.ArrayList;

import java.util.ArrayList;

//for given an array of heights, find the maximum amount of water that can be stored between the heights.
//The water can be stored between two heights, and the amount of water is determined by the shorter height and the distance between the two heights.

public class storeWater {

    //Brute Force Approach => Time Complexity: O(n^2) Space Complexity: O(1)
    public static int storeWaterContainer(ArrayList<Integer>height) {
        int maxWater = 0;

        for(int i = 0; i<height.size();i++){
            for(int j=i+1;j<height.size();j++){
                int ht = Math.min(height.get(i), height.get(j));
                int widht = j - i;
                int water = ht * widht;
                maxWater = Math.max(maxWater, water);
            }
        }
        return maxWater;
    }

    //two pointer approach => Time Complexity: O(n) Space Complexity: O(1)
    public static int storeWaterContainerTwoPointer(ArrayList<Integer> height) {
        int left = 0;
        int right = height.size() - 1;
        int maxWater = 0;

        while (left < right) {
            int ht = Math.min(height.get(left), height.get(right));
            int width = right - left;
            int water = ht * width;
            maxWater = Math.max(maxWater, water);

            if (height.get(left) < height.get(right)) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }

    public static void main(String[] args) {
        ArrayList<Integer> height = new ArrayList<>();
        height.add(1);
        height.add(8);
        height.add(6);
        height.add(2);
        height.add(5);
        height.add(4);
        height.add(8);
        height.add(3);
        height.add(7);

        System.out.println(storeWaterContainer(height)); // Output: 49
    }
    
}
