package DSA.Stack;

import java.util.Stack;

public class DuplicateParenthesis {

    public static boolean dupliParan(String str){
        Stack<Character> s = new Stack<>();

        for(int i=0; i<str.length();i++){
            char ch = str.charAt(i);

            if(ch==')'){
                int count = 0;
                while(s.pop()!='('){
                    count++;
                }
                if(count<1){
                    return true;
                }
            }else{
                s.push(ch);
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String str1 = "((a+b)+c+((d)))";
        String str2 ="(a+b-(c+d))";

        System.out.println(dupliParan(str1));
        System.out.println(dupliParan(str2));
    }
}
