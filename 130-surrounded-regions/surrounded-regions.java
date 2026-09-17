class Solution {
    class Pair{
        int row;
        int col;

        Pair(int row, int col){
            this.row=row;
            this.col=col;
        }
    }
    public void solve(char[][] board) {

        int m=board.length;
        int n=board[0].length;
        
        Queue<Pair> queue= new LinkedList<>();

        for(int i=0; i<n; i++){
            if(board[0][i]=='O'){
                queue.add(new Pair(0,i));
                 board[0][i] = '#';
            }

            if(board[m-1][i]=='O'){
                queue.add(new Pair(m-1,i));
                 board[m-1][i] = '#';
            }
        }

        for(int i=0; i<m; i++){
            if(board[i][0]=='O'){
                queue.add(new Pair(i,0));
                 board[i][0] = '#';
            }

            if(board[i][n-1]=='O'){
                queue.add(new Pair(i,n-1));
                 board[i][n-1] = '#';
            }
        }

        int [] dRow={-1,0,+1,0};
        int [] dCol={0,1,0,-1};
        while(!queue.isEmpty()){

            Pair current= queue.poll();

            int row=current.row;
            int col=current.col;

            for(int i=0; i<4; i++){
                int nRow=row+dRow[i];
                int nCol=col+dCol[i];

                if(nRow>=0 && nRow<m && nCol>=0 && nCol<n &&
                    board[nRow][nCol]=='O'
                ){
                    queue.add(new Pair(nRow, nCol));
                    board[nRow][nCol]='#';
                }
            }

        }


        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }else if(board[i][j]=='#'){
                    board[i][j]='O';
                }
            }
        }
    }
}