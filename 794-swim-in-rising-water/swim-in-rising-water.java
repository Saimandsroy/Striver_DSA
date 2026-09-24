class Solution {

    class Pair{
        int time;
        int row;
        int col; 

        Pair(int time, int row, int col){
            this.time=time;
            this.row=row;
            this.col=col;
        }
    }
    public int swimInWater(int[][] grid) {
        

        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b) -> a.time-b.time);

        int n=grid.length;
        int [][] dist= new int [n][n];

        for(int i=0; i<n; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[0][0] = grid[0][0];;

        pq.add(new Pair(grid[0][0],0,0));

        int [] dRow={-1,0,1,0};
        int [] dCol={0,1,0,-1};

        while(!pq.isEmpty()){
            
            Pair current= pq.poll();

            int time=current.time;
            int row=current.row;
            int col=current.col;


            if(row==n-1 && col == n-1){
                return time;
            }


            for(int i=0; i<4; i++){

                int nRow=row+dRow[i];
                int nCol=col+dCol[i];

            if(nRow>=0 && nRow<n && nCol>=0 && nCol<n){
                int newTime=Math.max(time, grid[nRow][nCol]);

                if(newTime< dist[nRow][nCol]){
                   dist[nRow][nCol]=newTime;

                    pq.add(new Pair(newTime, nRow, nCol));
                }
            }


            }


        }

        return -1;


    }
}