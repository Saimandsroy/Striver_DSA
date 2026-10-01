class Solution {

    public int minFallingPathSum(int[][] grid) {

        int n = grid.length;

        // dp[row][col] =
        // minimum falling path sum from (row, col)
        // to the last row
        int[][] dp = new int[n][n];

        // Base case: last row
        for (int col = 0; col < n; col++) {
            dp[n - 1][col] = grid[n - 1][col];
        }

        // Bottom -> Top
        for (int row = n - 2; row >= 0; row--) {

            for (int col = 0; col < n; col++) {

                int min = Integer.MAX_VALUE;

                // Try every column in the next row
                for (int nextCol = 0; nextCol < n; nextCol++) {

                    // Same column is not allowed
                    if (nextCol != col) {

                        min = Math.min(min, dp[row + 1][nextCol]);
                    }
                }

                dp[row][col] = grid[row][col] + min;
            }
        }

        // Starting column can be anything in row 0
        int result = Integer.MAX_VALUE;

        for (int col = 0; col < n; col++) {
            result = Math.min(result, dp[0][col]);
        }

        return result;
    }
}