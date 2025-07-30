



public class ArraysPair {
    public static void pairPrint(int num[]) {
        int tp = 0;
        for (int i = 0; i < num.length; i++) {
            int Curr = num[i];
            for (int j = i+1; j < num.length; j++) {
                System.out.println("{" + Curr + "," + num[j] + "}");
                tp++;
            }System.out.println();
        }System.out.println("Total Pair are : "+tp);
    }

    public static void main(String[] args) {
        int num [] ={2,4,6,8,10};
        pairPrint(num);
    }
}