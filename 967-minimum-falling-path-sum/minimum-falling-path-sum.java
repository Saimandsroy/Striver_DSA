class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;

        int[][] dp = new int[n][n];


       for(int j=0; j<n; j++){
            dp[0][j]=matrix[0][j];
       }

        for (int row = 1; row < n; row++) {
            for (int col = 0; col < n; col++) {
                
                int str = matrix[row][col] + dp[row - 1][col];

                int leftDig = (int)1e9;

                if(col>0){
                    leftDig =matrix[row][col]+dp[row-1][col-1];
                }
                int rightDig = (int)1e9;

                if(col < n-1){
                    rightDig = matrix[row][col]+dp[row - 1][col + 1];
                }

                dp[row][col] = Math.min(str, Math.min(leftDig, rightDig));
            }
        }

        int min=(int)1e9;
        for(int i=0; i<n; i++){
            if(min > dp[n-1][i]){
                min=dp[n-1][i];
            }
        }

        return min;


    }

}