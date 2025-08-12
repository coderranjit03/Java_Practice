
public class LargestOfArr {
    public static int getLargest(int num[]){
        int largest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;

        for(int i=0; i<num.length;i++){
            if(largest<num[i]){
                largest=num[i];
            }
            if(smallest>num[i]){
                smallest=num[i];
            }
        }
        System.out.println("Smallest Value in Array is: "+ smallest);
        return largest;
    }
    
    public static void main(String[] args) {
        int num[]={12,58,96,14,56,32,54,76,1,2,8,4,6,2000,0};
        int number = getLargest(num);
        System.out.println("Largest Value in Array is: "+ number);
    }
}
      