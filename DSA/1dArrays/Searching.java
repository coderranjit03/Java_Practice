

public class Searching {
    // public static int LinearSearch(String menu[], String key){
    //     for (int i=0; i<menu.length; i++){
    //         if(menu[i].equals(key)){
    //             return i;
    //         }
    //     }
    //     return -1;
    // }

    // public static void main(String[] args) {
    //     String menu[]= {"vadapav", "gulabjam","cholebhature","dalchawal","bhaji","polibhaji"};
    //     String key = "bhaji";
    //     int index = LinearSearch(menu, key);
    //     int number = index+1;
    //         if(index==-1){
    //             System.out.println("Dish Not Found");
    //         }else{
    //             System.out.println("Dish At number: "+ number);
    //         }
        
    //     System.out.println();
                
    // }



    // BINARY SEARCH
    public static int binarySearch(int num[], int key) {
        int start = 0;
        int end = num.length-1;

        while(start<=end){
            int mid =(start+end)/2;

            if(key==num[mid]){
                return mid;
            }
            if(key<num[mid]){
                end = mid-1;
            }else{
                start=mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int num[]={2,4,6,8,10,12,14,16,18,20,22,24,26,28,30};
        int key = 12;
        int index =binarySearch(num,key);
        System.out.println("The Key is at Index : "+ index );
    }
}

