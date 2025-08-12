
public class buubulesort {

    public static void bubbleSort(int arr[]) {
        int swapcount = 0;
        for (int turn = 0; turn < arr.length - 1; turn++) {
            boolean isSwap = false;
            for (int j = 0; j < arr.length - 1 - turn; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSwap = true;
                    swapcount++;
                }
            }
            if (!isSwap) {
                break;
            }

        }
        System.out.println("no of swaps are : " + swapcount);
    }

    public static void printArr(int arr[]) {
        System.out.print(" Sorted array is : ");
        for (int i = 0; i < arr.length; i++) {
            
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = { 5, 4, 1, 3, 2 };
        bubbleSort(arr);
        printArr(arr);

    }
}
