package DSA.Stack;

import java.util.Stack;

// import DSA.String.string;

public class reverseString {

   public static String reversedString(String str){
    Stack <Character> s = new Stack<>();
    int idx =0;
    while(idx<str.length()){
        s.push(str.charAt(idx));
        idx++;
    }

    StringBuilder res = new StringBuilder(" ");
    while (!s.isEmpty()) {
        res.append(s.pop());
    }

    str = res.toString();
    return str;
}

    public static void main(String[] args) {
    String str ="abc";
    String res = reversedString(str);
    System.out.println(res);
    }
}
