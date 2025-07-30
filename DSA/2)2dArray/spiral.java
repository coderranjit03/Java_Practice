public class spiral {
    public static void prntSpiral(int matrix[][]){
    int startRow=0;
    int startCol =0;
    int endRow= matrix.length-1;
    int endCol = matrix[0].length-1;

    while(startRow<=endRow&& startCol<=endCol){
        //top
        //-->  j for col
        //-->  i for row
        for(int j=startCol; j<=endCol;j++){
            System.out.print(matrix[startRow][j]+" ");
        }

        //right
        for(int i=startRow+1; i<=endRow;i++){
            System.out.print(matrix[i][endCol]+ " ");
        }

        //bottom
        for(int k=endCol-1;k>=startCol;k--){
            if(startRow==endRow){
                break;
            }
            System.out.print(matrix[endRow][k]+ " ");
        }

        //left
        for(int l=endCol-1; l>=startRow+1;l--){
            if(startCol==endCol){
                break;
            }
            System.out.print(matrix[l][startCol]+" ");
        }

        startCol++;
        startRow++;
        endCol--;
        endRow--;
    }
    System.out.println();
    }

    public static void main(String[] args) {
        int matrix[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        prntSpiral(matrix);
    }
}
