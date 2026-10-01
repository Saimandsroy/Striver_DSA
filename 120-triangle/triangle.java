class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();

        int[][] dp = new int[n][n];

        for(int j=0; j<n; j++){
            
            dp[n-1][j]=triangle.get(n-1).get(j);
        }

        for (int row= n - 2; row >= 0; row--) {

            for (int col = row; col >= 0; col--) {


                int down = triangle.get(row).get(col) + dp[row+1][col];

                int diagonal = triangle.get(row).get(col) + dp[row+1][col+1];

               dp[row][col] = Math.min(down, diagonal);

            }
        }

        return dp[0][0];

    }

}
