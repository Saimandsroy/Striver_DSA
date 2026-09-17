class Solution {

    public void dfs(int row,int col, int [][]grid){

        if(row<0 || row>= grid.length ||
            col<0 || col>=grid[0].length ||
            grid[row][col] != 1
        ){
            return;
        }

        grid[row][col]='#';


        dfs(row-1,col,grid);
        dfs(row+1,col,grid);
        dfs(row,col-1,grid);
        dfs(row,col+1,grid);

    }
    public int numEnclaves(int[][] grid) {
        
         int m=grid.length; 
         int n=grid[0].length; 


         for(int i=0; i<n; i++){

            if(grid[0][i]==1){
                dfs(0,i,grid);
            }

            if(grid[m-1][i]==1){
                dfs(m-1,i,grid);
            }
         }

         for(int i=0; i<m; i++){

            if(grid[i][0]==1){
                dfs(i,0,grid);
            }

            if(grid[i][n-1]==1){
                dfs(i,n-1,grid);
            }

         }

        int count =0;
         for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==1){
                    count++;
                }
            }
         }

         return count;
    }
}