class Solution {

    public void dfs(int row, int col, char [][] grid){

        if(row<0 || row>=grid.length ||
            col<0 || col>=grid[0].length ||
            grid[row][col] != '1'
        ){
            return;
        }

        grid[row][col]='0';

        dfs(row+1,col, grid);
        dfs(row-1,col, grid);
        dfs(row,col+1, grid);
        dfs(row,col-1, grid);

    }

    public int numIslands(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(i, j, grid);
                }
            }
        }
        return count;
    }
}