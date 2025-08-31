package DSA.Stack;
import java.util.Stack;

public class reverseStack {

    public static void pushAtbottom(Stack <Integer> s, int data){
        if(s.isEmpty()){
            s.push(data);
            return;
        }

        int top = s.pop();
        pushAtbottom(s, data);
        s.push(top);
    }

    public static void reversedStack(Stack<Integer> s){
        if(s.isEmpty()){
            return;
        }

        int top = s.pop();
        reversedStack(s);
        pushAtbottom(s, top);
    }

    public static void printStack(Stack <Integer> s){
        while(!s.isEmpty()){
            System.out.println(s.pop());
        }
    }
    public static void main(String[] args) {
        Stack <Integer> s = new Stack<>();
        s.push(0);
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        reversedStack(s);
        printStack(s);
    }
}
