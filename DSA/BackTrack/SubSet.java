package DSA.BackTrack;

public class SubSet {

    public static void printSubSet(String str,String ans, int i){
        //base case
        if(i==str.length()){
            if(ans.length()==0){
                System.out.println("null");
            }else{
                System.out.println(ans);
            }
            return;
        }

        //include
        printSubSet(str, ans + str.charAt(i), i+1);

        //exclude
        printSubSet(str, ans, i+1);

    }
    public static void main(String[] args) {
        String str ="abc";
        String ans = "";
        printSubSet(str, ans, 0);

    }
}
