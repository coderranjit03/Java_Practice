package DSA.Stack;

import java.util.Stack;

public class maxAreaHisto {
    
    public static void maxArr(int arr[]){
        int maxArea=0;
        int nextSmallLeft[] =new int[arr.length];
        int nextSmallRight[] =new int[arr.length];

        Stack <Integer> s = new Stack<>();

        for(int i=arr.length-1;i>=0;i--){
            while(!s.isEmpty()&&arr[s.peek()]>=arr[i]){
                s.pop();
            }

            if(s.isEmpty()){
                nextSmallRight[i]=arr.length;
            }else{
                nextSmallRight[i]=s.peek();
            }
            s.push(i);
        }
        for(int i=0;i<arr.length;i++){
            while(!s.isEmpty()&&arr[s.peek()]>=arr[i]){
                s.pop();
            }

            if(s.isEmpty()){
                nextSmallLeft[i]=-1;
            }else{
                nextSmallLeft[i]=s.peek();
            }
            s.push(i);
        }

        for(int i=0;i<arr.length;i++){
            int height=arr[i];
            int width = nextSmallRight[i]-nextSmallLeft[i]-1;
            int area = height*width;
            maxArea = Math.max(maxArea, area);
        }

        System.out.println("MaxArea is "+maxArea);
    }
    public static void main(String[] args) {
        int arr[]={2,1,5,6,2,3};
        maxArr(arr);
    }
}
