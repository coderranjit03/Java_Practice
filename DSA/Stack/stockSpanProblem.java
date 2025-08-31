package DSA.Stack;

import java.util.Stack;

public class stockSpanProblem {
    public static void stockSpan(int stock[]){
        Stack <Integer> s = new Stack<>();
        int span []= new int[stock.length];
        span[0]=1;
        s.push(0);

        for(int i=1; i<stock.length;i++){
            int curr = stock[i];
            while (!s.isEmpty()&&curr>=stock[s.peek()]) {
                s.pop();
            }

            if(s.isEmpty()){
                span[i]=i+1;            
            }
            else{
                int prevHigh = s.peek();
                span[i]=i-prevHigh;
            }
            s.push(i);
        }

        for(int i=0; i<span.length;i++){
            System.out.println(span[i]+ " ");
        }
    }

    public static void main(String[] args) {
        int stock []= {100,80,60,70,60,85,100};

        stockSpan(stock);

    }
}
