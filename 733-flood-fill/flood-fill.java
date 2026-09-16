class Solution {
    class Pair{
        int row;
        int col;
        int target;

        Pair(int row, int col, int target){
            this.row=row;
            this.col=col;
            this.target=target;
        }

    }
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int m=image.length; 
        int n=image[0].length;
        int [][] vis= new int[m][n];

        Queue<Pair> queue = new LinkedList<>();

        for(int i=0; i<m ; i++){
            for(int j=0; j<n ;j++){
                if(i==sr && j==sc){
                    int target=image[i][j];
                    queue.add(new Pair(i,j,target));
                    vis[i][j]=color;
                }else{
                    vis[i][j]=image[i][j];
                }
            }
        }


        int [] dRow={-1,0,+1,0};
        int [] dCol={0,+1,0,-1};

        
        while(!queue.isEmpty()){
            int r=queue.peek().row;
            int c=queue.peek().col;
            int t=queue.peek().target;


            queue.remove();
            
        for(int i=0; i<4; i++){
            int nRow=r+dRow[i];
            int nCol=c+dCol[i];

            if(nRow>=0 && nRow<m &&
                nCol>=0 && nCol<n &&
                image[nRow][nCol]==t &&
                vis[nRow][nCol] !=color
            ){
                queue.add(new Pair(nRow, nCol, t));
                vis[nRow][nCol]=color;
            }

        }
            
        }

    return vis;
      
        
    }
}