package DSA.BackTrack; 

public class stringPermutation {
    public static void printPermutation(String str, String ans){
        //base case
        if(str.length() == 0){
            System.out.println(ans);
            return;
        }

        for(int i=0; i<str.length();i++){
            char curr = str.charAt(i);
            String newString =str.substring(0,i) + str.substring(i+1);
            printPermutation(newString, ans + curr);

        }
    }
    public static void main(String[] args) {
        String str = "abc";
        String ans = "";
        printPermutation(str, ans);
    }
}
