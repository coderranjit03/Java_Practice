package DSA.DivideConquer;

public class mergeSortAlg {

    // Recursive function
    public static void mergeSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, start, mid, end);

    }

    // merge function This function merges two sorted subarrays into one sorted
    // array
    public static void merge(int[] arr, int start, int mid, int end){
        int temp[] = new int [end - start + 1];
        int i = start, // left subarray
        j = mid + 1, // right subarray
        k = 0; // index for temp array

        while (i <= mid && j <= end) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }


        // Copy remaining elements of left subarray, if any
        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }

        // Copy remaining elements of right subarray, if any
        while (j <= end) {
            temp[k] = arr[j];
            j++;
            k++;
        }

        // Copy sorted elements back to original array
        for (k = 0, i = start; k < temp.length; k++, i++) {
            arr[i] = temp[k];
        }

    }

    public static void main(String[] args) {
        int arr[] = { 6, 3, 9, 5, 2, 8 };
        mergeSort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }
}