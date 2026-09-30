class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;

        int [][] dp=new int[m][n];

        if(obstacleGrid[0][0] ==1 || obstacleGrid[m-1][n-1]==1){
            return 0;
        }

        for(int i=0; i<m ; i++){
            Arrays.fill(dp[i], -1);
        }

        return solve(dp, m-1, n-1, obstacleGrid);


    }

    public int solve(int [][] dp, int row, int col, int [][] obstacleGrid){

        if(row==0 && col==0){
            return 1;
        } 

        if(dp[row][col] != -1){
            return dp[row][col];
        }

        int up=0;
        int left=0;

        if(row > 0 && obstacleGrid[row][col] != 1){
            up+=solve(dp, row-1 , col, obstacleGrid);
        }

        if(col > 0 && obstacleGrid[row][col] != 1){
            left+= solve(dp,row, col-1,obstacleGrid);
        }

        return dp[row][col]=left+up;


    }

}