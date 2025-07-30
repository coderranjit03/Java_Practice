
public class trappedRainWater {
    public static int trappingRainWater(int height[]) {
        int n = height.length;
        int trappedWater = 0;

        // calculate leftMax boundary- array
        int leftMax[] = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        // calculate rightMax boundary - array
        int rightMax[] = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        for (int i = 0; i < n; i++) {
            // watreLevel= min(leftMax, rightMax)
            int watreLevel = Math.min(leftMax[i], rightMax[i]);

            // trappedWater calculation
            trappedWater += watreLevel - height[i];
        }

        return trappedWater;
    }

    public static int maxArea(int height[]) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            // Calculate the current area
            int currentHeight = Math.min(height[left], height[right]);
            int width = right - left;
            int area = currentHeight * width;

            // Update maxArea if the current area is larger
            maxArea = Math.max(maxArea, area);

            // Move the pointer that points to the smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int height[] = { 4, 2, 0, 6, 3, 2, 5 };
        System.out.println("Total Trapped Water is : " + trappingRainWater(height));
        System.out.println("Total Trapped Water is : " + maxArea(height));
        
    }

}

