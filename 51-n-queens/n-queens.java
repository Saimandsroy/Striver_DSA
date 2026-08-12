class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result= new ArrayList<>();

        char [][] board= new char[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                board[i][j]='.';

            }
        }
        placeQueens(board, 0,result);
        return result;
    }


    private static void placeQueens(char [][] board, int row, List<List<String>>result){
        if(row==board.length){
            List<String> current= new ArrayList<>();

            for(char [] r : board){
                current.add(new String(r));

            }
            result.add(current);
            return;
        }


        for(int col=0; col<board.length; col++){
            if(!isSafe(board,row,col)){
              continue;
            }

            board[row][col]='Q';
            placeQueens(board,row+1,result);

            board[row][col]='.';
        }

    }

    private static boolean isSafe(char[][] board, int row, int col){
        for(int r=0; r<row; r++){
            if(board[r][col]=='Q'){
                return false;
            }
        }

        for(int r=row-1, c=col-1; r>=0&&c>=0;r--,c--){
            if(board[r][c]=='Q'){
                return false;
            }
        }

        for(int r=row-1,c=col+1; r>=0&& c<board.length ; r--,c++){
            if(board[r][c]=='Q'){
                return false;
            }
        }
        return true;
    }
}