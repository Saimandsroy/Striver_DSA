class Solution {

    public void solveSudoku(char[][] board) {
        solve(board);
    }

    //comments are for the reading purposes dont assume AI

    private boolean solve(char[][] board) {

        // Find an empty cell
        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                if (board[row][col] == '.') {

                    // Try numbers 1 to 9
                    for (char num = '1'; num <= '9'; num++) {

                        // Ask: Can I put this number here?
                        if (isSafe(board, row, col, num)) {

                            // CHOOSE
                            board[row][col] = num;

                            // RECURSE
                            if (solve(board)) {
                                return true;
                            }

                            // UNDO
                            board[row][col] = '.';
                        }
                    }

                    // No number worked for this empty cell
                    return false;
                }
            }
        }

        // No empty cell remains
        return true;
    }

    private boolean isSafe(char[][] board, int row, int col, char num) {

        // Check row
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Find starting cell of 3x3 box
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        // Check box
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {

                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}