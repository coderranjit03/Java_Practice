package DSA.BackTrack;

public class SudokuSolver {

    public static boolean isSafe(int [][]board, int row, int col,int digit){
        //column
        for(int i=0;i<=8;i++){
            if(board[i][col]==digit){
                return false;
            }
        }

        //row
        for(int j=0; j<=8; j++){
            if(board[row][j]==digit){
                return false;
            }
        }

        //Grid 3*3
        int sr=(row/3)*3;
        int sc=(col/3)*3;

        for(int i=sr; i<sr+3; i++){
            for(int j=sc; j<sc+3; j++){
                if(board[i][j]==digit){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean solveSudoku(int[][]board, int row, int col){
        //base case
        if(row==9){
            return true;
        }

        //recursion
        int nextRow=row, nextCol=col+1;
        if(col+1==9){
            nextRow=row+1;
            nextCol=0;
        }
        if(board[row][col]!=0){
            return solveSudoku(board, nextRow, nextCol);
        }

        for(int digit=1; digit<=9; digit++){
            if(isSafe(board, row, col, digit)){
                board[row][col]=digit;
                if(solveSudoku(board, nextRow, nextCol)){
                    return true;
                }
                board[row][col]=0; //backtrack
            }
        }

        return false;

    }

    public static void printBoard(int[][]board){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0 ,0}
        };

        if (solveSudoku(board,0,0)) {
            printBoard(board);
        } else {
            System.out.println("Cannot solve the Sudoku");
        }
    }
}
