class Solution {
    public int minFallingPathSum(int[][] grid) {

        int n = grid.length;

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        return solve(grid, dp, n, 0);

    }

    public int solve(int [][]grid, int [][]dp, int lastCol, int row) {

        int n = grid.length;

        if (row == n) {
            return 0;
        }

        if (dp[row][lastCol] != Integer.MAX_VALUE) {
            return dp[row][lastCol];
        }

        int min = Integer.MAX_VALUE;

        for (int col = 0; col <= n - 1; col++) {

            if(col != lastCol){
                int cost= grid[row][col] + solve(grid, dp, col, row+1);

                min=Math.min(min,cost);
            }
        }


        return dp[row][lastCol]=min;

    }
}