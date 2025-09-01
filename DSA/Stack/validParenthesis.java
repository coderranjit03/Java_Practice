package DSA.Stack;

import java.util.Stack;

public class validParenthesis {

    public static boolean validparanth(String str){
        Stack <Character> s = new Stack<>();

        for(int i =0; i<str.length();i++){
            char ch = str.charAt(i);

            if(ch=='('||ch=='{'||ch=='['){
                s.push(ch);
            }else{
                if(s.isEmpty()){
                    return false;
                }
                if(s.peek()=='('&&ch==')'||
                   s.peek()=='{'&&ch=='}'||
                   s.peek()=='['&&ch==']'){
                    s.pop();
                }else{
                    return false;
                }
            }
        }

        if(s.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        String str1 = "(((([{()}]))))";
        String str2 ="[{}]([]]])";

        System.out.println(validparanth(str1));
        System.out.println(validparanth(str2));
    }
}
