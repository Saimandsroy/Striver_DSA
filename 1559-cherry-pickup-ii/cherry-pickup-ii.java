class Solution {
    public int cherryPickup(int[][] grid) {
        int m=grid.length; 
        int n=grid[0].length;

        int [][][] dp = new int[m][n][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        return solve(grid, dp, 0,0,n-1);

    }

    public int solve(int [][] grid, int [][][] dp, int row, int col1, int col2){

        int m=grid.length; 
        int n=grid[0].length;


        if(col1 <0 || col1 >= n || col2<0 || col2 >= n){
            return (int)-1e9;
        }

        if(row==m-1){

            if(col1==col2){
                dp[row][col1][col2]=grid[row][col1];
            }else{
                dp[row][col1][col2]=grid[row][col1]+grid[row][col2];
            }
        }


        if(dp[row][col1][col2] != -1){
            return dp[row][col1][col2];
        }

        int maxi=0;

        for(int dj1=-1; dj1<=1; dj1++){
            for(int dj2=-1; dj2<=1; dj2++){
                
                int value;

                if(col1==col2){
                    value=grid[row][col1];
                }else{
                    value=grid[row][col1]+grid[row][col2];
                }


                value+=solve(grid, dp, row+1, col1+dj1, col2+dj2);

                maxi=Math.max(maxi, value);
            }
        }

        return dp[row][col1][col2]=maxi;


    }

}