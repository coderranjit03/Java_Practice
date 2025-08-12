
public class MaxSubArray {
    // Kadanes Algorithm Approach
    // public static void KadanesAlg(int[] num) {
    // int maxSoFar = num[0];
    // int currSum = num[0];

    // for (int i = 1; i < num.length; i++) {
    // // Update the current sum if adding the current element increases the sum
    // currSum = Math.max(num[i], currSum + num[i]);

    // // Update the maximum sum so far if the current sum is greater
    // maxSoFar = Math.max(maxSoFar, currSum);
    // }

    // System.out.println("Max SubArray Sum is: " + maxSoFar);
    // }

    // Brute Foce Approch
    // public static void sumArr(int num[]) {

    // int currSum = 0;
    // int MaxSum = Integer.MIN_VALUE;
    // for (int i = 0; i < num.length; i++) {
    // int start = i;
    // for (int j = i; j < num.length; j++) {
    // int end = j;
    // currSum = 0;
    // for (int k = start; k <= end; k++) {
    // currSum = currSum + num[k];
    // }
    // System.out.println(currSum);
    // if (MaxSum < currSum) {
    // MaxSum = currSum;
    // }
    // }
    // }
    // System.out.println("Max sum = " + MaxSum);
    // }

    // Prefix SUm Approach
    public static void prefixSum(int num[]) {
        int current;
        int MaxSum = Integer.MIN_VALUE;
        int prefix[] = new int[num.length];
        prefix[0]=num[0];

        for (int i = 1; i < num.length; i++) {
            prefix[i] = prefix[i - 1] + num[i];
        }

        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length; j++) {
                current = i == 0 ? prefix[j] : prefix[j] - prefix[i - 1];

                if (MaxSum < current) {
                    MaxSum = current;
                }
            }
        }
        System.out.println("Max Sum = "+ MaxSum);
    }

    public static void main(String[] args) {
        // KadanesAlg(nums);
        // sumArr(nums);
        //prefixSum(nums);
        
    }
}