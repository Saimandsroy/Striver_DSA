class Solution {
    class Pair{
        int row;
        int col;
        int tm;

        Pair(int row, int col, int tm){
            this.row=row;
            this.col=col;
            this.tm=tm;
        }
    }
    public int orangesRotting(int[][] grid) {
        
        int m=grid.length;
        int n=grid[0].length;

        Queue<Pair> q= new LinkedList<>();

        int [] [] vis=new int[m][n];
        int cntFresh=0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==2){
                    q.add(new Pair(i, j, 0));
                    vis[i][j]=2;
                }else{
                    vis[i][j]=0;
                }

                if(grid[i][j]==1){
                    cntFresh++;
                }
            }
        }

        int tm=0;

        int [] drow= {-1,0,1,0};
        int [] dcol={0,1,0,-1};

        int count=0;

        while(!q.isEmpty()){
            int r=q.peek().row;
            int c=q.peek().col;
            int t=q.peek().tm;

            tm=Math.max(tm,t);


            q.remove();

            for(int i=0; i<4; i++){
                int nRow=r+drow[i];
                int nCol=c+dcol[i];


                if(nRow>=0 && nRow<m &&
                nCol>=0 && nCol<n &&
                vis[nRow][nCol]==0 &&
                grid[nRow][nCol]==1){
                    q.add(new Pair(nRow,nCol, t+1));

                    vis[nRow][nCol]=2;

                    count++;
                }
            }
        }

        if(count!=cntFresh){
            return -1;
        }



        return tm;


    }
}