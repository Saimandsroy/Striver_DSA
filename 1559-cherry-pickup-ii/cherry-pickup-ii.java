class Solution {
    public int cherryPickup(int[][] grid) {

        int m=grid.length; 
        int n=grid[0].length;

        int [][][] dp= new int [m][n][n];

        for(int col1=0; col1<n; col1++){
            for(int col2=0; col2<n; col2++){
            
                if(col1==col2){
                    dp[m-1][col1][col2]=grid[m-1][col1];
                }else{
                    dp[m-1][col1][col2]=grid[m-1][col1]+grid[m-1][col2];
                }
            }
        }

        for(int row=m-2; row>=0; row--){

            for(int col1=0; col1<n; col1++){
                for(int col2=0; col2<n; col2++){

                    int maxi=Integer.MIN_VALUE;

                    for(int dj1=-1; dj1<=1; dj1++){
                        for(int dj2=-1; dj2<=1; dj2++){

                            int nextCol1=col1+dj1;
                            int nextCol2=col2+dj2;


                        if(nextCol1>=0 && nextCol1<n  && nextCol2>=0 && nextCol2<n ){
                        int value;


                        if(col1==col2){
                            value=grid[row][col1];
                        }else{
                            value=grid[row][col1]+grid[row][col2];
                        }

                        value+=dp[row+1][nextCol1][nextCol2];

                        maxi=Math.max(maxi, value);
                        }
                    }
                }

                dp[row][col1][col2]=maxi;
            }
        }

        
    }

    return dp[0][0][n-1];

}

    
}