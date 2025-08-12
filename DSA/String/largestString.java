package DSA.String;

public class largestString {
    

    public String findLargest(String fruits[]) {
        String largest = fruits[0];
        for (int i = 1; i < fruits.length; i++) {
            if (largest.compareTo(fruits[i]) < 0) {
                largest = fruits[i];
            }
        }
        return largest;
    }

    public static void main(String[] args) {
        String fruits[] = {"acdpple", "banana", "cherry", "date", "elderberry", "fig", "grape"}; 
        largestString obj = new largestString();
        String largestFruit = obj.findLargest(fruits);
        System.out.println("The largest string is: " + largestFruit);
    }
}

