package DSA.BackTrack;

public class NQueen {
    public static boolean isSafe(char board[][], int row, int col){
        //vertical up
        for(int i= row-1; i>=0;i--){
            if(board[i][col]=='Q'){
                return false;
            }
        }

        //diagonal left up
        for(int i=row-1, j=col-1; i>=0 && j>=0; i--, j--){
            if(board[i][j]=='Q'){
                return false;
            }
        }           

        //diagonal right up
        for(int i=row-1, j=col+1; i>=0 && j<board.length; i--, j++){
            if(board[i][j]=='Q'){
                return false;
            }
        }
        return true;
    }

    public static boolean solveNQueen(char board[][], int row) {
        //base case
        if(row == board.length) {
            System.out.println("---------chess board---------");
            printBoard(board);
            count++;
            return true;
        }

        //column loop
        for(int j=0; j<board.length; j++) {
            if(isSafe(board, row, j)) {
                board[row][j] = 'Q';
                solveNQueen(board, row + 1);
                board[row][j] = 'x'; //backtrack
            }
        }
        return false;
    }


    public static void printBoard(char board[][]) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int count = 0;
    public static void main(String[] args) {
        int n = 5;
        char board[][] = new char[n][n];
        
        
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++) {
                board[i][j] = 'x';
            }
        }
        if(!solveNQueen(board, 0)) {
            System.out.println("only " + count + " ways to solve the "+n+"*"+n+ " -queen problem");
        }
        System.out.println("Total ways to solve N-Queen: " + count);
    }
}
