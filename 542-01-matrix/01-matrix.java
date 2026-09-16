class Solution {

    class Pair {
        int row;
        int col;
        int distance;

        Pair(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }

    }

    public int[][] updateMatrix(int[][] mat) {
        
        int m=mat.length; 
        int n=mat[0].length;


        Queue<Pair> queue= new LinkedList<>();

        int [][] dist= new int[m][n];


        for(int i=0; i<m ;i++){
            for(int j=0; j<n; j++){

                if(mat[i][j]==0){
                    dist[i][j]=0;
                    queue.add(new Pair(i,j,0));

                }else{
                    dist[i][j]= -1;
                }
            }
        }

            int [] dRow={-1,0,1,0};
            int [] dCol={0,1,0,-1};

            while(!queue.isEmpty()){

                Pair current=queue.poll();

                int row= current.row; 
                int col=current.col;


                for(int k=0; k<4; k++){
                    int nRow=row+dRow[k];
                    int nCol=col+dCol[k];


                if(nRow>=0 && nRow<m &&
                    nCol>=0 && nCol<n &&
                    dist[nRow][nCol]==-1){

                    dist[nRow][nCol]=dist[row][col]+1;
                    queue.add(new Pair(nRow,nCol,dist[nRow][nCol]));
                    }
            }
         }
     
        return dist;
    }
}