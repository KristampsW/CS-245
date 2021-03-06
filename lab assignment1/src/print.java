import java.util.Scanner;

public class print {
    private static final int row = 9;
    private static final int col = 9;

    public static boolean isSafe(int[][] board,
                                 int row, int col,
                                 int num)
    {
        for (int d = 0; d < board.length; d++) {
            if (board[row][d] == num) {
                return false;
            }
        }
        for (int r = 0; r < board.length; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }
        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart;
             r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart;
                 d < boxColStart + sqrt; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean solveSudoku(
            int[][] board, int n)
    {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        if (isEmpty) {
            return true;
        }
        for (int num = 1; num <= n; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, n)) {
                    return true;
                }
                else {
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    public static void print(
            int[][] board, int n)
    {
        for (int r = 0; r < n; r++) {
            for (int d = 0; d < n; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % (int)Math.sqrt(n) == 0) {
                System.out.print("");
            }
        }
    }

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);

        int[][] board =         {{3, 1, 6, 5, 7, 8, 4, 9, 2 }, 
                                { 5, 2, 9, 1, 3, 4, 7, 6, 8 }, 
                                { 4, 8, 7, 6, 2, 9, 5, 3, 1 }, 
                                { 2, 6, 3, 0, 1, 5, 9, 8, 7 }, 
                                { 9, 7, 4, 8, 6, 0, 1, 2, 5 }, 
                                { 8, 5, 1, 7, 9, 2, 6, 4, 3 }, 
                                { 1, 3, 8, 0, 4, 7, 2, 0, 6 }, 
                                { 6, 9, 2, 3, 5, 1, 8, 7, 4 }, 
                                { 7, 4, 5, 0, 8, 6, 3, 1, 0 }};

        int n = board.length;

        if (solveSudoku(board, n)) {
            print(board, n);
        }
        else {
            System.out.println("No solution");
        }
    }
}