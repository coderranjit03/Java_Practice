public class SearchSortMatrix {
    public static boolean starecaseSearch(int matrix[][], int key){
            
        //Start from top-rigth
        int row=0; int col = matrix[0].length-1;
            while(row<matrix.length && col >=0){
                if(matrix[row][col]==key){
                    System.out.print("Key found at ("+row+","+col+")");
                    return true;
                }
                else if(key< matrix[row][col]){ 
                    col--;
                }
                else{
                    row++;
                }
            }
            System.out.println("key not found!");
            return false;


            //start from bottom-left
            // int row=matrix.length-1; int col = 0;
            // while(row>=0 && col <matrix[0].length){
            //     if(matrix[row][col]==key){
            //         System.out.print("Key found at ("+row+","+col+")");
            //         return true;
            //     }
            //     else if(key< matrix[row][col]){ 
            //         row--;
            //     }
            //     else{
            //         col++;
            //     }
            // }
            // System.out.println("key not found!");
            // return false;
    }
    public static void main(String[] args) {
        // int matrix[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int matrix[][] ={{10,20,30,40},{15,25,35,45},{27,29,37,48},{32,33,39,50}};
        int key =33;
        starecaseSearch(matrix, key);
    }
    
}
