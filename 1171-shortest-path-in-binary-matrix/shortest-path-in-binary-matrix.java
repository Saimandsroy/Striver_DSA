class Solution {
    class Pair{
        int distance; 
        int row;
        int col;

        Pair(int distance, int row, int col){
            this.distance=distance;
            this.row=row;
            this.col=col;
        }
    }
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        int n=grid.length;


        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        if (n == 1) {
            return 1;
        }

        if(grid.length==1){
            return 1;
        }

        Queue<Pair> q = new LinkedList<>();
        
        int [][] dist= new int [n][n];
        
        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid.length; j++){
                dist[i][j]=-1;
            }
        }
        
        dist[0][0]=1;

        q.add(new Pair(1, 0,0));


        int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dCol = {-1, 0,  1, -1, 1,-1, 0, 1};

        while(!q.isEmpty()){

            Pair current=q.poll();

            int distance=current.distance;
            int row=current.row;
            int col=current.col;

            for(int i=0; i<8 ; i++){
                int nRow=row+dRow[i];
                int nCol=col+dCol[i];


                if(nRow>=0 && nRow<n && nCol>=0 && nCol<n &&
                    grid[nRow][nCol]==0 && dist[nRow][nCol]==-1){
                        dist[nRow][nCol]=distance+1;


                        if(nRow==n-1 && nCol==n-1){
                            return distance+1;
                        }
                    q.add(new Pair(distance+1, nRow, nCol));
                    }
            }
        }

        return -1;
    }
}