package DSA.DivideConquer;

public class SearchInRotatedArr {
    public static int search(int[] arr, int target,int start, int end) {
        if(start > end) {
            return -1; // Element not found
        }

        int mid = start + (end - start) / 2;
        if(arr[mid] == target) {
            return mid; // Element found
        }

        if(arr[start] <= arr[mid]) {
            // Left half is sorted
            if(arr[start] <= target && target <= arr[mid]) {
                return search(arr, target, start, mid - 1);
            } else {
                return search(arr, target, mid + 1, end);
            }
        } else {
            // Right half is sorted
            if(arr[mid] <= target && target <= arr[end]) {
                return search(arr, target, mid + 1, end);
            } else {
                return search(arr, target, start, mid - 1);
            }
        }     
        
    }

    public static void main(String[] args) {
        int arr[] = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 2;
        int result = search(arr, target, 0, arr.length - 1);
        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found in the array.");
        }
    }
}
