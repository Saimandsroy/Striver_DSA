class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;

        int[] prev = new int[n];


       for(int j=0; j<n; j++){
            prev[j]=matrix[0][j];
       }

        for (int row = 1; row < n; row++) {

            int [] temp= new int[n];

            for (int col = 0; col < n; col++) {
                
                int str = matrix[row][col] + prev[col];

                int leftDig = (int)1e9;

                if(col>0){
                    leftDig =matrix[row][col]+prev[col-1];
                }
                int rightDig = (int)1e9;

                if(col < n-1){
                    rightDig = matrix[row][col]+prev[col + 1];
                }

                temp[col] = Math.min(str, Math.min(leftDig, rightDig));
            }

            prev=temp;
        }

        int min=(int)1e9;
        for(int i=0; i<n; i++){
            if(min > prev[i]){
                min=prev[i];
            }
        }

        return min;


    }

}